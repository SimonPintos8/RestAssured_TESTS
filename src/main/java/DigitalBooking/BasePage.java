package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    // Base Link: https://parabank.parasoft.com/parabank/index.htm
    private WebDriver driver;
    public WebDriverWait wait;

    /** Constructor de la página base.
     * @param driver El controlador del navegador web.
     */
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /** Método para configurar opciones del navegador
     */
    protected void setup() {
        driver.manage().window().maximize();
    }

    /** Método para navegar a la URL especificada.
     * @param url La URL a la que se desea navegar.
     */
    protected void getUrl(String url) throws InterruptedException {
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    /** Método para cerrar el navegador web.
     */
    protected void close() {
        driver.quit();
    }

    /** Método para encuentrar un elemento en la página mediante el localizador especificado.
     * @param locator El localizador del elemento.
     * @return El elemento encontrado.
     */
    protected WebElement findElement(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    /** Método para ingresar texto en el elemento especificado.
     * @param inputText El texto a ingresar.
     * @param locator El localizador del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected void sendText(String inputText, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    /** Método para hacer click en el elemento especificado.
     * @param locator El localizador del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    /** Método para enviar una tecla al elemento especificado.
     * @param key La tecla a enviar.
     * @param locator El localizador del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(key);
    }

    /** Método para obtener el texto del elemento especificado.
     * @param locator El localizador del elemento.
     * @return El texto del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected String getText(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }
}