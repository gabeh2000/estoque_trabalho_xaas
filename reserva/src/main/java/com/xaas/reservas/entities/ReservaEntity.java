package com.xaas.reservas.entities;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reserva")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String produto;
    private int limite;
}
