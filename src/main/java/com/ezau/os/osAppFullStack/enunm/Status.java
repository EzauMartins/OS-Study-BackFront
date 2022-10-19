package com.ezau.os.osAppFullStack.enunm;

public enum Status {

    CRIADO (0,"CRIADO"),
    ABERTO(1,"ABERTO"),
    FECHADO(2,"FECHADO");

    private Integer cod;
    private String descricao;

    Status(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }


    public  static Status toEnum(Integer cod) throws IllegalAccessException {
        if(cod == null){
            return null;
        }

        for(Status x : Status.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalAccessException("codigo invalido!!");
    }
}

