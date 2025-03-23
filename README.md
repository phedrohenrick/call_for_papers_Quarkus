# API Documentation

## Tecnologias Utilizadas
- **Quarkus**: Framework para Java utilizado para construir a API.
- **PostgreSQL**: Banco de dados utilizado, executado dentro de um container Docker na porta `5434`.
- **Docker**: Utilizado para containerizar o banco de dados PostgreSQL.
- **RestEasy**: Framework para a construção de endpoints RESTful em Quarkus.
- **Java 21**: Versão do Java utilizada no desenvolvimento da API.
- **GitHub**: Repositório utilizado para controle de versão do código.
- **Maven**: para gerenciar dependências e construir o projeto

## Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:
- [Docker](https://www.docker.com/products/docker-desktop)
- [Java 21](https://jdk.java.net/21/)
- [Maven](https://maven.apache.org/)  para gerenciar dependências e construir o projeto

## Configuração do Ambiente

### Passo 1: Clonando o repositório

Clone o repositório para o seu ambiente local:

```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
cd SEU_REPOSITORIO
```

### Passo 2: Subindo o container do PostgreSQL com Docker
O arquivo docker-compose.yml está configurado para rodar um container do PostgreSQL na porta 5434. Para iniciar o banco de dados, entre na pasta do arquivo, e execute:

``` bash
docker-compose up -d
```
Isso iniciará o container do PostgreSQL e deixará o banco de dados disponível para conexão na porta 5434.

### Passo 3: Configuração da conexão com o banco de dados
Verifique se as configurações de conexão com o banco de dados estão corretas no arquivo application.properties dentro do seu projeto Quarkus. A configuração deve ter as suas credenciais de conexão:

``` java
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=sua-senha
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5434/seu_banco
``` 

### Passo 4: Construir e rodar a API com Quarkus
Execute o seguinte comando para compilar e rodar a API localmente em modo de desenvolvimento:

``` bash
mvn quarkus:dev
```
A API estará disponível por padrão em http://localhost:8080.
