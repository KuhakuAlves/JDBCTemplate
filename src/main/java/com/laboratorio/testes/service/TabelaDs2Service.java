package com.laboratorio.testes.service;

import com.laboratorio.testes.Tipos.Banco;
import com.laboratorio.testes.model.Tabela;
import com.laboratorio.testes.repository.TabelaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaDs2Service {
    @Autowired
    private TabelaDao tabelaDao;

    public List<Tabela> selectAll(){
        return tabelaDao.consultarTabela(Banco.conexao2);
    }
    public void insertTab(List<Tabela> tabelas){
        this.deleteTab();
        for (Tabela tab : tabelas){
            try {
                tabelaDao.insert(Banco.conexao2, tab.getDescricao());
            }catch(Exception ex){
                throw new RuntimeException("Ocorreram erros ao tentar inserir o registro" + tab.getId(), ex);
            }
        }
    }

    public void updateTab(Tabela tabela){
        try {
            tabelaDao.update(Banco.conexao2,tabela);
        }catch(Exception ex){
            throw new RuntimeException("Ocorreram erros ao tentar atualizar o registro" + tabela.getId(), ex);
        }
    }

    public void deleteTabById(int id){
        try {
            tabelaDao.deleteById(Banco.conexao2,id);
        }catch(Exception ex){
            throw new RuntimeException("Ocorreram erros ao tentar deletar o registro" + id, ex);
        }
    }

    public void deleteTab(){
        try {
            tabelaDao.deleteAll(Banco.conexao2);
        }catch(Exception ex){
            throw new RuntimeException("Ocorreram erros ao tentar deletar os registros", ex);
        }
    }
}
