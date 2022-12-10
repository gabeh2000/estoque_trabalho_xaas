package com.xaas.estoque.controller;

import com.xaas.estoque.model.Produto;
import com.xaas.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto createProduto(@RequestBody Produto produto){
        return service.addProduto(produto);
    }

    @GetMapping
    public List<Produto> getProdutos() {
        return service.findAllProdutos();
    }


    @GetMapping("/{produtoId}")
    public Produto getProduto(@PathVariable String produtoId){
        return service.getProdutoByProdutoId(produtoId);
    }

    @GetMapping("/qtd/{qtd}")
    public List<Produto> findProdutoUsingQtd(@PathVariable int qtd){
        return service.getProdutoByQtd(qtd);
    }

    @GetMapping("/nome/{nome}")
    public List<Produto> getProdutoByNome(@PathVariable String nome){
        return service.getProdutoByNome(nome);
    }

    @PutMapping
    public Produto modifyProduto(@RequestBody Produto produto){
        return service.updateProduto(produto);
    }

    @DeleteMapping("/{produtoId}")
    public String deleteProduto(@PathVariable String produtoId){
        return service.deleteProduto(produtoId);
    }
}
