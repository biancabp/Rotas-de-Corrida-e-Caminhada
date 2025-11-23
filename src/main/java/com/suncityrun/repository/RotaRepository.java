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
    ObjectMapper mapper = new ObjectMapper();
    try (InputStream is = getClass().getResourceAsStream("/data/rotas.json")) {
        if (is == null) {
            throw new RuntimeException("Arquivo rotas.json não encontrado! Verifique o caminho: src/main/resources/data/rotas.json");
        }
        this.rotas = mapper.readValue(is, new TypeReference<List<Rota>>() {});
        System.out.println("Carregadas " + rotas.size() + " rotas com sucesso!");
    } catch (Exception e) {
        throw new RuntimeException("Erro crítico ao carregar rotas.json", e);
    }
}
	    public List<Rota> findAll() {
	        return rotas;
	    }

		public Optional<Rota> findById(String id) {
			return rotas.stream()
				.filter(r -> java.util.Objects.equals(r.getId(), id))
				.findFirst();
		}
	}

