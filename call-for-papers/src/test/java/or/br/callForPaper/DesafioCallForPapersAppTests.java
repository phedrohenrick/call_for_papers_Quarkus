package or.br.callForPaper;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.br.callForPaper.dto.PaperDetailsDTO;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@QuarkusTest
class DesafioCallForPapersAppTests {
    private static final Logger log = LoggerFactory.getLogger(DesafioCallForPapersAppTests.class);
     Long paperId;

    @Test
    void testCreatePaperSuccess() {
        PaperDetailsDTO paper = new PaperDetailsDTO( "titulo", "resumo", "nomeDoAutor", "email@email");

        given()
                .contentType(ContentType.JSON)
                .body(paper)
                .when().post("/api/paper/")
                .then()
                .log().body()
                .statusCode(201)
                .body("titulo", equalTo(paper.getTitulo()))
                .body("resumo", equalTo(paper.getResumo()))
                .body("nomeDoAutor", equalTo(paper.getNomeDoAutor()))
                .body("email", equalTo(paper.getEmail()));
    }

    @Test
    public void testCreatePaperFailure_MissingFields() {

        PaperDetailsDTO invalidPaper = new PaperDetailsDTO("", "", "", "");

        given()
                .contentType(ContentType.JSON)
                .body(invalidPaper)
                .when().post("/api/paper/")
                .then()
                .log().body()
                .statusCode(400)
                .body("parameterViolations.size()", greaterThan(0))
                .body("parameterViolations.find { it.path == 'createPaper.paperDetailsDTO.titulo' }.message", equalTo("o conteúdo é obrigatório"))
                .body("parameterViolations.find { it.path == 'createPaper.paperDetailsDTO.resumo' }.message", equalTo("o conteúdo é obrigatório"))
                .body("parameterViolations.find { it.path == 'createPaper.paperDetailsDTO.nomeDoAutor' }.message", equalTo("o conteúdo é obrigatório"))
                .body("parameterViolations.find { it.path == 'createPaper.paperDetailsDTO.email' }.message", equalTo("o conteúdo é obrigatório"));
    }

    @BeforeEach
    void setup() {
        // Criando um paper antes do teste
        PaperDetailsDTO paper = new PaperDetailsDTO(null, "Título Original", "Resumo Original", "Autor Original", "autor@email.com");
        PaperDetailsDTO paper2 = new PaperDetailsDTO(null, "Título Original2", "Resumo Original2", "Autor Original2", "autor2@email.com");

        paperId = given()
                .contentType(ContentType.JSON)
                .body(paper)
                .when().post("/api/paper/")
                .then()
                .log().body()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        given()
                .contentType(ContentType.JSON)
                .body(paper2)
                .when().post("/api/paper/")
                .then()
                .log().body()
                .statusCode(201);


        log.info(String.valueOf(paperId));

    }


    @Test
    public void testUpdatePaperSuccess() {
        PaperDetailsDTO updatedPaper = new PaperDetailsDTO(paperId, "Novo Título", "Novo Resumo", "Autor Original", "autor@email.com");

        given()
                .contentType(ContentType.JSON)
                .body(updatedPaper)
                .when()
                .put("/api/paper/update/" + paperId)
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Novo Título"))
                .body("resumo", equalTo("Novo Resumo"))
                .body("nomeDoAutor", equalTo("Autor Original"))
                .body("email", equalTo("autor@email.com"));


    }

    @Test
    public void testUpdatePaperFailure_UnrecognizedPaper() {
        Long invalidId = 9999L;

        PaperDetailsDTO updatedPaper = new PaperDetailsDTO(paperId, "Novo Título", "Novo Resumo", "Autor Original", "autor@email.com");

        given()
                .contentType(ContentType.JSON)
                .body(updatedPaper)
                .when()
                .put("/api/paper/update/" + invalidId)
                .then()
                .statusCode(404)
                .body(equalTo("A submissão que deseja editar não existe"));
    }

    @Test
    public void testDeletePaperSuccess() {
        // Criar um novo paper para garantir que o ID existe
        PaperDetailsDTO paper = new PaperDetailsDTO(null, "Título para Deletar", "Resumo", "Autor", "autor@email.com");

        Long paperIdToDelete = given()
                .contentType(ContentType.JSON)
                .body(paper)
                .when()
                .post("/api/paper")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");


        given()
                .when()
                .delete("/api/paper/" + paperIdToDelete)
                .then()
                .statusCode(204); // Espera um retorno 204 No Content

    }

    @Test
    public void testDeletePaperFailure_UnrecognizedId() {
        Long paperUnrecognizedId = 999L;


        given()
                .when()
                .delete("/api/paper/" + paperUnrecognizedId)
                .then()
                .statusCode(404);
    }

    @Test
    public void testlistPapers(){

        given()
                .when()
                .get("/api/paper/list")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(1))
                .body("id", hasItem(paperId.intValue()));
    }
}





