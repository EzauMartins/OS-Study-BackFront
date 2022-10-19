package com.ezau.os.osAppFullStack.service;

import com.ezau.os.osAppFullStack.dto.ClienteDTO;
import com.ezau.os.osAppFullStack.dto.TecnicoDTO;
import com.ezau.os.osAppFullStack.model.Cliente;
import com.ezau.os.osAppFullStack.model.Pessoa;
import com.ezau.os.osAppFullStack.model.Tecnico;
import com.ezau.os.osAppFullStack.repository.ClienteRepository;
import com.ezau.os.osAppFullStack.repository.PessoaRepository;
import com.ezau.os.osAppFullStack.service.exception.DataIntegratyViolationException;
import com.ezau.os.osAppFullStack.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
       Optional<Cliente> obj = clienteRepository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id:"+id+ " | Tipo " + Pessoa.class.getName()));
    }

    public Cliente create(ClienteDTO clienteDTO) {

        if (findByCPF(clienteDTO) != null){
            throw new DataIntegratyViolationException("CPF JÁ CADASTRADO !!!");
        }
        return clienteRepository.save(new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));

    }


    private Pessoa findByCPF(ClienteDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> obj = clienteRepository.findAll();
        List<ClienteDTO> objDTO = new ArrayList<>();
        obj.forEach(objlist -> objDTO.add(new ClienteDTO(objlist)));
        return ResponseEntity.ok().body(objDTO);
    }

    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {//Verifica se cpf existe, e se o id para esse CPF é == ao SEU se for diferente não é o seu.
        Cliente old = findById(id);

        if(findByCPF(clienteDTO) != null && findByCPF(clienteDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF JÁ CADASTRADO !!!");
        }

        old.setCpf(clienteDTO.getCpf());
        old.setNome(clienteDTO.getNome());
        old.setTelefone(clienteDTO.getTelefone());

        return clienteRepository.save(old);

    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Cliente possue ordens de serviço, não pode ser deletado");
        }
        clienteRepository.deleteById(id);
    }
}
