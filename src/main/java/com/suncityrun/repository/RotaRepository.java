package com.suncityrun.repository;


	import java.io.InputStream;
	import java.util.List;
	import java.util.Optional;

	import org.springframework.stereotype.Repository;

	import com.fasterxml.jackson.core.type.TypeReference;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.suncityrun.model.Rota;

	import jakarta.annotation.PostConstruct;

	@Repository
	public class RotaRepository {

	    private List<Rota> rotas;

	    @PostConstruct
	    public void init() {
	        try {
	            ObjectMapper mapper = new ObjectMapper();

	            InputStream inputStream = getClass()
	                .getResourceAsStream("/static/data/rotas.json");

	            this.rotas = mapper.readValue(inputStream, new TypeReference<List<Rota>>() {});
	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao carregar arquivo rotas.json", e);
	        }
	    }

	    public List<Rota> findAll() {
	        return rotas;
	    }

	    public Optional<Rota> findById(String id) {
	        return rotas.stream()
	            .filter(r -> r.getId().equals(id))
	            .findFirst();
	    }
	}

