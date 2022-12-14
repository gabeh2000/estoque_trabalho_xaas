package com.xaas.produtosapi.repositories;

import com.xaas.produtosapi.entities.ProdutoEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoEntity, Long> {
}