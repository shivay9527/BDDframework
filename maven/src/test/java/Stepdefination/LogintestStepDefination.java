package Stepdefination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import utilities.SeleniumUtility;
import webPage.HomePage;
import webPage.LoginPage;

public class LogintestStepDefination extends SeleniumUtility {

	public static WebDriver driver = null;
	static String url, userName, pwd;
	LoginPage loginPage;
	HomePage homePage;

	@Given("this is given for background")
	public void this_is_given_for_background() {
		System.out.println("this is given for background");
	}

	@Then("this is then for background")
	public void this_is_then_for_background() {
		System.out.println("this is then for background");
	}

	@Given("User has application url")
	public void user_has_application_url() {
		url = readPropFile("appUrl");
//		System.out.println(url);
	}

	@Given("User has username")
	public void user_has_username() {
		userName = readPropFile("username");
//		System.out.println(userName);
	}

	@Given("user has password")
	public void user_has_password() {
		pwd = readPropFile("password");
//		System.out.println(pwd);
	}

	@When("User open browser and open url")
	public void User_open_browser_and_open_url() {
		String browserName = readPropFile("browser");
//		System.out.println(browserName);
		// String appUrl = readPropFile("url");
		driver = setUp(browserName, url);
	}

	@When("User enter username and password and user click on login button")
	public void user_enter_username_and_password_and_user_click_on_login_button() {
		loginPage = new LoginPage(driver);
		loginPage.login(userName, pwd);
	}

	@Then("User navigates to home page")
	public void user_navigates_to_home_page() {
		// WebElement homePageElement =
		// driver.findElement(By.xpath("//span[text()='Products']"));
		// Assert.assertTrue(homePageElement.isDisplayed());
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
	}

	@Given("User is on the home page")
	public void user_is_on_the_home_page() {
		homePage = new HomePage(driver);
	}

	@When("User click on the sort button")
	public void user_click_on_the_sort_button() {
		homePage.clickOnSortButton();
	}

	@When("user click on Name \\(A to Z) sort")
	public void user_click_on_Name_A_to_Z_sort() {
		homePage.clickOnAtoZSort();
	}

	@Then("Product will get sorted in alphbetical ascending order")
	public void product_will_get_sorted_in_alphbetical_ascending_order() {
		List<WebElement> inventoryOptions1 = driver
				.findElements(By.xpath("//div[@id='inventory_container']//div[@class='inventory_item_name']"));
		List<String> inventoryOptionsString = getDropDownOptions(inventoryOptions1);
		List<String> inventoryOptionsSorted = getDropDownOptions(inventoryOptions1);
		Collections.sort(inventoryOptionsSorted);
		Assert.assertTrue(inventoryOptionsString.equals(inventoryOptionsSorted));
	}

	@When("user click on Name \\(Z to A) sort")
	public void user_click_on_Name_Z_to_A_sort() {
		homePage.clickOnZtoASort();
	}

	@Then("Product will get sorted in alphbetical descending order")
	public void product_will_get_sorted_in_alphbetical_descending_order() {
		List<WebElement> inventoryOptions1 = driver
				.findElements(By.xpath("//div[@id='inventory_container']//div[@class='inventory_item_name']"));
		List<String> inventoryOptionsString = getDropDownOptions(inventoryOptions1);
		List<String> inventoryOptionsSorted = getDropDownOptions(inventoryOptions1);
		Collections.sort(inventoryOptionsSorted, Collections.reverseOrder());
		Assert.assertTrue(inventoryOptionsString.equals(inventoryOptionsSorted));
	}

	@When("user click on Price \\(low to high) sort")
	public void user_click_on_Price_low_to_high_sort() {
		homePage.clickOnPriceLowToHighSort();
	}

	@Then("Product will get sorted in price ascending order")
	public void product_will_get_sorted_in_price_ascending_order() {
		List<WebElement> inventoryOptions1 = driver
				.findElements(By.xpath("//div[@id='inventory_container']//div[@class='inventory_item_price']"));
		List<String> inventoryOptionsString = getDropDownOptions(inventoryOptions1);
		List<Integer> inventoryOptionsInteger = new ArrayList<Integer>();
		List<Integer> inventoryOptionsInteger1 = new ArrayList<Integer>();
		for (String s : inventoryOptionsString) {
			inventoryOptionsInteger.add((int) Double.parseDouble(s.substring(1)));
			inventoryOptionsInteger1.add((int) Double.parseDouble(s.substring(1)));
		}
		Collections.sort(inventoryOptionsInteger);
		Assert.assertTrue(inventoryOptionsInteger.equals(inventoryOptionsInteger1));
	}

	@When("user click on Price \\(high to low) sort")
	public void user_click_on_Price_high_to_low_sort() {
		homePage.clickOnPriceHighToLowSort();
	}

	@Then("Product will get sorted in price descending order")
	public void product_will_get_sorted_in_price_descending_order() {
		List<WebElement> inventoryOptions1 = driver
				.findElements(By.xpath("//div[@id='inventory_container']//div[@class='inventory_item_price']"));
		List<String> inventoryOptionsString = getDropDownOptions(inventoryOptions1);
		List<Integer> inventoryOptionsInteger = new ArrayList<Integer>();
		List<Integer> inventoryOptionsInteger1 = new ArrayList<Integer>();
		for (String s : inventoryOptionsString) {
			inventoryOptionsInteger.add((int) Double.parseDouble(s.substring(1)));
			inventoryOptionsInteger1.add((int) Double.parseDouble(s.substring(1)));
		}
		Collections.sort(inventoryOptionsInteger, Collections.reverseOrder());
		Assert.assertTrue(inventoryOptionsInteger.equals(inventoryOptionsInteger1));
	}

}