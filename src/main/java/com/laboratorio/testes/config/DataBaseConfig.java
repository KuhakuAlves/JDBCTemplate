package com.laboratorio.testes.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties mainProprierties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){ return mainProprierties().initializeDataSourceBuilder().build();
    }

    @Bean("conexao")
    @ConfigurationProperties(prefix = "spring.conexao")
    public DataSource   datasourceConexao(){ return DataSourceBuilder.create().build();
    }
}
