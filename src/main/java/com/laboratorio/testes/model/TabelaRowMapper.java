package com.laboratorio.testes.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabelaRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
      Tabela tabela = new Tabela();
      tabela.setId(rs.getInt("id"));
      tabela.setDescricao(rs.getString("descricao"));
      return tabela;
    }

    public static List<Tabela> convertTabela(List<Map<String, Object>> entrada){
        List<Tabela> tabelas = new ArrayList<>();
        for (Map<String, Object> it : entrada){
            tabelas.add((Tabela)it.get(it.keySet()));
      }
      return tabelas;
    }
}
