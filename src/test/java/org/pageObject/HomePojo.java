package org.pageObject;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePojo extends BaseClass{
	
	public HomePojo() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(@id,'menu-btn')]")
	public WebElement menuButton;

	@FindBy(xpath = "//a[text()='About']")
	public WebElement aboutButton;
	
	@FindBy(className = "title")
	public WebElement pageTitle;
	
	@FindBy(id = "logout_sidebar_link")
	public WebElement logoutButton;
}
