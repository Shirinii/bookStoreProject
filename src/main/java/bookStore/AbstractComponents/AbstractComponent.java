package bookStore.AbstractComponents;

import java.time.Duration;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import bookStore.PageObjects.ShoppingCartPage;
import bookStore.PageObjects.WishListPage;

public class AbstractComponent {

	public WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//mat-toolbar-row//span[contains(text(), 'Login')]")
	WebElement login;

	@FindBy(xpath = "//*[@ng-reflect-router-link='/shopping-cart']")
	WebElement checkout;

	@FindBy(xpath = "//*[@ng-reflect-router-link='/wishlist']")
	WebElement wishList;

//	@FindBy(xpath = "//mat-list-item//*[contains(text(), 'Mystery')]")
//	WebElement category;

	public void waitToElementBeAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitToSnackBarBeAppear(WebElement findBy) {
		WebDriverWait waitMsm = new WebDriverWait(driver, Duration.ofSeconds(7));
		waitMsm.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitToAllElementsBeAppear(List<WebElement> findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
	}

	public void goToLoginPage() {
		waitToElementBeAppear(login);
		login.click();
	}

	public ShoppingCartPage goToShoppingCart() {
		waitToElementBeAppear(checkout);
		checkout.click();
		ShoppingCartPage shoppingcart = new ShoppingCartPage(driver);
		return shoppingcart;

	}

//	public void changePriceFilter() {
//		waitToElementBeAppear(category);
//		category.click();
//	}

//	public WishListPage goToWishListPage() {
//
//		waitToElementBeAppear(wishList);
//		wishList.click();
//		WishListPage wishlist = new WishListPage(driver);
//		return wishlist;
//	}

}
