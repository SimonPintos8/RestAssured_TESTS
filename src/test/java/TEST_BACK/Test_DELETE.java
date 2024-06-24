package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;

public class Test_DELETE {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIDELETE-Test.html");
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
    @Tag("DELETE")
    public void DELETE_Test01() {
        test = extent.createTest("Primer Test DELETE - Eliminar un usuario existente");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Primer Test Delete");

        try {
        given()
                .when().delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .body(emptyString())
                .log().status();

        test.log(Status.PASS, "Usuario eliminado correctamente");
        System.out.println("Usuario eliminado correctamente");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo en el test: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Tag("DELETE")
    public void DELETE_Test02() {
        test = extent.createTest("Segundo Test DELETE - Intentar eliminar un usuario inexistente");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Segundo Test Delete");

        try {
        given()
                .when().delete("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .log().status();

        test.log(Status.PASS, "Intento de eliminar un usuario inexistente correctamente manejado");
        System.out.println("Intento de eliminar un usuario inexistente correctamente manejado");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo en el test: " + e.getMessage());
            System.out.println("Fallo en el test: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Tag("DELETE")
    public void DELETE_Test03() {
        test = extent.createTest("Tercer Test DELETE - Eliminar un usuario sin permisos adecuados");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Tercer Test Delete");

        try {
        given()
                .auth().basic("user", "12345678999")
                .when().delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(401)
                .log().status();

        test.log(Status.PASS, "Eliminación sin permisos adecuadamente manejada");
        System.out.println("Eliminación sin permisos adecuadamente manejada");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo en el test: " + e.getMessage());
            System.out.println("Fallo en el test: " + e.getMessage());
            throw e;
        }
    }
}