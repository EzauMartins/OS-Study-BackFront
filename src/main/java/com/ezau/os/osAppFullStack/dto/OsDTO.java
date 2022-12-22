package com.ezau.os.osAppFullStack.dto;

import com.ezau.os.osAppFullStack.enunm.Prioridade;
import com.ezau.os.osAppFullStack.enunm.Status;
import com.ezau.os.osAppFullStack.model.Cliente;
import com.ezau.os.osAppFullStack.model.OS;
import com.ezau.os.osAppFullStack.model.Tecnico;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OsDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dtAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dtFechamento;


    private Integer prioridade;

    private Integer status;

    private Integer tecnico;

    private Integer cliente;

    public OsDTO() {
    }

    public OsDTO(OS obj) throws IllegalAccessException {
        this.id = obj.getId();
        this.dtAbertura = obj.getDtAbertura();
        this.dtFechamento = obj.getDtFechamento();
        this.prioridade = obj.getPrioridade().getCod();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(LocalDateTime dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public LocalDateTime getDtFechamento() {
        return dtFechamento;
    }

    public void setDtFechamento(LocalDateTime dtFechamento) {
        this.dtFechamento = dtFechamento;
    }

    public Prioridade getPrioridade() throws IllegalAccessException {
        return Prioridade.toEnum(this.prioridade);
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() throws IllegalAccessException {
        return Status.toEnum(this.status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}
