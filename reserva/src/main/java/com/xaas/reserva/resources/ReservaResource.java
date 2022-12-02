package com.xaas.reserva.resources;

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
}
