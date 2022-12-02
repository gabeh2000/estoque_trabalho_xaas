package com.xaas.reservas.controllers;

import com.xaas.reservas.resources.ReservaResource;
import com.xaas.reservas.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaResource>> getAllReservas() {
        return ResponseEntity.ok(reservaService.getAllReservas());
    }

    @PostMapping
    public ResponseEntity<ReservaResource> createReserva(@RequestBody ReservaResource reservaResource) {
        return new ResponseEntity<>(reservaService.createReserva(reservaResource), CREATED);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteReserva(@PathVariable("id") Long id){
        reservaService.deleteReserva(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReservaResource> updateReserva(@RequestBody ReservaResource reservaResource, @PathVariable Long id) {
        return ResponseEntity.ok(reservaService.updateReserva(reservaResource, id));
    }
}
