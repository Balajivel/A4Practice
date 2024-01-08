package org.pageObject;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPojo extends BaseClass{
	public CartPojo() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[contains(@id,'cart')]")
	public WebElement cartButton;
	
	@FindBy(id = "checkout")
	public WebElement checkoutButton;
	

	@FindBy(id = "first-name")
	public WebElement firstNameField;
	
	@FindBy(id = "last-name")
	public WebElement lastNameField;
	
	@FindBy(id = "postal-code")
	public WebElement postalCode;
	
	@FindBy(id = "continue")
	public WebElement continueButton;
	
	@FindBy(xpath = "//div[contains(@class,'summary_total')]")
	public WebElement totalPrice;
}
