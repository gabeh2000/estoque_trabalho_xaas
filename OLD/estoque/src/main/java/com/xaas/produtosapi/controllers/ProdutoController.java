package com.xaas.produtosapi.controllers;

import com.xaas.produtosapi.resources.ProdutoResource;
import com.xaas.produtosapi.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResource>> getAllProdutos() {
        return ResponseEntity.ok(produtoService.getAllProdutos());
    }

    @PostMapping
    public ResponseEntity<ProdutoResource> createProduto(@RequestBody ProdutoResource produtoResource) {
        return new ResponseEntity<>(produtoService.createProduto(produtoResource), CREATED);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduto(@PathVariable("id") Long id){
        produtoService.deleteProduto(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResource> updateProduto(@RequestBody ProdutoResource produtoResource, @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.updateProduto(produtoResource, id));
    }
}
