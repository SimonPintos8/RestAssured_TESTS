package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private By username = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By password = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private By loginButtom = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    // Completa los campos necesarios para el login.
    public void completarCampos() throws InterruptedException {
        this.sendText("pakitoatr", username);
        this.sendText("123456", password);
    }

    /** Hace click en el botón de Log In.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void clickIngresar() throws InterruptedException {
        this.click(loginButtom);
    }

}