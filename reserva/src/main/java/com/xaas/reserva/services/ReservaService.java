package com.xaas.reservas.services;

import com.xaas.reservas.resources.ReservaResource;

import java.util.List;

public interface ReservaService {
    List<ReservaResource> getAllReservas();

    ReservaResource getReservaById(Long id);

   ReservaResource createReserva(ReservaResource reservaResource);

    void deleteReserva(Long id);

    ReservaResource updateReserva(ReservaResource reservaResource, Long id);
}
