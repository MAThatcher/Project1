package dev.thatcher.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.thatcher.pages.LoginPage;
import dev.thatcher.runners.LoginRunner;

public class LoginStepImpl {
	public static LoginPage loginPage = LoginRunner.loginPage;
	public static WebDriver driver = LoginRunner.driver;
	
	@Given("^The employee is on the Login page$")
	public void the_employee_is_on_the_Login_page() {
		driver.get("http://localhost:8080/Project1/HTML/index.html");
	}
	@When("^The employee enters his email$")
	public void the_employee_enters_his_email() {
		loginPage.email.sendKeys("Elon.Musk@DunderMifflen.com");
	}
	@When("^The employee enters his password$")
	public void the_employee_enters_his_password() {
		loginPage.password.sendKeys("password1");
	}
	@When("^The employee clicks on log in$")
	public void the_employee_clicks_on_log_in() throws InterruptedException {
		Thread.sleep(1500);
		loginPage.loginButton.click();
		Thread.sleep(9500);
	}
	@Then("^The employee should be on his Home Page$")
	public void the_employee_should_be_on_his_Home_Page() {
		Assert.assertEquals("Welcome Page",driver.getTitle());
	}
}
