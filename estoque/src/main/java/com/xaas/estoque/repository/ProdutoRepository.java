package com.xaas.estoque.repository;

import com.xaas.estoque.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto,String> {

    List<Produto> findByQtd(int qtd);

    @Query("{ nome: ?0 }")
    List<Produto> getProdutoByNome(String nome);
}
