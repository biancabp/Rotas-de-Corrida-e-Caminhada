package com.suncityrun.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.suncityrun.model.Rota;
import com.suncityrun.repository.RotaRepository;

@Service
public class RotaService {

    private final RotaRepository rotaRepository;

    public RotaService(RotaRepository rotaRepository) {
        this.rotaRepository = rotaRepository;
    }

    public List<Rota> findAll() {
        return rotaRepository.findAll();
    }

    public Optional<Rota> findById(String id) {
        return rotaRepository.findById(id);
    }

    public List<Rota> findByDificuldade(String dificuldade) {
    return rotaRepository.findAll().stream()
            .filter(r -> r.getDificuldade() != null &&
                         r.getDificuldade().equalsIgnoreCase(dificuldade))
            .toList();
}

    public List<Rota> findByDistancia(Double min, Double max) {
    return rotaRepository.findAll().stream()
        .filter(r -> {
            double d = r.getDistancia();

            boolean maiorQueMin = (min == null) || d >= min;
            boolean menorQueMax = (max == null) || d <= max;

            return maiorQueMin && menorQueMax;
        })
        .toList();
    }
}
