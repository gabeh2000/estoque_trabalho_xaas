package com.xaas.reservas.resources;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservaResource {
    private Long id;
    private String produto;
    private int limite;
    private int qtd_reservada;

}
