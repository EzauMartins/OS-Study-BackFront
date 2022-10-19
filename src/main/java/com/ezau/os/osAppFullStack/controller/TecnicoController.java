package com.ezau.os.osAppFullStack.controller;

import com.ezau.os.osAppFullStack.dto.TecnicoDTO;
import com.ezau.os.osAppFullStack.model.Tecnico;
import com.ezau.os.osAppFullStack.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping( value="/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
       Tecnico obj = tecnicoService.findById(id);
       TecnicoDTO objDTO = new TecnicoDTO(obj);
       return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
 //ALTERNATIVA->/*List<TecnicoDTO> listDTO = tecnicoService.findAll().stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());      */
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDTO = new ArrayList<>();

        list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
        Tecnico obj = tecnicoService.create(objDTO);
        //BOAS PRATICAS PASSAR URI DE ACESSO AO OBJETO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
        TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id,objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
