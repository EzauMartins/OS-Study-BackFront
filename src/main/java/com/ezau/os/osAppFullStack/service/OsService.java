package com.ezau.os.osAppFullStack.service;

import com.ezau.os.osAppFullStack.dto.OsDTO;
import com.ezau.os.osAppFullStack.enunm.Prioridade;
import com.ezau.os.osAppFullStack.enunm.Status;
import com.ezau.os.osAppFullStack.model.Cliente;
import com.ezau.os.osAppFullStack.model.OS;
import com.ezau.os.osAppFullStack.model.Tecnico;
import com.ezau.os.osAppFullStack.repository.OSRepository;
import com.ezau.os.osAppFullStack.service.exception.DataIntegratyViolationException;
import com.ezau.os.osAppFullStack.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(
                ()-> new ObjectNotFoundException("Objeto Não Encontrado -> " + id + " | Tipo " + OS.class.getName()));
    }

    public List<OS> findAll(){
        return osRepository.findAll();
    }

    public OS create(OsDTO objDTO) throws IllegalAccessException {

        return fromDTO(objDTO);
        }

    public OS fromDTO(OsDTO objDTO) throws IllegalAccessException {
        OS newObj = new OS();
        newObj.setId(objDTO.getId());
        newObj.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        newObj.setStatus(Status.toEnum(objDTO.getStatus()));

        if (newObj.getStatus().getCod().equals(2)){
            newObj.setDtFechamento(LocalDateTime.now());
        }

        Cliente cli = clienteService.findById(objDTO.getCliente());
        Tecnico tec = tecnicoService.findById(objDTO.getTecnico());
        newObj.setCliente(cli);
        newObj.setTecnico(tec);

        return  osRepository.save(newObj);

    }

    public OS update(@Valid OsDTO objDTO) throws IllegalAccessException {

        findById(objDTO.getId());
        return fromDTO(objDTO);

    }

}
