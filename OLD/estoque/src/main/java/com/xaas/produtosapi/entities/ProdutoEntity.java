package com.xaas.produtosapi.entities;

import lombok.*;

import javax.persistence.*;

//import org.springframework.data.annotation.Id;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String nome;
    private int qtd;
    private float preco;
}
