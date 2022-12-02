package com.xaas.reservas.services;

import com.xaas.reservas.entities.ReservaEntity;
import com.xaas.reservas.exceptions.ResourceNotFoundException;
import com.xaas.reservas.mapper.ReservaMapper;
import com.xaas.reservas.repositories.ReservaRepository;
import com.xaas.reservas.resources.ReservaResource;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DefaultReservaService implements ReservaService {

    //List<ReservaResource> todosReservas = new ArrayList();
    private final ReservaRepository reservaRepository;

    @Override
    public List<ReservaResource> getAllReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(ReservaMapper::convertToReservaResource)
                .collect(toList());
    }

    @Override
    public ReservaResource getReservaById(Long id) {
        ReservaEntity reservaEntity = reservaRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));

        return ReservaMapper.convertToReservaResource(reservaEntity);
    }

    @Override
    public ReservaResource createReserva(ReservaResource reservaResource) {
      requireNonNull(reservaResource);

      ReservaEntity reservaEntity = ReservaMapper.convertToReservaEntity(reservaResource);

      return ReservaMapper.convertToReservaResource(reservaRepository.save(reservaEntity));
    }

    @Override
    public void deleteReserva(Long id) {
        try{
            reservaRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }

    }

    @Override
    public ReservaResource updateReserva(ReservaResource reservaResource, Long id) {
        requireNonNull(reservaResource);

        ReservaEntity reservaEntity = reservaRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));

        reservaEntity = ReservaMapper.convertToReservaEntity(reservaResource);
        reservaEntity.setId(id);
        //reservaEntity.setNome()

        return ReservaMapper.convertToReservaResource(reservaRepository.save(reservaEntity));
    }


}
