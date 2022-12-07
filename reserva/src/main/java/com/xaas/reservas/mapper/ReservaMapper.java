package com.xaas.reservas.mapper;

import com.xaas.reservas.entities.ReservaEntity;
import com.xaas.reservas.resources.ReservaResource;

public final class ReservaMapper {

    public static ReservaResource convertToReservaResource(ReservaEntity reservaEntity) {
        return ReservaResource
                .builder()
                .id(reservaEntity.getId())
                .produto(reservaEntity.getProduto())
                .limite(reservaEntity.getLimite())
                .qtd_reservada(reservaEntity.getQtd_reservada())
                .build();
    }

    public static ReservaEntity convertToReservaEntity(ReservaResource reservaResource) {
        return ReservaEntity
                .builder()
                .id(reservaResource.getId())
                .produto(reservaResource.getProduto())
                .limite(reservaResource.getLimite())
                .qtd_reservada(reservaResource.getQtd_reservada())
                .build();
    }
}
