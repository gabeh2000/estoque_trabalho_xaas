package com.xaas.estoque.service;

import com.xaas.estoque.model.Produto;
import com.xaas.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    //CRUD  CREATE , READ , UPDATE , DELETE


    public Produto addProduto(Produto produto) {
        produto.setProdutoId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(produto);
    }

    public List<Produto> findAllProdutos() {
        return repository.findAll();
    }

    public Produto getProdutoByProdutoId(String produtoId){
        return repository.findById(produtoId).get();
    }

    public List<Produto> getProdutoByQtd(int qtd){
        return  repository.findByQtd(qtd);
    }

    public List<Produto> getProdutoByNome(String nome){
        return repository.getProdutoByNome(nome);
    }

    public Produto updateProduto(Produto produtoRequest){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Produto existingProduto = repository.findById(produtoRequest.getProdutoId()).get();
        existingProduto.setNome(produtoRequest.getNome());
        existingProduto.setQtd(produtoRequest.getQtd());
        existingProduto.setPreco(produtoRequest.getPreco());
        return repository.save(existingProduto);
    }

    public String deleteProduto(String produtoId){
        repository.deleteById(produtoId);
        return produtoId+" produto deleted from dashboard ";
    }
}
