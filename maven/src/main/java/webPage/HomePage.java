package webPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.SeleniumUtility;

public class HomePage extends SeleniumUtility {
	WebDriver driver;
	Select s;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "product_sort_container")
	private WebElement sortButton;

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement addToCartButton;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartButton;
	
	@FindBy(className ="cart_quantity")
	private WebElement orderDisplayed;

	public void clickOnSortButton() {
		clickOnElement(sortButton);
	}

	public void clickOnAtoZSort() {
		s = new Select(sortButton);
		s.selectByIndex(0);
	}

	public void clickOnZtoASort() {
		s = new Select(sortButton);
		s.selectByIndex(1);
	}

	public void clickOnPriceLowToHighSort() {
		s = new Select(sortButton);
		s.selectByIndex(2);
	}

	public void clickOnPriceHighToLowSort() {
		s = new Select(sortButton);
		s.selectByIndex(3);
	}

	public void addToCart() {
		clickOnElement(addToCartButton);
	}

	public void addToCarts() {
		clickOnElement(addToCartButton);
		clickOnElement(addToCartButton);
	}
	
	public void goToCartPage() {
		clickOnElement(cartButton);
	}
}