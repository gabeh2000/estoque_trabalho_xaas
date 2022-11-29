package com.xaas.produtosapi.mapper;

import com.xaas.produtosapi.entities.ProdutoEntity;
import com.xaas.produtosapi.resources.ProdutoResource;

public final class ProdutoMapper {

    public static ProdutoResource convertToProdutoResource(ProdutoEntity produtoEntity) {
        return ProdutoResource
                .builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .qtd(produtoEntity.getQtd())
                .preco(produtoEntity.getPreco())
                .build();
    }

    public static ProdutoEntity convertToProdutoEntity(ProdutoResource produtoResource) {
        return ProdutoEntity
                .builder()
                .id(produtoResource.getId())
                .nome(produtoResource.getNome())
                .qtd(produtoResource.getQtd())
                .preco(produtoResource.getPreco())
                .build();
    }
}
