package com.suncityrun.service;

import java.util.Comparator;
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

    // ------------------- FILTRO GERAL -------------------
    public List<Rota> filtrar(String dificuldade, Double min, Double max) {
        return rotaRepository.findAll().stream()

                .filter(r -> dificuldade == null ||
                        (r.getDificuldade() != null &&
                        r.getDificuldade().equalsIgnoreCase(dificuldade)))

                .filter(r -> min == null || r.getDistancia() >= min)

                .filter(r -> max == null || r.getDistancia() <= max)

                .toList();
    }

    // ------------------- BUSCAS ESPECÍFICAS -------------------

    public List<Rota> buscarPorDistanciaMinima(double min) {
        return rotaRepository.findAll()
                .stream()
                .filter(r -> r.getDistancia() >= min)
                .toList();
    }

    public List<Rota> buscarPorDistanciaMaxima(double max) {
        return rotaRepository.findAll()
                .stream()
                .filter(r -> r.getDistancia() <= max)
                .toList();
    }

    // >>> corrigido para evitar NullPointerException <<<
    public List<Rota> buscarPorTerreno(String tipo) {
        return rotaRepository.findAll()
                .stream()
                .filter(r -> r.getTerreno() != null &&
                             r.getTerreno().equalsIgnoreCase(tipo))
                .toList();
    }

    public List<Rota> buscarPorDificuldade(String dificuldade) {
        return rotaRepository.findAll()
                .stream()
                .filter(r -> r.getDificuldade() != null &&
                             r.getDificuldade().equalsIgnoreCase(dificuldade))
                .toList();
    }

    // >>> corrigido para evitar NullPointerException <<<
    public List<Rota> buscarPorLocalizacao(String bairro) {
        return rotaRepository.findAll()
                .stream()
                .filter(r -> r.getLocalizacao() != null &&
                             r.getLocalizacao().equalsIgnoreCase(bairro))
                .toList();
    }

    public List<String> listarSomenteNomes() {
        return rotaRepository.findAll()
                .stream()
                .map(Rota::getNome)
                .toList();
    }

    public long contarRotas() {
        return rotaRepository.findAll().size();
    }

    public List<Rota> ordenarPorDistancia() {
        return rotaRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Rota::getDistancia))
                .toList();
    }

    public List<Rota> buscarPorIntervalo(double min, double max) {
        return rotaRepository.findAll()
                .stream()
                .filter(r -> r.getDistancia() >= min && r.getDistancia() <= max)
                .toList();
    }

    // Retorna rotas "seguras" (ex: dificuldade fácil e terreno corrida)
   public List<Rota> recomendarRotasSeguras() {
    return rotaRepository.findAll()
            .stream()
            .filter(r -> "facil".equalsIgnoreCase(r.getDificuldade()))
            .filter(r -> "corrida".equalsIgnoreCase(r.getTerreno()))
            .toList();
}

}
