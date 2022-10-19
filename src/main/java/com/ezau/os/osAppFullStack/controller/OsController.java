package com.ezau.os.osAppFullStack.controller;

import com.ezau.os.osAppFullStack.dto.OsDTO;
import com.ezau.os.osAppFullStack.model.OS;
import com.ezau.os.osAppFullStack.service.OsService;
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
@RequestMapping(value = "/os")
public class OsController {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<OsDTO> findById(@PathVariable Integer id) throws IllegalAccessException {
        OsDTO objDTO = new OsDTO(osService.findById(id));

        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    private ResponseEntity<List<OsDTO>> findAll() {
        List<OsDTO> listOS = new ArrayList<>();
        List<OS> osList = osService.findAll();

        osList.forEach(obj -> {
            try {
                listOS.add(new OsDTO(obj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return ResponseEntity.ok().body(listOS);
    }


    @PostMapping
    public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO objDTO) throws IllegalAccessException {
        OsDTO obj = new OsDTO(osService.create(objDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO osDTO) throws IllegalAccessException {
          OsDTO newObjDTO = new OsDTO(osService.update(osDTO));
          return ResponseEntity.ok().body(newObjDTO);
    }

}
