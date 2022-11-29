package com.xaas.produtosapi.services;

import com.xaas.produtosapi.resources.ProdutoResource;

import java.util.List;

public interface ProdutoService {
    List<ProdutoResource> getAllProdutos();

    ProdutoResource getProdutoById(Long id);

   ProdutoResource createProduto(ProdutoResource produtoResource);

    void deleteProduto(Long id);

    ProdutoResource updateProduto(ProdutoResource produtoResource, Long id);
}
