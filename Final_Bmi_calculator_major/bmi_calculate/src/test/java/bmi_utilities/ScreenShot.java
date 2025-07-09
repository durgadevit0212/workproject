package bmi_utilities;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
    public static WebDriver driver;
    public ScreenShot()
    {
    	
    }
    public ScreenShot(WebDriver driver)
    {
    	ScreenShot.driver=driver;
    }
	public  String captureScreen(String tname) throws IOException
	 {
		 File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 String filepath = "src/test/resource/screenshots/" + timeStamp() + tname + " newrep.png";
		 File dest = new File(filepath);
		 FileUtils.copyFile(src, dest);
		 return filepath;
	 }
	public static String timeStamp()
	{
	     return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	

}
