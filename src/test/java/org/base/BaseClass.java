package org.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
public static	WebDriver driver;
	
	public static void browserLaunch() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();

	}
	
	public static void urlLaunch(String url) {
		
		driver.get(url);

	}
	
	public static void fillText(WebElement ele,String value) {
		ele.sendKeys(value);
		

	}
	
	public static void btnClick(WebElement cli) {
		cli.click();

	}
	
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
	
	public static void maxBrowser() {
		driver.manage().window().maximize();

	}
	
	public static void browserBackNavi() {
		driver.navigate().back();

	}
	
	public static void gettingAttribute(WebElement ele, String name) {
		ele.getAttribute(name);

	}
	
	public static String getttingText(WebElement ele) {
		String text = ele.getText();
		return text;

	}
	
	public static String gettingCurrentURL() {
		String url = driver.getCurrentUrl();
		return url;

	}

	public static void closeBrowser() {
		driver.quit();

	}
}
