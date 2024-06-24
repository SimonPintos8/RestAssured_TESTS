package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Test_POST {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIPOST-Test.html");
    static ExtentReports extent;
    ExtentTest test;

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }

    @Test
    @Tag("POST")
    public void POST_Test01() {
        test = extent.createTest("Primer Test POST de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Post");
        test.log(Status.INFO, "Iniciando Primer Test Post");

        JsonObject request = new JsonObject();
        request.addProperty("name", "PACE");
        request.addProperty("job", "PROFESOR");

        given()
                .contentType("application/json")
                .body(request)
                .when().post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body();

        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "Primer Test Post finalizado");
    }

    @Test
    @Tag("POST")
    public void POST_Test02() {
        test = extent.createTest("Segundo Test POST de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Segundo Test Post");
        test.log(Status.INFO, "Iniciando Segundo Test Post");

        JsonObject request = new JsonObject();
        request.addProperty("name", "SERGIO");
        request.addProperty("job", "PROFESOR");

        given()
                .contentType("application/json")
                .body(request)
                .when().post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("SERGIO"))
                .body("job", equalTo("PROFESOR"))
                .body("createdAt", containsString("2024-06-06"))
                .log().status()
                .log().body();

        System.out.println("Segundo Test Post finalizado");
        test.log(Status.PASS, "Segundo Test Post finalizado");
    }
}