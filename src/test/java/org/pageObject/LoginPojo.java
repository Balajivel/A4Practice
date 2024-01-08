package org.pageObject;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPojo extends BaseClass {

	public LoginPojo() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='user-name']")
	public WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	public WebElement passWord;

	@FindBy(xpath = "//input[@id='login-button']")
	public WebElement loginBtn;
	
	@FindBy(xpath = "//button[@class='error-button']//parent::h3")
	public WebElement errorMessage;

}
