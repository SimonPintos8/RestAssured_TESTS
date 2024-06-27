package DigitalBooking;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static reportes.ReportFactory.captureScreenshot;

public class testAccountActivity {
    private WebDriver driver;
    private WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/AccountActivity-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE ACTIVIDAD DE LA CUENTA >>>");
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
    @Tag("ACTIVITY_ACCOUNT")
    @Tag("EXITOSO")
    public void obtenerActividadDeCuentas() throws InterruptedException {
        ExtentTest test = extent.createTest("CHEQUEO DE ACTIVIDAD DE CUENTA");
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);
        AccountActivityPage accountActivityPage = new AccountActivityPage(driver, wait);

        try {
            accountOverviewPage.clickAccountOverviewButton();
            wait.until(ExpectedConditions.urlToBe("https://parabank.parasoft.com/parabank/overview.htm"));
            if (accountOverviewPage.obtenerTexto().equals("*Balance includes deposits that may be subject to holds")) {
                System.out.println("Texto encontrado: " + accountOverviewPage.obtenerTexto());
            } else {
                System.out.println("Balance includes deposits that may be subject to holds no encontrado.");
            }
            accountActivityPage.clickSpecificAccount();
            if (accountActivityPage.getTextDetails().equals("Account Details")) {
                test.log(Status.PASS, "Texto encontrado: " + accountActivityPage.getTextDetails());
            } else {
                test.log(Status.FAIL, "Account Details no encontrado.");
            }
            accountActivityPage.clickActivityPeriod();
            accountActivityPage.clickSpecificOptionActivity();
            accountActivityPage.clickTypeLocation();
            accountActivityPage.clickSpecificOptionType();
            accountActivityPage.clickGoButton();

        } catch (Exception e) {
            test.log(Status.FAIL, "FALLO EL TEST DE VERIFICAR ACTIVIDAD DE CUENTA" + e.getMessage());
            captureScreenshot(test, "FAIL_Actividad_cuenta", driver);
        }
    }

    @AfterEach
    public void cerrar() {
        AccountActivityPage accountActivityPage = new AccountActivityPage(driver, wait);
        accountActivityPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE CHEQUEO DE ACTIVIDAD DE CUENTAS >>>");
    }
}
