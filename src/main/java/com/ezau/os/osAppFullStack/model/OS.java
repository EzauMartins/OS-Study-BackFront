package com.ezau.os.osAppFullStack.model;

import com.ezau.os.osAppFullStack.enunm.Prioridade;
import com.ezau.os.osAppFullStack.enunm.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class OS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dtAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dtFechamento;
    private Integer prioridade;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public OS() {
        this.setDtAbertura(LocalDateTime.now());
        this.setStatus(Status.ABERTO);
        this.setPrioridade(Prioridade.BAIXA);
    }

    public OS(Integer id, Prioridade prioridade,
              Status status, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.setDtAbertura(LocalDateTime.now());
        this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
        this.status = (status == null) ? 0: status.getCod();
        this.tecnico = tecnico;
        this.cliente = cliente;
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

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade.getCod();
    }

    public Status getStatus() throws IllegalAccessException {
        return Status.toEnum(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OS os = (OS) o;
        return id.equals(os.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
