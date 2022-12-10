package com.xaas.reserva.service;

import com.xaas.reserva.model.Reserva;
import com.xaas.reserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    //CRUD  CREATE , READ , UPDATE , DELETE


    public Reserva addReserva(Reserva reserva) {
        reserva.setReservaId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(reserva);
    }

    public List<Reserva> findAllReservas() {
        return repository.findAll();
    }

    public Reserva getReservaByReservaId(String reservaId){
        return repository.findById(reservaId).get();
    }

    public List<Reserva> getReservaByLimite(int limite){
        return  repository.findByLimite(limite);
    }

    public List<Reserva> getReservaByProduto(String produto){
        return repository.getReservaByProduto(produto);
    }

    public Reserva updateReserva(Reserva reservaRequest){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Reserva existingReserva = repository.findById(reservaRequest.getReservaId()).get();
        existingReserva.setLimite(reservaRequest.getLimite());
        existingReserva.setProduto(reservaRequest.getProduto());
        return repository.save(existingReserva);
    }

    public String deleteReserva(String reservaId){
        repository.deleteById(reservaId);
        return reservaId+" reserva deleted from dashboard ";
    }
}
