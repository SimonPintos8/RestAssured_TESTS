package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;

public class Test_GET {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIGET-Test.html");
    static ExtentReports extent;

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
    @Tag("GET")
    public void GET_Test01() {
        ExtentTest test = extent.createTest("Primer Test GET de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Primer Test Get");
        test.log(Status.INFO, "Iniciando Primer Test Get");

        Response responseGet = RestAssured.get("https://reqres.in/api/users?page=1");
        System.out.println(responseGet.getBody().asString());
        System.out.println(responseGet.statusCode());
        System.out.println(responseGet.getHeader("Date"));
        System.out.println(responseGet.getTime());

        System.out.println("Primer Test Get finalizado");
        test.log(Status.PASS, "Primer Test Get finalizado");
    }

    @Test
    @Tag("GET")
    public void GET_Test02() {
        ExtentTest test = extent.createTest("Segundo Test GET de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Segundo Test Get");
        test.log(Status.INFO, "Iniciando Segundo Test Get");

        Response responseGet = RestAssured.get("https://reqres.in/api/users?page=1");

        int statusCode = responseGet.statusCode();
        JsonPath body = responseGet.jsonPath();
        String nombre_1 = body.getString("data[0].first_name");
        String mail_1 = body.getString("data[0].email");

        Assertions.assertEquals(nombre_1, "George");
        Assertions.assertEquals(mail_1, "george.bluth@reqres.in");

        System.out.println("EL CÓDIGO DEL RESPONSE ES: " + statusCode);
        System.out.println("EL NOMBRE DEL PRIMER USUARIO DE LA LISTA ES: " + nombre_1);
        System.out.println("EL MAIL DEL USUARIO" + nombre_1 + " ES: " + mail_1);

        System.out.println("Segundo Test Get finalizado");
        test.log(Status.PASS, "Segundo Test Get finalizado");
    }

    @Test
    @Tag("GET")
    public void GET_Test03() {
        ExtentTest test = extent.createTest("Tercer Test GET de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Tercer Test Get");
        test.log(Status.INFO, "Iniciando Tercer Test Get");

        given()
                .get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .log().body();

        System.out.println("Tercer Test Get finalizado");
        test.log(Status.PASS, "Tercer Test Get finalizado");
    }
}