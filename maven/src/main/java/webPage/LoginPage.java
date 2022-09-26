package webPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtility;


public class LoginPage extends SeleniumUtility{
	WebDriver driver;
	

	public LoginPage(WebDriver driver) {
	this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement userNameInputField;

	@FindBy(id = "password")
	private WebElement pwdInputField;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	public void login(String userName, String pwd) {
		typeInput(userNameInputField, userName);
		typeInput(pwdInputField, pwd);
		clickOnElement(loginButton);
	}
}
