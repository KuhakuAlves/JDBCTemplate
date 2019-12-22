package com.laboratorio.testes.Controller;

import com.laboratorio.testes.model.Tabela;
import com.laboratorio.testes.service.TabelaDs1Service;
import com.laboratorio.testes.service.TabelaDs2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("Tabelas")
public class TabelaController {

    @Autowired
    private TabelaDs1Service tabelaDs1Service;

    @Autowired
    private TabelaDs2Service tabelaDs2Service;


    @GetMapping("ConsultarPostgres")
    public ResponseEntity<List<Tabela>> consultaPostgres(){
        List<Tabela> tabelaList = tabelaDs1Service.selectAll();
        return  ResponseEntity.ok().body(tabelaList);
    }

    @GetMapping("ConsultarConexao")
    public ResponseEntity<List<Tabela>> consultaConexao(){
        List<Tabela> tabelaList = tabelaDs2Service.selectAll();
        return  ResponseEntity.ok().body(tabelaList);
    }
    @PostMapping("insertByPostgres")
    public ResponseEntity<?> inserirPostgres(){
        tabelaDs1Service.insertTab(tabelaDs2Service.selectAll());
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @PostMapping("insertByConexao")
    public ResponseEntity<?> inserirConexaos(){
        tabelaDs2Service.insertTab(tabelaDs1Service.selectAll());
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @PutMapping("updateByPostgres")
    public ResponseEntity<?> updatePostgres(@RequestBody Tabela tabela){
        if (tabela == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("necessario informar os dados para update");
        }
        tabelaDs1Service.updateTab(tabela);
        return ResponseEntity.status(HttpStatus.CREATED).body("criado com sucesso");
    }

    @PutMapping("updateByConexao")
    public ResponseEntity<?> updateConexaos(@RequestBody Tabela tabela){
        if (tabela == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("necessario informar os dados para update");
        }
        tabelaDs2Service.updateTab(tabela);
        return ResponseEntity.status(HttpStatus.CREATED).body("criado com sucesso");
    }

    @DeleteMapping("deletePostgres")
    public ResponseEntity<?> deleteAllPostgres(){
      tabelaDs1Service.deleteTab();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deletada toda a tabela");
    }

    @DeleteMapping("deleteConexao")
    public ResponseEntity<?> deleteAllConexao(){
        tabelaDs2Service.deleteTab();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deletada toda a tabela");
    }

    @DeleteMapping("deletePostgres/{id}")
    public ResponseEntity<?> deletePostgres(@RequestParam("id") int id){
        tabelaDs1Service.deleteTabById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deletado registro de id: " + id);
    }

    @DeleteMapping("deleteConexao/{id}")
    public ResponseEntity<?> deleteConexao(@RequestParam("id") int id){
        tabelaDs2Service.deleteTabById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deletado registro de id: " + id);
    }

}
