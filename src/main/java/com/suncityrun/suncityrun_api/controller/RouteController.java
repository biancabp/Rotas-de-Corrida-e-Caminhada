package com.suncityrun.suncityrun_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import com.suncityrun.model.Rota;
import com.suncityrun.service.RotaService;


    @RestController
    @RequestMapping("/rotas")
    public class RouteController {

    private final RotaService rotaService;

    public RouteController(RotaService rotaService) {
        this.rotaService = rotaService;
    }
    @GetMapping
    public ResponseEntity<List<Rota>> listarRotas(
       
        @RequestParam(required = false) String dificuldade,
        @RequestParam(required = false) Double min,
        @RequestParam(required = false) Double max) {

     //Filtro por dificuldade       
    if (dificuldade != null) {
        List<Rota> filtradas = rotaService.findByDificuldade(dificuldade);
        return ResponseEntity.ok(filtradas);
    }

    if( min != null || max != null) {
        List<Rota> filtradas = rotaService.findByDistancia(min, max);
        return ResponseEntity.ok(filtradas);
    }

    return ResponseEntity.ok(rotaService.findAll());
    }  

    @GetMapping("/{id}")
    public ResponseEntity<Rota> getRotaPorId(@PathVariable String id) {
        return rotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Rota> criarRota(
        @Valid @RequestBody Rota rota) {
    return ResponseEntity.ok(rota);
}

}