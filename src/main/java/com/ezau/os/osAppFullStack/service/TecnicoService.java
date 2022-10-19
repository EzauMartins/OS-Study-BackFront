package com.ezau.os.osAppFullStack.service;

import com.ezau.os.osAppFullStack.dto.TecnicoDTO;
import com.ezau.os.osAppFullStack.model.Pessoa;
import com.ezau.os.osAppFullStack.model.Tecnico;
import com.ezau.os.osAppFullStack.repository.PessoaRepository;
import com.ezau.os.osAppFullStack.repository.TecnicoRepository;
import com.ezau.os.osAppFullStack.service.exception.DataIntegratyViolationException;
import com.ezau.os.osAppFullStack.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return  obj.orElseThrow(()-> new ObjectNotFoundException("Objeto Não Encontrado -> " + id + " | Tipo " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        if (findByCPF(objDTO) != null){
            throw new DataIntegratyViolationException("CPF JÁ CADASTRADO !!!");
        }
            return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone() ));
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){ //Verifica se cpf existe, e se o id para esse CPF é == ao SEU se for diferente não é o seu.
            throw new DataIntegratyViolationException("CPF JÁ CADASTRADO !!!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return tecnicoRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Técnico possue ordens de serviço, não pode ser deletado");
        }
        tecnicoRepository.deleteById(id);
    }
    private Pessoa findByCPF(TecnicoDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

}
