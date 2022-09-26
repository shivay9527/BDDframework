package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtility {
	
		public static WebDriver driver = null;
		protected Actions action;

		public WebDriver setUp(String browser, String url) {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			}

			if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}

			if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}

			if (browser.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}

			if (browser.equalsIgnoreCase("opera")) {
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
			}

			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.get(url);

			return driver;
		}

		public WebDriver getDriver() {
			return driver;
		}

		public String readPropFile(String Key) {
			Properties properties = new Properties();
			try {
				FileInputStream fis = new FileInputStream(
						"C:\\Users\\maintenance.utility\\eclipse-workspace\\maven\\src\\main\\resources\\data.properties");
				properties.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return properties.getProperty(Key);
		}

		public void typeInput(WebElement element, String input) {
			waitForElementDisplayed(element);
			element.clear();
			element.sendKeys(input);
		}

		public void clickOnElement(WebElement element) {
			waitForElementToBeClickable(element);
			element.click();
		}

		public void performMouseOverOperation(WebElement element) {
			action.moveToElement(element).perform();
		}

		public void performRightClickOperation(WebElement element) {
			action.moveToElement(element).contextClick().build().perform();
		}

		public void performDragAndDrop(WebElement source, WebElement target) {
			action.dragAndDrop(source, target).build().perform();
		}

		public void takeScreenShotOfThePage(String location) {
			// downcast the driver to access TakesScreenshot method
			TakesScreenshot ts = (TakesScreenshot) driver;
			// capture screenshot as output type FILE
			File file = ts.getScreenshotAs(OutputType.FILE);
			try {
				// save the screenshot taken in destination path
				FileUtils.copyFile(file, new File(location));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Method to get the title of current page
		 */
		public String getCurrentTitleOfApplication() {
			return driver.getTitle();
		}

		/**
		 * Method to get the current url of the application
		 */
		public String getCurrentUrlOfApplication() {
			return driver.getCurrentUrl();
		}

		public boolean isElementExist(WebElement element) {
			waitForElementDisplayed(element);
			return element.isDisplayed();
		}

		public boolean isCheckBoxSelected(WebElement element) {
			waitForElementDisplayed(element);
			return element.isSelected();
		}

		/**
		 * Utility to handle HTML dropdown list
		 */
		protected void handleHtmlDropdownListWithVisibleText(WebElement element, String visibileText) {
			waitForElementDisplayed(element);
			Select select = new Select(element);
			select.selectByVisibleText(visibileText);
		}

		/**
		 * Utility to handle HTML dropdown list
		 */
		protected void handleHtmlDropdownListWithIndex(WebElement element, int index) {
			waitForElementDisplayed(element);
			Select select = new Select(element);
			select.selectByIndex(index);
		}

		/**
		 * Utility to handle HTML dropdown list
		 */
		protected List<WebElement> getHtmlDropdownListSize(WebElement element) {
			waitForElementDisplayed(element);
			Select select = new Select(element);
			return select.getOptions();
		}

		/**
		 * Utility to handle HTML dropdown list
		 */
		protected WebElement getFirstSelectedOptionFromHtmlDropdownList(WebElement element) {
			waitForElementDisplayed(element);
			Select select = new Select(element);
			return select.getFirstSelectedOption();
		}

		/**
		 * Utility to handle HTML dropdown list
		 */
		protected List<WebElement> getAllSelectedOptionFromMultiSelectDropdownList(WebElement element) {
			waitForElementDisplayed(element);
			Select select = new Select(element);
			return select.getAllSelectedOptions();
		}

		/**
		 * Utility to handle iframes
		 */
		protected void switchToIFrameWithWebElement(WebElement element) {
			waitForElementDisplayed(element);
			driver.switchTo().frame(element);
		}

		/**
		 * Utility to handle iframes
		 */
		protected void switchToIFrameWithIndex(int index) {
			driver.switchTo().frame(index);
		}

		/**
		 * Utility to handle iframes
		 */
		protected void switchFromIFrameToMainPage() {

			driver.switchTo().defaultContent();
		}

		/**
		 * This is sleep method from java only use it when uttermost required
		 * 
		 * @param millis time in mili seconds
		 */
		protected void setSleepTime(long millis) {

			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {

			}
		}

		/**
		 * Method to refresh Page
		 */
		protected void refreshPage() {

			driver.navigate().refresh();

		}

		/**
		 * Method to wait for an element till it's not display .
		 * 
		 * @param by
		 */
		public void waitForElementDisplayed(WebElement element) {

			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
		}

		/**
		 * Method to wait for an element till it's not clickable.
		 * 
		 * @param by
		 */
		public void waitForElementToBeClickable(WebElement element) {

			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(element));
		}

		public List<String> getDropDownOptions(List<WebElement> list) {
			List<String> strList = new ArrayList<String>();
			for (WebElement e : list) {
				strList.add(e.getText());
			}
			return strList;
		}

		public void cleanUp() {
			driver.close();
		}

	}
	
