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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test_PUT {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIPUT-Test.html");
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
    @Tag("PUT")
    public void PUT_Test01() {
        test = extent.createTest("Primer Test PUT de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Put");
        test.log(Status.INFO, "Iniciando Primer Test Put");

        JsonObject request = new JsonObject();
        request.addProperty("name", "SERGIO");
        request.addProperty("job", "PROFESOR");
        Date actual = new Date();
        new SimpleDateFormat("yyyy-mm-dd").format(actual);
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {
        given()
                .contentType("application/json")
                .body(request)
                .when().put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("SERGIO"))
                .body("job", equalTo("PROFESOR"))
                .body("updatedAt", matchesRegex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"))
                .body("updatedAt", containsString(currentDate))
                .log().body();

        test.log(Status.PASS, "Usuario actualizado correctamente");
        System.out.println("Usuario actualizado correctamente");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo en el test: " + e.getMessage());
            System.out.println("Fallo en el test: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Tag("PUT")
    public void PUT_Test02() {
        test = extent.createTest("Segundo Test PUT de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Segundo Test Put");
        test.log(Status.INFO, "Iniciando Segundo Test Put");

        JsonObject request = new JsonObject();
        request.addProperty("name", "");
        request.addProperty("job", "");
        Date actual = new Date();
        new SimpleDateFormat("yyyy-mm-dd").format(actual);
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {
            given()
                    .contentType("application/json")
                    .body(request)
                    .when().put("https://reqres.in/api/users/2")
                    .then()
                    .statusCode(400)
                    .body("updatedAt", matchesRegex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"))
                    .body("updatedAt", containsString(currentDate))
                    .log().body();

            test.log(Status.PASS, "Intento de actualización de datos invalidos");
            System.out.println("Intento de actualización de datos invalidos");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo en el test: " + e.getMessage());
            System.out.println("Fallo en el test: " + e.getMessage());
            throw e;
        }
    }
}
