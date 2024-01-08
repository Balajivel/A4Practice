package org.test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.pageObject.CartPojo;
import org.pageObject.CheckoutPage;
import org.pageObject.HomePojo;
import org.pageObject.LoginPojo;
import org.pageObject.ProductPojo;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SauceDemoAssessment extends BaseClass {

	LoginPojo loginPojo;
	HomePojo homePojo;
	ProductPojo productPojo;
	CartPojo cartPojo;
	CheckoutPage checkoutPage;
	ExtentTest test;
	ExtentReports extentReports;
	
	@BeforeSuite
	public void setup() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("extentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		
		test = extentReports.createTest("Sauce Labs Test", "Functionality Validation");

	}

	@BeforeClass
	public void startLogin() {

		test.log(Status.INFO, "Starting test case for Sauce Demo site.");
		browserLaunch();
		test.pass("Chrome Browser Started");
		urlLaunch("https://www.saucedemo.com/");
		test.pass("Sauce Demo website launched");

		implicitWait();
		maxBrowser();
		test.pass("Browser Maximized");

		loginPojo = new LoginPojo();
		homePojo = new HomePojo();
		productPojo = new ProductPojo();
		cartPojo = new CartPojo();
		checkoutPage = new CheckoutPage();

	}

	@DataProvider(name = "Credentials")
	public Object[][] data() {
		return new Object[][] { { "standard_user", "secret_sauce" }, { "locked_out_user", "secret_sauce" }, {"abcccd", "secret_sauce"} };

	}

	@Test(dataProvider = "Credentials", description = "Multiple crendentail Combinations validation.")
	public void tc1(String user, String pass) {

		fillText(loginPojo.userName, user);
		fillText(loginPojo.passWord, pass);

		btnClick(loginPojo.loginBtn);

		String currentUrl = driver.getCurrentUrl();

		if (currentUrl.contains("inventory")) {
			//System.out.println("Username: "+user+ " ,Password: "+pass+" ->Successful Login");
			test.pass("Username: "+user+ " ,Password: "+pass+" ->Successful Login");
			btnClick(homePojo.menuButton);

			btnClick(homePojo.logoutButton);
		} else {
			//System.out.println("Username: "+user+ " ,Password: "+pass+" -> Error Message: "+loginPojo.errorMessage.getText());
			test.pass("Username: "+user+ " ,Password: "+pass+" -> Error Message: "+loginPojo.errorMessage.getText());
			driver.navigate().refresh();
		}

		

	}

	@Parameters({ "username", "password" })
	@Test(description = "Valid Login success and about us page launch")
	public void tc2(String user, String pass) {

		fillText(loginPojo.userName, user);
		fillText(loginPojo.passWord, pass);

		btnClick(loginPojo.loginBtn);

		btnClick(homePojo.menuButton);
		btnClick(homePojo.aboutButton);

		String currentUrl = driver.getCurrentUrl();

		Assert.assertEquals("https://saucelabs.com/", currentUrl);
		
		test.pass("Navigated to About us page after valid login");

	}

	
	@Test(description = "Adding highest value Product to the cart page")
	public void tc3() throws InterruptedException {

		browserBackNavi();

		String getttingText = getttingText(homePojo.pageTitle);

		Assert.assertEquals("Products", getttingText);

		Select se = new Select(productPojo.productSorting);
		se.selectByValue("hilo");

		List<WebElement> allProducts = productPojo.allProducts;
		btnClick(allProducts.get(0));

		btnClick(productPojo.addToCartButton);

		btnClick(cartPojo.cartButton);

		String gettingURL = gettingCurrentURL();

		Assert.assertTrue(gettingURL.contains("cart"));
		
		test.pass("Product with highest price is added to cart page");

	}
	
	@Parameters({ "firstName", "lastName", "zipcode" })
	@Test(description = "Verify checkout page contains total price in $xx.yy format")
	public void tc4(String firstName, String lastName, String zipcode){
		btnClick(cartPojo.checkoutButton);

		String getttingText1 = getttingText(homePojo.pageTitle);

		Assert.assertEquals("Checkout: Your Information", getttingText1);

		fillText(cartPojo.firstNameField, firstName);
		fillText(cartPojo.lastNameField, lastName);
		fillText(cartPojo.postalCode, zipcode);

		btnClick(cartPojo.continueButton);

		String getttingText2 = getttingText(homePojo.pageTitle);

		Assert.assertEquals("Checkout: Overview", getttingText2);

		String cartTotal = getttingText(cartPojo.totalPrice);
		int indexOfSpace = cartTotal.indexOf(' ');
		String substring = cartTotal.substring(indexOfSpace + 1);

		String pattern = "\\$\\d{2}\\.\\d{2}";

		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(substring);

		boolean matches = matcher.matches();

		if (matches == true) {
			System.out.println("Pattern Matches the value");
			Assert.assertTrue(true);

		} else {
			System.out.println("Pattern does not Matches the value");
			Assert.assertTrue(false);
		}
		
		test.pass("Verified the price format is according to $xx.yy format");

	}

	@Test(description = "Verify order successful messgae")
	public void tc5() {
		
		btnClick(checkoutPage.finishButton);
		
		Assert.assertTrue(checkoutPage.orderSuccessMessage.getText().contains("Thank you"));
		
		test.pass("Verified the Successful order placement");

	}
	
	@Test (description = "Logged out successfully")
	public void tc6() {
		btnClick(homePojo.menuButton);

		btnClick(homePojo.logoutButton);

		String currentUrl = driver.getCurrentUrl();

		Assert.assertTrue(currentUrl.contains("saucedemo"));
		
		test.pass("Successfully logged out");

	}

	@AfterClass
	public void closing() {
		closeBrowser();
		
		test.log(Status.INFO, "Ending Test for Sauce Demo site.");

	}
	
	@AfterSuite
	public void ending() {
		extentReports.flush();

	}
	
	

}
