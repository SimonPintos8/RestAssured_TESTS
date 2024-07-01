package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenNewAccountPage extends BasePage{

    protected OpenNewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    private By openNewAccountBtn = By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");
    private By deployOptionsMenuBtn = By.xpath("//*[@id=\"type\"]");
    private By chooseSavingsBtn = By.xpath("//*[@id=\"type\"]/option[2]");
    private By createNewAccountBtn = By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");
    private By verifyCreationTest = By.xpath("//*[@id=\"openAccountResult\"]/p[1]");

    public void clickOpenNewAccount() throws InterruptedException {
        this.click(openNewAccountBtn);
    }

    public void clickMenuOptions() throws InterruptedException {
        this.click(deployOptionsMenuBtn);
    }

    public void clickChooseSavingsBtn() throws InterruptedException {
        this.click(chooseSavingsBtn);
    }

    public void clickCreateNewAccount() throws InterruptedException {
        this.click(createNewAccountBtn);
    }

    public String getCreationText() throws InterruptedException {
        String res = this.getText(verifyCreationTest);
        System.out.println("Result:" + res);
        return res;
    }
}
