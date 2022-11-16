package com.teste.estoque.entities;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Produto {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String nome;
    private int quantidade;
    private float preco;

}
