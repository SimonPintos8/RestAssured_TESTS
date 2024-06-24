package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferPage extends BasePage{

    private By transferButton = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private By transferText = By.xpath("//*[@id=\"showForm\"]/h1");
    private By amountInput = By.xpath("//*[@id=\"amount\"]");
    private By selectAccountFirst = By.xpath("//*[@id=\"fromAccountId\"]");
    private By selectedAccountFirst = By.xpath("//*[@id=\"fromAccountId\"]/option[2]"); // 16119
    private By selectAccountSecond = By.xpath("//*[@id=\"toAccountId\"]");
    private By selectedAccountSecond = By.xpath("//*[@id=\"toAccountId\"]"); // 17118
    private By completeTransferButton = By.xpath("//*[@id=\"transferForm\"]/div[2]/input");
    private By transferCompletedText = By.xpath("//*[@id=\"showResult\"]/h1");

    public TransferPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public String getTextTransfer() throws InterruptedException {
        String res = this.getText(transferText);
        System.out.println("Resultado:" + res);
        return res;
    }

    public String getTextCompletedTransfer() throws InterruptedException {
        String res = this.getText(transferCompletedText);
        System.out.println("Resultado:" + res);
        return res;
    }

    public void clickTransferButton() throws InterruptedException {
        this.click(transferButton);
    }

    public void sendAmount(String amount) throws InterruptedException {
        this.sendText(amount, amountInput);
    }

    public void clickSelectAccountFirst() throws InterruptedException {
        this.click(selectAccountFirst);
    }

    public void clickSelectedAccountFirst() throws InterruptedException {
        this.click(selectedAccountFirst);
    }

    public void clickSelectAccountSecond() throws InterruptedException {
        this.click(selectAccountSecond);
    }

    public void clickSelectedAccountSecond() throws InterruptedException {
        this.click(selectedAccountSecond);
    }

    public void clickCompleteTransferButton() throws InterruptedException {
        this.click(completeTransferButton);
    }

}
