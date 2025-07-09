//package bmi_pages;
//
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class QueriesPage {
//    WebDriver driver;
//
//    @FindBy(id = "contact")
//    public WebElement emailInput;
//
//    @FindBy(id = "query")
//    public WebElement queryTextarea;
//
//    @FindBy(xpath = "//button[text() = 'Send']")
//    public WebElement sendButton;
//
//    public QueriesPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//   
//
//	public void enterEmail(String email) {
//        emailInput.clear();
//        emailInput.sendKeys(email);
//    }
//
//    public void enterQuery(String query) {
//        queryTextarea.clear();
//        queryTextarea.sendKeys(query);
//    }
//
//    public void clickSend() {
//        sendButton.click();
//    }
//}
package bmi_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QueriesPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "contact")
    public WebElement emailInput;

    @FindBy(id = "query")
    public WebElement queryTextarea;

    @FindBy(xpath = "//button[text() = 'Send']")
    public WebElement sendButton;

    public QueriesPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(this.driver,Duration.ofSeconds(100));
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).clear();
        emailInput.sendKeys(email);
    }

    public void enterQuery(String query) {
        wait.until(ExpectedConditions.visibilityOf(queryTextarea)).clear();
        queryTextarea.sendKeys(query);
    }

    public void clickSend() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
    }
}

