package org.pageObject;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseClass{
	
	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "finish")
	public WebElement finishButton;
	
	@FindBy(xpath = "//h2[@class='complete-header']")
	public WebElement orderSuccessMessage;

}
