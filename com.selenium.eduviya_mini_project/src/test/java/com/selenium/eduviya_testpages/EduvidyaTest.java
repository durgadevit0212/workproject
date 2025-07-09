package com.selenium.eduviya_testpages;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.selenium.eduviya_pages.HomePage;
import com.selenium.eduviya_pages.SchoolSearchPage;
import com.selenium.eduviya_utility.ScreenshotUtil;
import com.selenium.eduviya_utility.WriteToCsvFile;
public class EduvidyaTest {

    WebDriver driver;
    HomePage homePage;
    SchoolSearchPage searchPage;
    List<String> schoolNames;
    WriteToCsvFile csvWriter;

    @BeforeClass
    public void setUp() throws IOException {
        // Configure ChromeOptions to accept insecure certificates
    	ChromeOptions options = new ChromeOptions();
    	options.setAcceptInsecureCerts(true);
    	options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    	driver = new ChromeDriver(options);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.manage().window().maximize();

    	homePage = new HomePage(driver);
    	searchPage = new SchoolSearchPage(driver);
    	schoolNames = new ArrayList<>();


    	csvWriter = new WriteToCsvFile("src/test/resources/exceldata/");

        

    }
    

    @Test(priority = 1)
    public void openHomePage() {
        driver.get("https://www.eduvidya.com/");
    }
//    public void titlePage() {
//    	
//    }
    @Test(priority = 2)
    public void navigateToSchools() {
//    	System.out.println("reached");
        homePage.clickSchoolsLink();
    }
   
    @Test(priority=3)
    public void selectCourseType() {
    	searchPage.selectCourseType("CBSE");
    }
    @Test(priority=4)
    public void selectCity()
    {
    	 searchPage.selectCity("Pune");
    }
    @Test(priority = 5)
    public void searchSchools() throws IOException, InterruptedException {
//        searchPage.selectCourseType("CBSE");
//        searchPage.selectCity("Pune");
        searchPage.clickSearch();
        Thread.sleep(3000);
        ScreenshotUtil.takeScreenshot(driver);
        for (int i = 1; i <= 5; i++) {
            Thread.sleep(2000);
            for (var school : searchPage.getSchoolLinks()) {
                String name = school.getText().trim();
                if (!name.isEmpty()) {
                    schoolNames.add(name);
                    csvWriter.writeSchoolName(name);

                }
            }
            if (i < 5) {
                searchPage.goToNextPage(i + 1);
//                ScreenshotUtil.takeScreenshot(driver);
            }
        }

        System.out.println("Total schools found: " + schoolNames.size());
    }
    @Test(priority=6)
    public void displayListofSchools()
    {
    	for(String schoolname: schoolNames)
    	{
    		System.out.println(schoolname);
    	}
    }

    @AfterClass
    public void tearDown() throws IOException {
        if (csvWriter != null) {
            
        	csvWriter.closeWriter();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
