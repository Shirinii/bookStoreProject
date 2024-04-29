package bookStore.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bookStore.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	@FindBy(xpath = "//*[@placeholder='Name']")
	WebElement Name;

	@FindBy(xpath = "//*[@placeholder='Address Line 1']")
	WebElement Addressline1;

	@FindBy(xpath = "//*[@placeholder='Address Line 2']")
	WebElement Addressline2;

	@FindBy(xpath = "//*[@placeholder='Pincode']")
	WebElement Pincode;

	@FindBy(xpath = "//*[@placeholder='State']")
	WebElement State;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement Placeorder;

	@FindBy(xpath = "//*[contains(@class,'mdc-snackbar')]")
	WebElement ToastMsm;

	@FindBy(xpath = "//tfoot//th[2]")
	WebElement GrandTotal;

	@FindBy(xpath = "//tbody//tr//td[4]")
	List<WebElement> PriceList;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void insertShippingAdd(String name, String addressline1, String addressline2, String pincode, String state) {

		Name.sendKeys(name);
		Addressline1.sendKeys(addressline1);
		Addressline2.sendKeys(addressline2);
		Pincode.sendKeys(pincode);
		State.sendKeys(state);

	}

	public void placeOrder() {
		waitToElementBeAppear(Placeorder);
		Actions action = new Actions(driver);
		action.moveToElement(Placeorder).click().build().perform();

	}

	public String getToastMsm() {

		waitToSnackBarBeAppear(ToastMsm);
		String message = ToastMsm.getText();
		return message;
	}

	public int getGrandTotalOfOrder() {
		waitToElementBeAppear(GrandTotal);
		int grandtotal = Integer.valueOf(GrandTotal.getText().split(".00")[0].replaceAll("\\D", ""));
		return grandtotal;
	}

	public List<Integer> getPricelistOfOrder() {
		waitToAllElementsBeAppear(PriceList);
		List<Integer> list = PriceList.stream().map(i -> i.getText().split(".00")[0].replaceAll("\\D", ""))
				.map(Integer::parseInt).toList();
		return list;
	}

	public Boolean verifyGrnadTotal() {
		int sum = 0;
		for (int i = 0; i < getPricelistOfOrder().size(); i++) {
			sum += getPricelistOfOrder().get(i);
		}
		return sum == getGrandTotalOfOrder();

	}
}
