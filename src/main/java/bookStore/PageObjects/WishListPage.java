package bookStore.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bookStore.AbstractComponents.AbstractComponent;

public class WishListPage extends AbstractComponent {

	public WishListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
}
