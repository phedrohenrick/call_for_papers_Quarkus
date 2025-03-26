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


}





