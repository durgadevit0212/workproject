package bmi_pages;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindDoctorPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//div[@class='card-group']/div[1]/div[2]//a")
    public WebElement doctorLink1;

    @FindBy(xpath = "//div[@class='card-group']/div[2]/div[2]//a")
    public WebElement doctorLink2;

    @FindBy(xpath = "//div[@class='card-group']/div[3]/div[2]//a")
    public WebElement doctorLink3;

    @FindBy(xpath = "//div[@class='card-group']/div[4]/div[2]//a")
    public WebElement doctorLink4;

    @FindBy(xpath = "//div[@class='card-group']/div[5]/div[2]//a")
    public WebElement doctorLink5;

    public FindDoctorPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(this.driver,Duration.ofSeconds(100));
        PageFactory.initElements(driver, this);
    }

	public void clickDocLink(WebElement link) {
		wait.until(ExpectedConditions.elementToBeClickable(link)).click();
		
	}
   

}
