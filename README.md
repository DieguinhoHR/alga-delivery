# 🚀 Curso Ignição Microsserviços - AlgaWorks

Bem-vindo(a) ao repositório do curso **Ignição Microsserviços** da [AlgaWorks](https://www.algaworks.com/)!  
Este projeto foi desenvolvido como parte do treinamento intensivo sobre **arquitetura de microsserviços**, **mensageria**, **resiliência**, e **observabilidade** em sistemas distribuídos.

## ⚙️ Como Executar o Projeto

### Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:
- [Java 17+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### Passos

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/<seu-usuario>/ignicao-microsservicos.git
   cd ignicao-microsservicos
   docker compose up -d
   ./mvnw spring-boot:run

Acessar os serviços

Gateway: http://localhost:8080

Eureka Server: http://localhost:8761

Config Server: http://localhost:8888
