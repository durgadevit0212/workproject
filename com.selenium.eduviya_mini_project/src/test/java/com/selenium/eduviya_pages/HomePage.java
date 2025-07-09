package com.selenium.eduviya_pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    @FindBy(xpath="//a[normalize-space()='Schools']")
    WebElement schools;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    

	public void clickSchoolsLink() {
		// TODO Auto-generated method stub
		schools.click();
	}
}
