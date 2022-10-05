package one.digitalinnovation.estacionamento.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.estacionamento.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenExtractBody() {
        RestAssured.given()
                .auth()
                .basic("user","aspas@1234")
                .when()
                .get("/parking")
                .then()
                .extract().response().body().prettyPrint();
    }
    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .auth()
                .basic("user","aspas@1234")
                .get("/parking")
                .then()
                .statusCode(200)
                .body("license[0]", Matchers.equalTo("PLE-1111"));
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("VERDE");
        createDTO.setLicense("PLE-1111");
        createDTO.setModel("Fusca");
        createDTO.setState("BA");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("PLE-1111"))
                .body("model", Matchers.equalTo("Fusca"))
                .body("color", Matchers.equalTo("VERDE"))
                .body("state", Matchers.equalTo("BA"));
    }
}