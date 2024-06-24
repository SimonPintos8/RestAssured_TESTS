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

public class Test_POST_NUEVA_CUENTA {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIPOST-NUEVA-CUENTA-Test.html");
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
    public void POST_Apertura_nueva_cuenta() {
        test = extent.createTest("Primer Test POST de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Post");
        test.log(Status.INFO, "Iniciando Primer Test Post");
        given()
                .contentType("application/json")
                .auth().preemptive().basic("pakitoatr", "123456")
                .when().post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=15986&newAccountType=1&fromAccountId=18894")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "Primer Test Post finalizado");
    }

    @Test
    @Tag("POST2")
    public void POST_Transferencia_exitosa() {
        test = extent.createTest("Primer Test POST de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Post");
        test.log(Status.INFO, "Iniciando Primer Test Post");
        given()
                .contentType("application/json")
                .auth().preemptive().basic("pakitoatr", "123456")
                .when().post("https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=18894&toAccountId=19005&amount=10")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "Primer Test Post finalizado");
    }
}