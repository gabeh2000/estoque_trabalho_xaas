package com.xaas.estoque.controller;

import com.xaas.estoque.model.Produto;
import com.xaas.estoque.service.ProdutoService;
import com.xaas.estoque.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

	@Autowired
	private RabbitTemplate rabbitTemplate;
    
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
        produto=service.updateProduto(produto);

		OrderCreatedEvent event = new OrderCreatedEvent(produto.getProdutoId(), produto.getQtd());

		rabbitTemplate.convertAndSend("produtos.v1.produto-updated", "", event);        
        return produto;
    }

    @DeleteMapping("/{produtoId}")
    public String deleteProduto(@PathVariable String produtoId){
        return service.deleteProduto(produtoId);
    }
}
