package bmi_tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bmi_pages.FindDoctorPage;
import bmi_pages.HealthTipsPage;
import bmi_pages.HomePage;
import bmi_pages.KnowYourResultsPage;
import bmi_pages.QueriesPage;
import bmi_utilities.ExcelUtils;
import bmi_utilities.ScreenShot;
public class BMI_test { 
    protected WebDriver driver;
    WebDriverWait wait;
    protected final String BASE_URL = "https://karthi-553.github.io/BMI_Calculator/index.html";
    HomePage home;
    KnowYourResultsPage knowYourResult;
    HealthTipsPage healthTipPage;
    FindDoctorPage findDoctor;
    QueriesPage queryPage;
    ScreenShot shot;
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) throws IOException {
         switch (browser.toLowerCase()) {
    	            case "chrome":
    	            	driver = new ChromeDriver();
    	                break;
    	            case "edge":
    	                driver = new EdgeDriver();
    	                break;
    	            default:
    	                throw new IllegalArgumentException("Unsupported browser type: " + browser);
    	}
        
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        shot=new ScreenShot(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
	public void testHomeLink(){
    	HomePage home = new HomePage(driver);
		home.navigateToHome();
		Assert.assertTrue(driver.getCurrentUrl().contains("index.html"), "Home link failed.");

	}
    
    @Test(priority = 2)
    public void testKnowYourResultsLink() {
        HomePage home = new HomePage(driver);
        home.navigateToKnowYourResults();
        Assert.assertTrue(driver.getCurrentUrl().contains("bmi.html"), "Know Your Results link failed.");

    }

    @Test(priority = 3)
    public void testHealthTipsLink() {
        HomePage home = new HomePage(driver);
        home.navigateToHealthTips();
        Assert.assertTrue(driver.getCurrentUrl().contains("tips.html"), "Health Tips link failed.");

    }
    

    @Test(priority = 4)
    public void testFindDoctorLink() {
        HomePage home = new HomePage(driver);
        home.navigateToFindDoctor();
        Assert.assertTrue(driver.getCurrentUrl().contains("consult.html"), "Find a Doctor link failed.");

    
    }

    @Test(priority = 5)
    public void testQueriesLink()  {
        HomePage home = new HomePage(driver);
        home.navigateToQueries();
        Assert.assertTrue(driver.getCurrentUrl().contains("queries.html"), "Queries link failed.");
    }
	    
    @Test(priority = 6, description = "Verify Kids and Adults button functionality")
    public void testKidsAndAdultsButtons(){

    	
    	HomePage home = new HomePage(driver);
		home.navigateToHome();
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	home.kidsButton();
    	js.executeScript("window.scrollBy(0, -1000)");
    	home.adultsButton();
    	Assert.assertTrue(home.getAdultsButtonElement().isEnabled() && home.getKidsButtonElement().isEnabled(), "buttons are not enabled.");

    }

    
    @DataProvider(name = "bmiData")
    public Object[][] getBmiData1() throws IOException {
        return ExcelUtils.readExcelData("utils/TestData.xlsx", "BMIData");
    }
    
    @Test(priority = 7, dataProvider = "bmiData", description = "Test BMI calculation with valid inputs across different units")
    public void testBmiCalculationValidInputs(String height, String weight, String heightUnit, String weightUnit, String expectedResult) throws InterruptedException {
    	HomePage home = new HomePage(driver);
        home.navigateToKnowYourResults();

        KnowYourResultsPage bmiPage = new KnowYourResultsPage(driver);
        bmiPage.enterHeight(height);
        bmiPage.enterWeight(weight);
        bmiPage.selectHeightUnit(heightUnit);
        bmiPage.selectWeightUnit(weightUnit);
        bmiPage.clickCalculateBMI();
       
        String actualResult = bmiPage.getResultText();
        Assert.assertEquals(actualResult.trim(), expectedResult.trim(), "BMI result mismatch.");
        bmiPage.clickReset();
       
    }


    @Test(priority = 8, description = "Test health tips external links")
    public void testHealthTipsLinks() throws InterruptedException {
    	HomePage home = new HomePage(driver);
        home.navigateToHealthTips();
        HealthTipsPage tipsPage = new HealthTipsPage(driver);


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 600)");
        Thread.sleep(800);
        String originalWindowHandle = driver.getWindowHandle();
        testExternalLink(tipsPage.cdcLink, originalWindowHandle, "cdc.gov");
        js.executeScript("window.scrollBy(0, 800)"); // Scroll for next link if needed
        Thread.sleep(800);
        testExternalLink(tipsPage.whoLink, originalWindowHandle, "who.int");
        testExternalLink(tipsPage.nihLink, originalWindowHandle, "nih.gov");

      
    }

   private void testExternalLink(WebElement link, String originalWindowHandle, String expectedUrlContains) throws InterruptedException {

        HealthTipsPage health=new HealthTipsPage(driver);
        health.clickLink(link);
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);

        
        String newWindowHandle = "";
        for (String handle : windowHandlesList) {
            if (!handle.equals(originalWindowHandle)) {
                newWindowHandle = handle;
                break;
            }
        }
        driver.switchTo().window(newWindowHandle);

        System.out.println("Switched to: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlContains),
                "New tab URL does not contain expected domain: " + expectedUrlContains);

        driver.close(); 
        driver.switchTo().window(originalWindowHandle); 
    }
    
    @Test(priority = 9)
    public void clickFindDoctorLink() throws InterruptedException {
    	 HomePage home = new HomePage(driver);
         home.navigateToFindDoctor();

    }

    @Test(priority=10)
    public void testDoctorLinks() throws InterruptedException {

        FindDoctorPage doctorPage = new FindDoctorPage(driver);
        String originalWindow = driver.getWindowHandle();

        testDoctorLink(doctorPage.doctorLink1, originalWindow, "apollohospitals.com");
        testDoctorLink(doctorPage.doctorLink2, originalWindow, "mgmmalar.in");
        testDoctorLink(doctorPage.doctorLink3, originalWindow, "kauveryhospital.com");
        testDoctorLink(doctorPage.doctorLink4, originalWindow, "fortishealthcare.com");
        testDoctorLink(doctorPage.doctorLink5, originalWindow, "miotinternational.com");
    }

    private void testDoctorLink(org.openqa.selenium.WebElement link, String originalWindow, String expectedDomain) throws InterruptedException {
        FindDoctorPage doctor=new FindDoctorPage(driver);
    	JavascriptExecutor js = (JavascriptExecutor) driver;
   
        if(expectedDomain.equals("fortishealthcare.com") || expectedDomain.equals("miotinternational.com")) {
        	js.executeScript("window.scrollBy(0, 500)");
            Thread.sleep(800);
        }
      
        doctor.clickDocLink(link);


        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {
            List<String> windowHandlesList = new ArrayList<>(windowHandles);
            String newWindowHandle = "";
            for (String handle : windowHandlesList) {
                if (!handle.equals(originalWindow)) {
                    newWindowHandle = handle;
                    break;
                }
            }
            driver.switchTo().window(newWindowHandle);

            System.out.println("Switched to doctor link URL: " + driver.getCurrentUrl());
            Assert.assertTrue(driver.getCurrentUrl().contains(expectedDomain),
                    "Doctor link URL does not contain expected domain: " + expectedDomain);
            driver.close();
            driver.switchTo().window(originalWindow);

        } else {
            System.out.println("Doctor link did not open in new tab. Navigating back.");
            driver.navigate().back();
            Assert.assertEquals(driver.getCurrentUrl(), "https://karthi-553.github.io/BMI_Calculator/consult.html");
        }
    }
    @DataProvider(name = "queryData")
    public Object[][] getQueryData1() throws IOException {
        return ExcelUtils.readExcelData("utils/TestData.xlsx", "QueryData");
    }
    @Test(priority = 11, dataProvider = "queryData", description = "Test the Queries form with different inputs")
    public void testQueriesForm(String email, String queryText, String check) throws InterruptedException {
    	 HomePage home = new HomePage(driver);
         home.navigateToQueries();
         QueriesPage queriesPage = new QueriesPage(driver);
         queriesPage.enterEmail(email);
         queriesPage.enterQuery(queryText);
         queriesPage.clickSend();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String validationMessage = (String) js.executeScript(
        "return arguments[0].validationMessage;", queriesPage.emailInput);
	    String querymessage = (String) js.executeScript(
	    "return arguments[0].validationMessage;", queriesPage.queryTextarea);
	    boolean isEmailValid = validationMessage.isEmpty();
	    boolean isQueryValid = querymessage.isEmpty();
	    boolean allValid = isEmailValid && isQueryValid;
	    Assert.assertEquals(check, Boolean.toString(allValid));

    }
    
}