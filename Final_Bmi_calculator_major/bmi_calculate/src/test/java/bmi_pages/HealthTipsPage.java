package bmi_pages;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthTipsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "CDC Children's Health Guidelines →")
    public WebElement cdcLink;

    @FindBy(linkText = "WHO Weight Guidelines")
    public WebElement whoLink;

    @FindBy(linkText = "NIH Weight Management")
    public WebElement nihLink;

    public HealthTipsPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(this.driver,Duration.ofSeconds(100));
        PageFactory.initElements(driver, this);
    }
       public void clickCdcLink() {
            wait.until(ExpectedConditions.elementToBeClickable(cdcLink)).click();
        }

        public void clickWhoLink() {
            wait.until(ExpectedConditions.elementToBeClickable(whoLink)).click();
        }

        public void clickNihLink() {
            wait.until(ExpectedConditions.elementToBeClickable(nihLink)).click();
        }

        public void clickLink(WebElement link) {
            wait.until(ExpectedConditions.elementToBeClickable(link)).click();
        }
    }
  
	

