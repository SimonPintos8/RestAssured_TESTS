package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;

public class Tests_GETS {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIGET-REGISTRO-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @Test
    @Tag("GET-REGISTRO")
    public void GET_REGISTRO_TEST() {
        ExtentTest test = extent.createTest("Primer Test GET de la página de registro");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Primer Test Get");
        test.log(Status.INFO, "Iniciando Primer Test Get");
        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/register.htm");
        if (responseGet.statusCode() == 200){
            System.out.println("Respuesta exitosa obtenida:" + responseGet.statusCode());
        } else {
            System.out.println("Respuesta diferente a status 200: " + responseGet.statusCode());
        }
        System.out.println("Primer Test Get finalizado");
        test.log(Status.PASS, "Primer Test Get finalizado");
    }

    @Test
    @Tag("GET_ACCOUNTS")
    public void get_accounts() {
        ExtentTest test;
        test = extent.createTest("Primer Test GET ACCOUNTS");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test GET ACCOUNTS");
        test.log(Status.INFO, "Iniciando Primer Test de Get accounts");
        given()
                .contentType("application/json")
                .auth().preemptive().basic("pakitoatr", "123456")
                .when().get("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/20781/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Primer Test de GET ACCOUNTS finalizado");
        test.log(Status.PASS, "Primer Test de Get ACCOUNTS finalizado");
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }
}