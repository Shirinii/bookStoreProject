package bookStore.PageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bookStore.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	@FindBy(xpath = "//*[@placeholder='Username']")
	WebElement userName;

	@FindBy(xpath = "//*[@placeholder='Password']")
	WebElement passWord;

	@FindBy(xpath = "//mat-card-actions//span[contains(text(),'Login')]")
	WebElement login;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToPage() throws InterruptedException {
		driver.get("https://bookcart.azurewebsites.net");
	}

	public ProductList LoginPage(String username, String password) {
		userName.sendKeys(username);
		passWord.sendKeys(password);
		login.click();
		ProductList productlist = new ProductList(driver);
		return productlist;

	}
}
