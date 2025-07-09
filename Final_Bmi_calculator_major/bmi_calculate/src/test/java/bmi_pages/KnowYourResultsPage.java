//
//package bmi_pages;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.Select;
//
//public class KnowYourResultsPage {
//    WebDriver driver;
//
//    @FindBy(id = "height")
//    public WebElement heightInput;
//
//    @FindBy(id = "weight")
//    public WebElement weightInput;
//
//    @FindBy(id = "height_units")
//    public WebElement heightUnitsDropdown;
//
//    @FindBy(id = "weight_units")
//    public WebElement weightUnitsDropdown;
//
//    @FindBy(xpath = "//button[text() = 'Calculate BMI']")
//    public WebElement calculateBMIButton;
//
//    @FindBy(id = "result")
//    public WebElement resultDisplay;
//
//    @FindBy(xpath = "//input[@type = 'reset']")
//    public WebElement resetButton;
//
//    public KnowYourResultsPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    public void enterHeight(String height) {
//        heightInput.clear();
//        heightInput.sendKeys(height);
//    }
//
//    public void enterWeight(String weight) {
//        weightInput.clear();
//        weightInput.sendKeys(weight);
//    }
//
//    public void selectHeightUnit(String unit) {
//        new Select(heightUnitsDropdown).selectByVisibleText(unit);
//    }
//
//    public void selectWeightUnit(String unit) {
//        new Select(weightUnitsDropdown).selectByVisibleText(unit);
//    }
//
//    public void clickCalculateBMI() {
//        calculateBMIButton.click();
//    }
//
//    public String getResultText() {
//        return resultDisplay.getText();
//    }
//
//    public void clickReset() {
//        resetButton.click();
//    }
//
//	
//}
package bmi_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KnowYourResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "height")
    public WebElement heightInput;

    @FindBy(id = "weight")
    public WebElement weightInput;

    @FindBy(id = "height_units")
    public WebElement heightUnitsDropdown;

    @FindBy(id = "weight_units")
    public WebElement weightUnitsDropdown;

    @FindBy(xpath = "//button[text() = 'Calculate BMI']")
    public WebElement calculateBMIButton;

    @FindBy(id = "result")
    public WebElement resultDisplay;

    @FindBy(xpath = "//input[@type = 'reset']")
    public WebElement resetButton;

    public KnowYourResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        PageFactory.initElements(driver, this);
    }

    public void enterHeight(String height) {
        wait.until(ExpectedConditions.visibilityOf(heightInput)).clear();
        heightInput.sendKeys(height);
    }

    public void enterWeight(String weight) {
        wait.until(ExpectedConditions.visibilityOf(weightInput)).clear();
        weightInput.sendKeys(weight);
    }

    public void selectHeightUnit(String unit) {
        wait.until(ExpectedConditions.elementToBeClickable(heightUnitsDropdown));
        new Select(heightUnitsDropdown).selectByVisibleText(unit);
    }

    public void selectWeightUnit(String unit) {
        wait.until(ExpectedConditions.elementToBeClickable(weightUnitsDropdown));
        new Select(weightUnitsDropdown).selectByVisibleText(unit);
    }

    public void clickCalculateBMI() {
        wait.until(ExpectedConditions.elementToBeClickable(calculateBMIButton)).click();
    }

    public String getResultText() {
        wait.until(ExpectedConditions.visibilityOf(resultDisplay));
        return resultDisplay.getText();
    }

    public void clickReset() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }
}

