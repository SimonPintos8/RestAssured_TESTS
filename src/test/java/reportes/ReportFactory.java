package reportes;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportFactory {
    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Navegador", "Chrome");
        extent.setSystemInfo("Ambiente", "QA");
        extent.setSystemInfo("Selenium Version", "4.21.0");
        return extent;
    }

    public static void captureScreenshot(ExtentTest test, String testName, WebDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "reportes/screenshots/" + testName + ".png";
            Files.createDirectories(Paths.get("reportes/screenshots"));
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            test.addScreenCaptureFromPath(screenshotPath, testName);
        } catch (IOException e) {
            test.log(Status.WARNING, "No se pudo capturar la captura de pantalla: " + e.getMessage());
        }
    }
}