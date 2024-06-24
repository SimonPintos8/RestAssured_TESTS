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

public class testTransfer {
    private WebDriver driver;
    private WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/TestTransfer-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE TRANSFERENCIA >>>");
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
    @Tag("TRANSFERENCIA")
    @Tag("EXITOSA")
    public void realizarTransferencia() throws InterruptedException {
        ExtentTest test = extent.createTest("CHEQUEO DE REALIZACIÓN DE TRANSFERENCIAS CORRECTAMENTE");
        TransferPage transferPage = new TransferPage(driver, wait);

        try {
            transferPage.clickTransferButton();
            wait.until(ExpectedConditions.urlToBe("https://parabank.parasoft.com/parabank/transfer.htm"));
            if (transferPage.getTextTransfer().equals("Transfer Funds")) {
                System.out.println("Titulo obtenido con exito!");
            } else {
                System.out.println("Titulo no obtenido");
            }
            transferPage.sendAmount("10");
            transferPage.clickSelectAccountFirst();
            transferPage.clickSelectedAccountFirst();
            transferPage.clickSelectAccountSecond();
            transferPage.clickSelectedAccountSecond();
            transferPage.clickCompleteTransferButton();
            Thread.sleep(10000);
            if(transferPage.getTextCompletedTransfer().equals("Transfer Complete!")) {
                System.out.println(transferPage.getTextCompletedTransfer() + ", Transferencia exitosa!");
            } else {
                System.out.println("Transferencia no realizada");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "FALLO EL TEST DE REALIZAR TRANSFERENCIAS" + e.getMessage());
            captureScreenshot(test, "FAIL_Transfer", driver);
        }
    }

    @AfterEach
    public void cerrar() {
        TransferPage transferPage = new TransferPage(driver, wait);
        transferPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REALIZAR TRANSFERENCIAS >>>");
    }

}
