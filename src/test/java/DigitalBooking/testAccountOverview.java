package DigitalBooking;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static reportes.ReportFactory.captureScreenshot;

public class testAccountOverview {
    private WebDriver driver;
    private WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/AccountOverview-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE RESUMEN DE CUENTAS >>>");
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
    @Tag("RESUMEN_CUENTAS")
    @Tag("EXITOSO")
    public void obtenerResumenDeCuentas() throws InterruptedException {
        ExtentTest test = extent.createTest("CHEQUEO DE RESUMEN DE CUENTAS");
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);

        try {
            accountOverviewPage.clickAccountOverviewButton();
            wait.until(ExpectedConditions.urlToBe("https://parabank.parasoft.com/parabank/overview.htm"));
            if (accountOverviewPage.obtenerTexto().equals("*Balance includes deposits that may be subject to holds")) {
                test.log(Status.PASS, "Chequeo de resumen de cuentas exitoso!");
            } else {
                test.log(Status.FAIL, "Chequeo de resumen de cuentas incompleto");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "FALLO EL TEST DE OBTENER RESUMENES DE CUENTAS" + e.getMessage());
            captureScreenshot(test, "FAIL_Resumen_cuentas", driver);
        }
    }

    @AfterEach
    public void cerrar() {
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);
        accountOverviewPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE CHEQUEO DE RESUMEN DE CUENTAS >>>");
    }
}
