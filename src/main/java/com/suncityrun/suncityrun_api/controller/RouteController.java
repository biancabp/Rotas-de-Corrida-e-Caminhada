package com.suncityrun.suncityrun_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Rota> listarRotas() {
        return rotaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rota> getRotaPorId(@PathVariable String id) {
        return rotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}