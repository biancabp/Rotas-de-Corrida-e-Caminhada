# SunCity Run API

**API REST para rotas de corrida e caminhada em Natal/RN**  
Projeto desenvolvido para a disciplina **IMD0040 – Linguagem de Programação II**  
**Caminho A** – API robusta com foco em Backend  
UFRN / Instituto Metrópole Digital – 2025.2

**Alunas**  
- Bianca Bezerra Pires – 20240020515  
- Maria Clara Silva Brito – 2024002780

---

### Descrição do Projeto
SunCity Run é uma API que centraliza informações de rotas reais de corrida e caminhada em Natal/RN, permitindo consulta por ID e (em breve) filtros por dificuldade, distância e segurança.

### Tecnologias
- Java 17
- Spring Boot 4.0.0
- Maven
- Springdoc OpenAPI (Swagger UI)
- Persistência em arquivo JSON local (`src/main/resources/data/rotas.json`)

### Estrutura de Camadas (já implementada)
com.suncityrun
├── model        → Rota.java
├── repository   → RotaRepository.java (@Repository + @PostConstruct)
├── service      → RotaService.java
├── controller   → RouteController.java (@RestController /rotas)
├── exception    → GlobalExceptionHandler.java (@ControllerAdvice)
└── suncityrun_api → SuncityrunApiApplication.java

### Como Executar
```bash
./mvnw spring-boot:run        # Linux/Mac
# ou
.\mvnw.cmd spring-boot:run    # Windows PowerShell

## 📅 Próximos Passos

- [ ] Filtros: `?dificuldade=` e `?maxDistancia=`
- [ ] Validação com `@Valid` + mensagens de erro
- [ ] Adição de **15+ rotas reais** (Ponta Negra, Dunas, Via Costeira etc.)
- [ ] Endpoint de **recomendação de rotas seguras**
- [ ] Testes unitários e de integração
- [ ] Deploy na nuvem

## ✅ Status Checkpoint 1 (23/11/2025)

- ✔ Estrutura em camadas completa
- ✔ API rodando localmente
- ✔ Endpoints GET funcionando
- ✔ Tratamento global de exceções
- ✔ Swagger UI 100% funcional
- ✔ JSON carregado na inicialização
