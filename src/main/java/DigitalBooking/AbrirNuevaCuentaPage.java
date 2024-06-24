package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbrirNuevaCuentaPage extends BasePage{

    protected AbrirNuevaCuentaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    private By abrirNuevaCuentaButton =By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");
    private By desplegarMenuOpcionesButton = By.xpath("//*[@id=\"type\"]");
    private By elegirSavingsButton = By.xpath("//*[@id=\"type\"]/option[2]");
    private By crearNuevaCuentaButton = By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");
    private By verificarTextoCreacion = By.xpath("//*[@id=\"openAccountResult\"]/p[1]");

    public void clickAbrirNuevaCuenta() throws InterruptedException {
        this.click(abrirNuevaCuentaButton);
    }

    public void clickDesplegarMenuOpcionesButton() throws InterruptedException {
        this.click(desplegarMenuOpcionesButton);
    }

    public void clickElegirSavingsButton() throws InterruptedException {
        this.click(elegirSavingsButton);
    }

    public void clickCrearNuevaCuentaButton() throws InterruptedException {
        this.click(crearNuevaCuentaButton);
    }

    public String obtenerTextoCreacion() throws InterruptedException {
        String res = this.getText(verificarTextoCreacion);
        System.out.println("Resultado:" + res);
        return res;
    }
}
