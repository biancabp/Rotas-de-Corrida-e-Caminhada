package com.suncityrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @GetMapping("/rotas")
    public String listarRotas() {
        return "API funcionando! (Esse é seu primeiro endpoint)";
    }
}
