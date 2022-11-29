package com.xaas.produtosapi.services;

import com.xaas.produtosapi.entities.ProdutoEntity;
import com.xaas.produtosapi.exceptions.ResourceNotFoundException;
import com.xaas.produtosapi.mapper.ProdutoMapper;
import com.xaas.produtosapi.repositories.ProdutoRepository;
import com.xaas.produtosapi.resources.ProdutoResource;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DefaultProdutoService implements ProdutoService {

    //List<ProdutoResource> todosProdutos = new ArrayList();
    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoResource> getAllProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoMapper::convertToProdutoResource)
                .collect(toList());
    }

    @Override
    public ProdutoResource getProdutoById(Long id) {
        ProdutoEntity produtoEntity = produtoRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));

        return ProdutoMapper.convertToProdutoResource(produtoEntity);
    }

    @Override
    public ProdutoResource createProduto(ProdutoResource produtoResource) {
      requireNonNull(produtoResource);

      ProdutoEntity produtoEntity = ProdutoMapper.convertToProdutoEntity(produtoResource);

      return ProdutoMapper.convertToProdutoResource(produtoRepository.save(produtoEntity));
    }

    @Override
    public void deleteProduto(Long id) {
        try{
            produtoRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }

    }

    @Override
    public ProdutoResource updateProduto(ProdutoResource produtoResource, Long id) {
        requireNonNull(produtoResource);

        ProdutoEntity produtoEntity = produtoRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));

        produtoEntity = ProdutoMapper.convertToProdutoEntity(produtoResource);
        produtoEntity.setId(id);
        //produtoEntity.setNome()

        return ProdutoMapper.convertToProdutoResource(produtoRepository.save(produtoEntity));
    }


}
