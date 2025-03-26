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
e abra na IDE De sua preferência (IntelliJ, VsCode, Eclipse), abra a pasta do projeto (/call-for-papers);

### 📌Passo 2: Subindo o container do PostgreSQL com Docker
O arquivo docker-compose.yml (src/main/docker/docker-compose.yml) está configurado para rodar um container do PostgreSQL na porta 5434. Para iniciar o banco de dados, entre na pasta onde está o arquivo, e execute:

``` bash
docker-compose up -d
```
Isso iniciará o container do PostgreSQL e deixará o banco de dados disponível para conexão na porta 5434.

### 📌Passo 3: Configuração da conexão com o banco de dados
Verifique se as configurações de conexão com o banco de dados estão corretas no arquivo application.properties dentro do seu projeto Quarkus. A configuração deve ter as suas credenciais de conexão (no caso do banco de dados do docker-file é o "callforpaperdb"):

``` java
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=sua-senha
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5434/callforpaperdb
``` 

### 📌Passo 4: Construir e rodar a API com Quarkus
Execute o seguinte comando para compilar e rodar a API localmente em modo de desenvolvimento:

``` bash
mvn quarkus:dev
```
A API estará disponível por padrão em http://localhost:8080.

---

## 📌 Deploy na Nuvem  
A API foi implantada no Railway  (Por simplicidade de testes, é claro). Para facilitar o teste de funcionamento, você pode enviar requisições diretamente pelas rotas específicas listadas abaixo, acessando os endpoints por uma ferramenta como postman. Alternativamente, também é possível baixar o código frontend, oferecendo uma interface mais amigável e intuitiva para interação com a API.

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

## 📌 Como testar a aplicação? (exemplo de submissão)
Foi desenvolvido com **React.js** um client para consumos mais fácil e intuitivo da aplicação, de forma mais intuitiva e amigável ao usuário, pode ser usado para interação com ela;

![Exemplo client](https://github.com/user-attachments/assets/cfc005cd-504a-4a66-bae7-0a9df34a785a)

Para ter mais informações rápidas de download e de strat do frontend, acesse:

🔗 ([DOWNLOAD DO FRONTEND](https://github.com/phedrohenrick/reactJS_desafioCFP_client))

Ao rodar o frontend localmente, ele estará configrado para acessar a aplicação que esta em produção na nuvem. Ou seja ele concta automaticamente com  `https://hearty-patience-production.up.railway.app/<rota_especifica>` e esta pronto para testes.

- **Uma outra Opção**:
 Usar uma ferramenta client como o [postman](https://www.postman.com/) ou [insomnia](https://insomnia.rest/) para Enviar requisições HTTP (GET, POST, PUT, DELETE), Configurar cabeçalhos, parâmetros e corpo da requisição, testar respostas da API e validar os dados retornados.

! Lembrando que nesse caso é necessário configurar o arquivo application.properties para se conectar ao banco de dados que estar rodando em docker com postgres.

| Ambiente  | URL de Acesso                        |
|-----------|--------------------------------------|
| **Local** | `http://localhost:8080`             |
| **Nuvem** | `https://hearty-patience-production.up.railway.app/<rota_especifica>` |


## 📌 Autenticação 
Esta api ainda não possui configuração de autenticação.

## 🌟 Conheça Meu Portfólio

Convido você a conhecer mais sobre meus  outros projetos que desenvolvi. Acesse meu portfólio e veja minhas criações!

🔗[Meu Portfólio](https://www.phedrohenrick-portifolio.com.br/)

Fique à vontade para explorar, dar feedbacks e entrar em contato! 🚀
