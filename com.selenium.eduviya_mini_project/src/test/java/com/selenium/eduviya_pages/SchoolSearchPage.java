package com.selenium.eduviya_pages;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SchoolSearchPage {
	    WebDriver driver;

	    @FindBy(id = "ddl_Category")
	    WebElement courseTypeDropdown;

	    @FindBy(id = "ddl_City")
	    WebElement cityDropdown;

	    @FindBy(id = "btnSearch")
	    WebElement searchButton;

	    @FindBy(xpath = "//a[@class='rec_links']")
	    List<WebElement> schoolLinks;

	    public SchoolSearchPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void selectCourseType(String course) {
	        new Select(courseTypeDropdown).selectByVisibleText(course);
	    }

	    public void selectCity(String city) {
	        new Select(cityDropdown).selectByVisibleText(city);
	    }

	    public void clickSearch() {
	        searchButton.click();
	    }

	    public List<WebElement> getSchoolLinks() {
	        return schoolLinks;
	    }

	    public void goToNextPage(int pageNumber) {
	        driver.findElement(By.xpath("//a[text()='" + pageNumber + "']")).click();
	    }
}



