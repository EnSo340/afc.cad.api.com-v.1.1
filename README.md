:

📚 CRUD de Alunos

Este projeto é uma API REST desenvolvida em Spring Boot para gerenciamento de alunos.
A aplicação permite cadastrar, listar, atualizar e deletar alunos, além de integrar informações como endereço, notas, faltas e sistema de aprovação/reprovação por presença.

🚀 Tecnologias Utilizadas

Java 17+

Spring Boot (Web, Data JPA, Validation)

Hibernate

Flyway (migrations do banco)

MySQL (banco de dados relacional)

Lombok (redução de boilerplate code)

⚙️ Funcionalidades

Cadastrar Aluno

Listar Alunos

Atualizar Aluno

Excluir Aluno

Controle de Faltas e Aprovação/Reprovação

Registro de Notas

📂 Estrutura do Projeto
src/main/java/cad/afc/cad/api/
 ├── aluno/        # Entidade, DTOs e regras de aluno
 ├── endereco/     # Endereço embutido no aluno
 ├── faltas/       # Entidade e controle de faltas
 ├── notas/        # Entidade e controle de notas
 ├── boletins/     # Estrutura de boletins e tipos de ensino
 └── controller/   # Controllers REST

🔗 Endpoints
👤 Alunos

POST /alunos → Cadastrar aluno

GET /alunos → Listar alunos

PUT /alunos/{id} → Atualizar aluno

DELETE /alunos/{id} → Deletar aluno

(Exemplo com Insomnia/Postman)

Cadastro de aluno (POST)
{
  "nome": "João Silva",
  "anoDeNasc": 2005,
  "serieAtual": "2º ano",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "cpf": "123.456.789-00",
  "endereco": {
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01000-000",
    "numero": "123"
  }
}

Atualização de aluno (PUT)
{
  "nome": "João Pedro Silva",
  "serieAtual": "3º ano",
  "telefone": "11888888888"
}

🛠️ Como Rodar o Projeto

Clone este repositório:

git clone https://github.com/SEU_USUARIO/SEU_REPO.git


Acesse a pasta do projeto:

cd SEU_REPO


Configure o banco MySQL no arquivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/suabase
spring.datasource.username=seuusuario
spring.datasource.password=suasenha
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true


Rode a aplicação:

./mvnw spring-boot:run

📌 Futuras Melhorias

Implementação de ResponseEntity para respostas mais detalhadas

Adicionar autenticação e autorização com Spring Security

Criar uma interface frontend em React para consumo da API
