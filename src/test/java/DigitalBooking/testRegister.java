package DigitalBooking;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static reportes.ReportFactory.captureScreenshot;

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Register-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void RegistroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickRegistrarse();
        try {
            Thread.sleep(2000);
            registerPage.escribirNombre("EfraS");
            registerPage.escribirApellido("Platzi");
            registerPage.escribirAddress("boston");
            registerPage.escribirCity("celtics");
            registerPage.escribirState("LA");
            registerPage.escribirZipcode("blabla");
            registerPage.escribirPhone("299 23982");
            registerPage.escribirSsn("asdd");
            registerPage.escribirUsername("pakitoatr");
            registerPage.escribirContrasenia("123456");
            registerPage.repetirContrasenia("123456");
            registerPage.clickCrearCuenta();
            Thread.sleep(2000);
            if (registerPage.cuentaCreadaExito().equals("Your account was created successfully. You are now logged in.")) {
                test.log(Status.PASS, "Registro alcanzado con éxito!");
            } else {
                test.log(Status.FAIL, "Registro incompleto");
            }
            registerPage.cuentaCreadaExito();
        } catch (Exception e) {
            test.log(Status.FAIL, "FALLO EL TEST DE REGISTRO" + e.getMessage());
            captureScreenshot(test, "FAIL_RegistroExitoso", driver);
        }
    }

    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
