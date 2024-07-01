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

public class testAbrirNuevaCuenta {


    private WebDriver driver;
    private WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/AbrirNuevaCuenta-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE ABRIR NUEVA CUENTA >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setup();
        loginPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.completarCampos();
        loginPage.clickIngresar();
    }

    @Test
    @Tag("APERTURA_CUENTA")
    @Tag("EXITOSA")
    public void aperturaCuentaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("APERTURA DE CUENTA EXITOSA");
        OpenNewAccountPage openNewAccountPage = new OpenNewAccountPage(driver, wait);

        try {
            Thread.sleep(2000);
            openNewAccountPage.clickOpenNewAccount();
            openNewAccountPage.clickMenuOptions();
            openNewAccountPage.clickChooseSavingsBtn();
            openNewAccountPage.clickCreateNewAccount();
            Thread.sleep(2000);
            if (openNewAccountPage.getCreationText().equals("Congratulations, your account is now open.")) {
                test.log(Status.PASS, "Apertura de cuenta alcanzado con éxito!");
            } else {
                test.log(Status.FAIL, "Apertura de cuenta incompleta");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "FALLO EL TEST DE APERTURA DE CUENTA" + e.getMessage());
            captureScreenshot(test, "FAIL_Apertura_de_cuenta_exitosa", driver);
        }
    }

    @AfterEach
    public void cerrar() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE ABRIR NUEVA CUENTA >>>");
    }
}
