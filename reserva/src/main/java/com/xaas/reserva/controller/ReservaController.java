package com.xaas.reserva.controller;

import com.xaas.reserva.model.Reserva;
import com.xaas.reserva.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva createReserva(@RequestBody Reserva reserva){
        return service.addReserva(reserva);
    }

    @GetMapping
    public List<Reserva> getReservas() {
        return service.findAllReservas();
    }


    @GetMapping("/{reservaId}")
    public Reserva getReserva(@PathVariable String reservaId){
        return service.getReservaByReservaId(reservaId);
    }

    @GetMapping("/limite/{limite}")
    public List<Reserva> findReservaUsingLimite(@PathVariable int limite){
        return service.getReservaByLimite(limite);
    }

    @GetMapping("/produto/{produto}")
    public List<Reserva> getReservaByProduto(@PathVariable String produto){
        return service.getReservaByProduto(produto);
    }

    @PutMapping
    public Reserva modifyReserva(@RequestBody Reserva reserva){
        return service.updateReserva(reserva);
    }

    @DeleteMapping("/{reservaId}")
    public String deleteReserva(@PathVariable String reservaId){
        return service.deleteReserva(reservaId);
    }
}
