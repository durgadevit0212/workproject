//package bmi_pages;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//public class HomePage {
//    WebDriver driver;
//
//    @FindBy(linkText = "Know Your Results")
//    public WebElement knowYourResultsLink;
//
//    @FindBy(linkText = "Health Tips")
//    public WebElement healthTipsLink;
//
//    @FindBy(linkText = "Find a Doctor")
//    public WebElement findDoctorLink;
//
//    @FindBy(linkText = "Queries")
//    public WebElement queriesLink;
//
//    @FindBy(linkText = "Home")
//    public WebElement homeLink;
//    
//    @FindBy(id="kids-btn")
//    public WebElement kids;
//    
//    @FindBy(id="adults-btn")
//    public WebElement adults;
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
////    public HomePage(WebDriver driver2) {
////		// TODO Auto-generated constructor stub
////	}
//
//	public void navigateToKnowYourResults() {
//        knowYourResultsLink.click();
//    }
//
//    public void navigateToHealthTips() {
//        healthTipsLink.click();
//    }
//
//    public void navigateToFindDoctor() {
//        findDoctorLink.click();
//    }
//
//    public void navigateToQueries() {
//        queriesLink.click();
//    }
//
//    public void navigateToHome() {
//        homeLink.click();
//    }
//    
//    public void kidsButton()
//    {
//    	kids.click();
//    }
//    public void adultsButton()
//    {
//    	adults.click();
//    }
//
//	public WebElement getKidsButtonElement() {
//		// TODO Auto-generated method stub
//		return kids;
//	}
//
//	public WebElement getAdultsButtonElement() {
//		// TODO Auto-generated method stub
//		return adults;
//	}
//
//	
//
//	
//}
package bmi_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "Know Your Results")
    public WebElement knowYourResultsLink;

    @FindBy(linkText = "Health Tips")
    public WebElement healthTipsLink;

    @FindBy(linkText = "Find a Doctor")
    public WebElement findDoctorLink;

    @FindBy(linkText = "Queries")
    public WebElement queriesLink;

   @FindBy(linkText = "Home")
    public WebElement homeLink;

    @FindBy(id = "kids-btn")
    public WebElement kids;

    @FindBy(id = "adults-btn")
    public WebElement adults;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100)); 
        PageFactory.initElements(driver, this);
    }

    public void navigateToKnowYourResults() {
        wait.until(ExpectedConditions.elementToBeClickable(knowYourResultsLink)).click();
    }

    public void navigateToHealthTips() {
        wait.until(ExpectedConditions.elementToBeClickable(healthTipsLink)).click();
    }

    public void navigateToFindDoctor() {
        wait.until(ExpectedConditions.elementToBeClickable(findDoctorLink)).click();
    }

    public void navigateToQueries() {
        wait.until(ExpectedConditions.elementToBeClickable(queriesLink)).click();
    }

    public void navigateToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
    }

    public void kidsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(kids)).click();
    }

    public void adultsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adults)).click();
    }

    public WebElement getKidsButtonElement() {
        return kids;
    }

    public WebElement getAdultsButtonElement() {
        return adults;
    }
}

