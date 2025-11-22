package com.suncityrun.suncityrun_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.suncityrun.model.Rota;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@RestController
public class RouteController {

    @GetMapping("/rotas")
    public List<Rota> listarRotas() throws IOException {
        ClassPathResource resource = new ClassPathResource("data/rotas.json");
        InputStream inputStream = resource.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, new TypeReference<List<Rota>>() {});
    }
}