package com.ezau.os.osAppFullStack.service;

import com.ezau.os.osAppFullStack.enunm.Prioridade;
import com.ezau.os.osAppFullStack.enunm.Status;
import com.ezau.os.osAppFullStack.model.Cliente;
import com.ezau.os.osAppFullStack.model.OS;
import com.ezau.os.osAppFullStack.model.Tecnico;
import com.ezau.os.osAppFullStack.repository.ClienteRepository;
import com.ezau.os.osAppFullStack.repository.OSRepository;
import com.ezau.os.osAppFullStack.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBservice {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;

    public void instaciaDB(){
        Tecnico t1 = new Tecnico(null,"NomeTecnico01","855.000.170-80","(92)98888-2222");
        Cliente c1 = new Cliente(null,"NomeCliente01", "523.616.490-71","(39)97777-1111");
        OS os1 = new OS(null, Prioridade.ALTA, Status.ABERTO,t1,c1);

        Tecnico t2 = new Tecnico(null,"NomeTecnico02","819.118.390-04","(92)98888-2222");

        t1.getList().add(os1);
        c1.getList().add(os1);

        clienteRepository.saveAll(Arrays.asList(c1));
        tecnicoRepository.saveAll(Arrays.asList(t1,t2));
        osRepository.save(os1);
    }

}
