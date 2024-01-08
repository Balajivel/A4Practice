package org.pageObject;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPojo extends BaseClass{
	
	public ProductPojo() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "product_sort_container")
	public WebElement productSorting;
	
	@FindBy(xpath = "//div[@class='inventory_item']//div[@class='inventory_item_name ']")
	public List<WebElement> allProducts;
	
	@FindBy(xpath = "//button[contains(@id,'cart')]")
	public WebElement addToCartButton;

}
