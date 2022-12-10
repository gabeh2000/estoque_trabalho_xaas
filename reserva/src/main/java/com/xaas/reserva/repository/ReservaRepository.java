package com.xaas.reserva.repository;

import com.xaas.reserva.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva,String> {

    List<Reserva> findByLimite(int limite);

    @Query("{ produto: ?0 }")
    List<Reserva> getReservaByProduto(String produto);
}
