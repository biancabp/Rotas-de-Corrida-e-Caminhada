# Suncityrun API
# Suncityrun API

API simples para listar rotas de corrida/caminhada.

Requisitos:
- Java 17
- Maven (ou usar o Maven Wrapper `mvnw`)

Build e execução:

```powershell
cd "c:/Users/adlin/OneDrive/Desktop/prog bia/Rotas-de-Corrida-e-Caminhada"
.\mvnw.cmd -B -DskipTests=false test
.\mvnw.cmd -B package
.\mvnw.cmd spring-boot:run
# ou
java -jar target/suncityrun-api-0.0.1-SNAPSHOT.jar
```

Localhost: http://localhost:8082/rotas

Endpoints principais:
- `GET /rotas` — lista todas as rotas (JSON)
- `GET /rotas/{id}` — retorna uma rota por id (404 se não encontrar)

O que foi corrigido e adicionado:
- `Rota`: `id` como `String` para compatibilidade com o JSON atual.
- `RotaRepository`: corrigido caminho do recurso para `classpath:/data/rotas.json`, tratamento explícito de IO e fechamento do stream.
- `RotaService`: nova camada de serviço `com.suncityrun.service.RotaService` que delega para o repository.
- `RouteController`: agora injeta `RotaService` e fornece `GET /rotas` e `GET /rotas/{id}`.
- `GlobalExceptionHandler`: `@ControllerAdvice` para respostas JSON consistentes em erros.
- `pom.xml`: padronizado para usar `spring-boot-starter-test` nos testes.
- CI: workflow GitHub Actions em `.github/workflows/ci.yml` para rodar `./mvnw test package`.
- Testes: adicionado `RouteControllerTest` usando MockMvc.

Próximos passos recomendados:
- Melhorar tratamento de exceções e capturar erros específicos em pontos críticos.
- Adicionar camada de validação (`@Valid`, DTOs) e testes unitários para o service.
- Adicionar Checkstyle/SpotBugs e integrar no CI.
