package com.suncityrun.suncityrun_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suncityrun.model.Rota;
import com.suncityrun.service.RotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rotas")
public class RouteController {

    private final RotaService rotaService;

    public RouteController(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    // LISTAR + FILTROS
    @GetMapping
    public ResponseEntity<List<Rota>> listarRotas(
            @RequestParam(required = false) String dificuldade,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max) {

        List<Rota> filtradas = rotaService.filtrar(dificuldade, min, max);
        return ResponseEntity.ok(filtradas);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Rota> getRotaPorId(@PathVariable String id) {
        return rotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CRIAR (mock)
    @PostMapping
    public ResponseEntity<Rota> criarRota(@Valid @RequestBody Rota rota) {
        return ResponseEntity.ok(rota);
    }

    // 1 - DISTÂNCIA MIN
    @GetMapping("/distancia/min/{min}")
    public List<Rota> getByDistanciaMinima(@PathVariable double min) {
        return rotaService.buscarPorDistanciaMinima(min);
    }

    // 2 - DISTÂNCIA MAX
    @GetMapping("/distancia/max/{max}")
    public List<Rota> getByDistanciaMaxima(@PathVariable double max) {
        return rotaService.buscarPorDistanciaMaxima(max);
    }

    // ❗ 3 - FILTRAR POR TERRENO (CORRIGIDO)
    @GetMapping("/terreno/{terreno}")
    public List<Rota> getByTerreno(@PathVariable String terreno) {
        return rotaService.buscarPorTerreno(terreno);
    }

    // 4 - FILTRAR POR DIFICULDADE
    @GetMapping("/dificuldade/{dificuldade}")
    public List<Rota> getByDificuldade(@PathVariable String dificuldade) {
        return rotaService.buscarPorDificuldade(dificuldade);
    }

    // ❗ 5 - FILTRAR POR LOCALIZAÇÃO (CORRIGIDO)
    @GetMapping("/localizacao/{localizacao}")
    public List<Rota> getByLocalizacao(@PathVariable String localizacao) {
        return rotaService.buscarPorLocalizacao(localizacao);
    }

    // 6 - SOMENTE NOMES
    @GetMapping("/nomes")
    public List<String> listarNomes() {
        return rotaService.listarSomenteNomes();
    }

    // 7 - CONTAR
    @GetMapping("/count")
    public long contarRotas() {
        return rotaService.contarRotas();
    }

    // 8 - ORDENAR POR DISTÂNCIA
    @GetMapping("/ordenar/distancia")
    public List<Rota> ordenarPorDistancia() {
        return rotaService.ordenarPorDistancia();
    }


    // 9 - INTERVALO
    @GetMapping("/distancia/intervalo")
    public List<Rota> buscarPorIntervalo(
            @RequestParam double min,
            @RequestParam double max) {
        return rotaService.buscarPorIntervalo(min, max);
    }

    // Endpoint de recomendação de rotas seguras
@GetMapping("/recomendadas")
public List<Rota> rotasSeguras(
        @RequestParam(required = false) String dificuldade,
        @RequestParam(required = false) String terreno,
        @RequestParam(required = false) Double min,
        @RequestParam(required = false) Double max) {

    return rotaService.findAll().stream()
            // se o usuário não passar dificuldade, recomendação padrão = "Fácil"
            .filter(r -> dificuldade != null ? 
                         r.getDificuldade().equalsIgnoreCase(dificuldade) :
                         r.getDificuldade().equalsIgnoreCase("Fácil"))
            // se não passar terreno, recomendação padrão = "Asfalto" ou "Trilha em terra batida"
            .filter(r -> terreno != null ? 
                         r.getTerreno().equalsIgnoreCase(terreno) :
                         r.getTerreno().equalsIgnoreCase("Asfalto") || r.getTerreno().equalsIgnoreCase("Trilha em terra batida"))
            .filter(r -> min == null || r.getDistancia() >= min)
            .filter(r -> max == null || r.getDistancia() <= max)
            .toList();
}

}
