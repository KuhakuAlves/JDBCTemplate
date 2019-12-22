package com.laboratorio.testes.repository;

import com.laboratorio.testes.Tipos.Banco;
import com.laboratorio.testes.model.Tabela;
import com.laboratorio.testes.model.TabelaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TabelaDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    DataSource datasource;

    @Autowired
    @Qualifier("conexao")
    DataSource datasourceConexao;


    public int qtdDados(Banco tipoBanco){
        switch (tipoBanco) {
            case postgres: return qtdDatasource(datasource);
            case conexao2: return qtdDatasource(datasourceConexao);
        }
        return 0;
    }

    public List<Tabela> consultarTabela(Banco tipoBanco){
        switch (tipoBanco) {
            case postgres: return pesquisaTabelas(datasource);
            case conexao2: return pesquisaTabelas(datasourceConexao);
        }
        return new ArrayList<>();
    }

    public void insert(Banco tipoBanco, String descricao){
        switch (tipoBanco) {
            case postgres: insertTabela(datasource, descricao);
              break;
            case conexao2: insertTabela(datasourceConexao, descricao);
        }
    }

    public void update(Banco tipoBanco, Tabela tabela){
        switch (tipoBanco) {
            case postgres: updateTabela(datasource, tabela);
                break;
            case conexao2: updateTabela(datasourceConexao, tabela);
        }
    }

    public void deleteById(Banco tipoBanco, int id){
        switch (tipoBanco) {
            case postgres: deleteTabelaById(datasource, id);
                break;
            case conexao2: deleteTabelaById(datasourceConexao, id);
        }
    }

    public void deleteAll(Banco tipoBanco){
        switch (tipoBanco) {
            case postgres: deleteAllTabela(datasource);
                break;
            case conexao2: deleteAllTabela(datasourceConexao);
        }
    }

    private int qtdDatasource( DataSource ds){

        JdbcTemplate template = new JdbcTemplate(ds);
        String query = "select count(*) from tabela";
        return (Integer)template.queryForObject(query, Integer.class);
    }

    private List<Tabela> pesquisaTabelas( DataSource ds){
        JdbcTemplate template = new JdbcTemplate(ds);
        String query = "select * from tabela";
        List<Tabela> tabelas = template.query(query, new BeanPropertyRowMapper(Tabela.class));
        //List<Tabela> tabelas =  TabelaRowMapper.convertTabela(template.queryForList(query, new TabelaRowMapper()));
        return tabelas;
    }

    private void insertTabela( DataSource ds, String descricao){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "INSERT INTO tabela values ( (select COALESCE(max(id), 0) + 1 from tabela), ? )";
        template.update(sql, new Object[]{ descricao});
    }

    private void updateTabela( DataSource ds, Tabela tabela){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "update tabela set descricao = ? where id = ?";
        template.update(sql, new Object[]{ tabela.getId(), tabela.getDescricao()});
    }

    private void deleteTabelaById( DataSource ds, int id){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "delete from tabela where id = ?";
        template.update(sql, new Object[]{ id });
    }

    private void deleteAllTabela(DataSource ds) {
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "delete from tabela";
        template.update(sql);
    }
}
