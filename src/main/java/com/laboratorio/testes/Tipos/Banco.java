package com.laboratorio.testes.Tipos;

public enum Banco {
    postgres(1),
    conexao2(2);

    public int valor;
    Banco(int valor){
        this.valor = valor;
    }
}
