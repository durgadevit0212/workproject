package com.selenium.eduviya_utility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
    	TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
        try {
            File dest = new File("src/test/resources/Screenshots" + timestamp() + " " + "Eduvidya.png");
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
	}
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

}
