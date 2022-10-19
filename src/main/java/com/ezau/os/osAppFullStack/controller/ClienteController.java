package com.ezau.os.osAppFullStack.controller;

import ch.qos.logback.core.net.server.Client;
import com.ezau.os.osAppFullStack.dto.ClienteDTO;
import com.ezau.os.osAppFullStack.model.Cliente;
import com.ezau.os.osAppFullStack.model.Pessoa;
import com.ezau.os.osAppFullStack.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = clienteService.findById(id);
        ClienteDTO objDTO = new ClienteDTO(obj);
        return ResponseEntity.ok(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
       return clienteService.findAll();

    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid  @RequestBody ClienteDTO clienteDTO){
        Cliente obj = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid  @RequestBody ClienteDTO clienteDTO) {
       return ResponseEntity.ok().body(new ClienteDTO (clienteService.update(id, clienteDTO)));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
