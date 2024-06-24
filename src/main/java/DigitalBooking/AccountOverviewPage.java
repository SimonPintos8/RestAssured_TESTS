package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage extends BasePage{

    private By accountOverviewButton = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
    private By accountMessageLocator = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");

    public AccountOverviewPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void clickAccountOverviewButton() throws InterruptedException {
        this.click(accountOverviewButton);
    }

    public String obtenerTexto() throws InterruptedException {
        String res = this.getText(accountMessageLocator);
        System.out.println("Resultado:" + res);
        return res;
    }
    // Se debe comparar con:*Balance includes deposits that may be subject to holds
}
