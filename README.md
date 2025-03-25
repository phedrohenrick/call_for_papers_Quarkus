# Documentação API 

## 📌Tecnologias Utilizadas
- **Quarkus**: Framework para Java utilizado para construir a API.
- **PostgreSQL**: Banco de dados utilizado, executado dentro de um container Docker na porta `5434`.
- **Docker**: Utilizado para containerizar o banco de dados PostgreSQL.
- **RestEasy**: Framework para a construção de endpoints RESTful em Quarkus.
- **Java 21**: Versão do Java utilizada no desenvolvimento da API.
- **GitHub**: Repositório utilizado para controle de versão do código.
- **Maven**: para gerenciar dependências e construir o projeto

## 📌Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:
- [Docker](https://www.docker.com/products/docker-desktop)
- [Java 21](https://jdk.java.net/21/)
- [Maven](https://maven.apache.org/install.html)

## Configuração do Ambiente

### 📌Passo 1: Clonando o repositório

Clone o repositório para o seu ambiente local:

```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
cd SEU_REPOSITORIO
```

### 📌Passo 2: Subindo o container do PostgreSQL com Docker
O arquivo (src/main/docker) docker-compose.yml está configurado para rodar um container do PostgreSQL na porta 5434. Para iniciar o banco de dados, entre na pasta do arquivo, e execute:

``` bash
docker-compose up -d
```
Isso iniciará o container do PostgreSQL e deixará o banco de dados disponível para conexão na porta 5434.

### 📌Passo 3: Configuração da conexão com o banco de dados
Verifique se as configurações de conexão com o banco de dados estão corretas no arquivo application.properties dentro do seu projeto Quarkus. A configuração deve ter as suas credenciais de conexão:

``` java
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=sua-senha
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5434/seu_banco
``` 

### 📌Passo 4: Construir e rodar a API com Quarkus
Execute o seguinte comando para compilar e rodar a API localmente em modo de desenvolvimento:

``` bash
mvn quarkus:dev
```
A API estará disponível por padrão em http://localhost:8080.

---

## 📌 Deploy na Nuvem  
A API foi implantada no **Railway**, ou seja, para tornar mais fácil o teste é possível visualizar todos os itens adicionados por mim. (mais informações sobre url de teste a baixo).

### 🚀 Infraestrutura
- **Backend:** Deploy do código Quarkus com o github.
- **Banco de Dados:** Um container do PostgreSQL configurado no próprio Railway, garantindo integração direta com a API.
- **Rede:** A API está acessível publicamente por meio de uma URL gerada pelo Railway.

### 🌐 URL da API
A API pode ser acessada publicamente através da seguinte URL:

```
https://seu-projeto-production.up.railway.app/rota_específica
```
---
## 📌 Endpoints da API  
### Criar um novo recurso  
**[POST]** `/api/paper/`  
```json
{
  "titulo": "Exemplo de Paper",
  "resumo": "Resumo do artigo",
  "nomeDoAutor": "Fulano de Tal",
  "email": "autor@email.com"
}
```

###  Listar todos os recursos  
**[GET]** `/api/paper/list`  

###  Buscar um recurso pelo ID  
**[GET]** `/api/paper/{id}`  

###  Atualizar um recurso  
**[PUT]** `/api/paper/{id}`  
```json
{
  "titulo": "Título atualizado",
  "resumo": "Resumo atualizado",
  "nomeDoAutor": "Fulano de Tal",
  "email": "autor@email.com"
}
```

###  Deletar um recurso  
**[DELETE]** `/api/paper/{id}`  

## 📌 Consumo pelo Frontend - Client 
Foi desenvolvido com **React.js** um client para consumos mais fácil e intuitivo da aplicação, de forma mais intuitiva e amigável ao usuário, pode ser usado para interação com ela;

Ele está disponível no seguinte repositório:  

🔗 ([DOWNLOAD DO FRONTEND](https://github.com/phedrohenrick/reactJS_desafioCFP_client))

Acesse para ter mais informações rápidas de download e de strat do frontend.

## 📌 Ambiente de Produção vs. Local  


| Ambiente  | URL de Acesso                        |
|-----------|--------------------------------------|
| **Local** | `http://localhost:8080`             |
| **Nuvem** | `https://hearty-patience-production.up.railway.app/rota_especifica` |

Caso queira testar sem precisar rodá-la localmente, basta usar um client (postman, por exemplo) e configurar com alguma dessas urls de conexão ou usar o proprio frontend local criado e já configurado para acesso remoto (da sessão acima);

---

## 📌 Autenticação 
Esta api ainda não possui configuração de autenticação.
---

## 📌 Licença  
Este projeto está licenciado sob a **MIT License**.  

---

📌 **Mantenha o README sempre atualizado com novas features!** 🚀

