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

public class Tests_POSTS {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/POSTS-TESTS-BACKEND.html");
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
    @Tag("POST_APERTURA_NUEVA_CUENTA")
    public void POST_Apertura_nueva_cuenta() {
        test = extent.createTest("Primer Test POST de apertura de una nueva cuenta");
        test.log(Status.INFO, "<<Comienza el Test>>");

        System.out.println("Iniciando Primer Test Post de una nueva cuenta");
        test.log(Status.INFO, "Iniciando Primer Test Post de una nueva cuenta");
        given()
                .contentType("application/json")
                .auth().preemptive().basic("pakitoatr", "123456")
                .when().post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=15986&newAccountType=1&fromAccountId=18894")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Primer Test Post de una nueva cuenta finalizado!");
        test.log(Status.PASS, "<<Primer Test Post de apertura de una nueva cuenta finalizado>>");
    }

    @Test
    @Tag("POST_DESCARGA_DE_FONDOS")
    public void POST_Descarga_fondos() {
        test = extent.createTest("Primer Test POST de Descarga de fondos");
        test.log(Status.INFO, "<<Comienza el Test>>");

        System.out.println("Iniciando Primer Test Post de Descarga de Fondos");
        test.log(Status.INFO, "Iniciando Primer Test Post de descarga de fondos");
        given()
                .contentType("application/json")
                .auth().preemptive().basic("pakitoatr", "123456")
                .when().post("https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=15564&toAccountId=15675&amount=10")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Primer Test Post de descarga de fondos finalizado");
        test.log(Status.PASS, "<<Primer Test Post de descarga de fondos finalizado>>");
    }

}