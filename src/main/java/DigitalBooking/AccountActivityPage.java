package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountActivityPage extends BasePage{

    private By cuentaSeleccion = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private By accountDetailsLocation = By.xpath("//*[@id=\"accountDetails\"]/h1");
    private By activityPeriodLocation = By.xpath("//*[@id=\"month\"]");
    private By activityPeriodOption = By.xpath("//*[@id=\"month\"]/option[1]");
    private By typeLocation = By.xpath("//*[@id=\"transactionType\"]");
    private By typeOption = By.xpath("//*[@id=\"transactionType\"]/option[1]");
    private By goButton = By.xpath("//*[@id=\"activityForm\"]/table/tbody/tr[3]/td[2]/input");

    public AccountActivityPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void clickSpecificAccount() throws InterruptedException {
        this.click(cuentaSeleccion);
    }

    public void clickActivityPeriod() throws InterruptedException {
        this.click(activityPeriodLocation);
    }

    public void clickSpecificOptionActivity() throws InterruptedException {
        this.click(activityPeriodOption);
    }

    public void clickTypeLocation() throws InterruptedException {
        this.click(typeLocation);
    }

    public void clickSpecificOptionType() throws InterruptedException {
        this.click(typeOption);
    }

    public void clickGoButton() throws InterruptedException {
        this.click(goButton);
    }


    public String getTextDetails() throws InterruptedException {
        String res = this.getText(accountDetailsLocation);
        System.out.println("Resultado:" + res);
        return res;
    }

}
