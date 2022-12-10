package com.xaas.produtosapi.resources;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoResource {
    private Long id;
    private String nome;
    private int qtd;
    private float preco;

}
