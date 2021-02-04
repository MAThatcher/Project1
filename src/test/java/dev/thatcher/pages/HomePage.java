package dev.thatcher.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
public WebDriver driver;
	
	@FindBy(id = "newReimbursement")
	public WebElement newReimbursement;
	
	@FindBy(id = "viewReimbursements")
	public WebElement viewReimbursements;
	
	@FindBy(id = "viewPending")
	public WebElement viewPending;
	
	@FindBy(id = "logout")
	public WebElement logout;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
