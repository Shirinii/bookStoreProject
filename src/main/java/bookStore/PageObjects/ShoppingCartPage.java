package bookStore.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bookStore.AbstractComponents.AbstractComponent;

public class ShoppingCartPage extends AbstractComponent {

	@FindBy(xpath = "//tbody//tr/td[2]//a")
	List<WebElement> orderList;

	@FindBy(xpath = "//*[@ng-reflect-router-link='/checkout']")
	WebElement checkoutButton;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CheckoutPage goTocheckout() {
		Actions action = new Actions(driver);
		waitToElementBeAppear(checkoutButton);
		action.moveToElement(checkoutButton).click().build().perform();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}

	public List<WebElement> getOrderList() {

		waitToAllElementsBeAppear(orderList);
		return orderList;
	}

	public boolean verifyProduct(String productName) {

		Boolean match = getOrderList().stream().anyMatch(i -> i.getText().equals(productName));
		return match;
	}

}
