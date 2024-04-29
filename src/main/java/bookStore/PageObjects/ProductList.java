package bookStore.PageObjects;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bookStore.AbstractComponents.AbstractComponent;

public class ProductList extends AbstractComponent {
	WebDriver driver;
	@FindBy(xpath = "//mat-card[contains(@class,'book-card')]")
	List<WebElement> productList;

	@FindBy(xpath = "//*[contains(@class,'mdc-snackbar')]")
	WebElement toastMsm;

	@FindBy(xpath = ".//*[contains(@class,'favourite')]")
	WebElement like;

	@FindBy(css = ".mdc-slider__track--active_fill")
	WebElement thumb;

	@FindBy(xpath = "//*[@type='range']")
	WebElement range;

	@FindBy(xpath = "//*[@type='search']")
	WebElement searchBox;

	@FindBy(xpath = "//*[@role='listbox']//mat-option[1]")
	WebElement searchOption;

	@FindBy(xpath = "//app-book-card//mat-card-content")
	List<WebElement> searchResultList;

	By cardButton = By.xpath(".//*[contains(text(),' Add to Cart')]");

	By likeBy = By.xpath(".//*[contains(@class,'favourite')]");
	Actions action ;

	public ProductList(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getProductList() {
		waitToAllElementsBeAppear(productList);
		return productList;
	}

	public WebElement getProductFromList(String productname) {

		WebElement prod = getProductList().stream()
				.filter(i -> i.findElement(By.xpath(".//a//strong")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addProducts(String productname) throws InterruptedException {

		action = new Actions(driver);
		action.moveToElement(getProductFromList(productname).findElement(cardButton)).click().build().perform();
		;

	}

	public String getToastMsm() {
		waitToSnackBarBeAppear(toastMsm);
		String message = toastMsm.getText();
		return message;

	}

	public void likeBooks(String productname) {
		action = new Actions(driver);
		waitToElementBeAppear(like);
		action.moveToElement(getProductFromList(productname).findElement(likeBy)).click().build().perform();
	}

	public int changePriceFiletr(int x, int y) {

		waitToElementBeAppear(thumb);
		action.moveToElement(thumb).clickAndHold().moveByOffset(x, y).release().perform();
		String maxValue = range.getAttribute("aria-valuetext");
		return Integer.valueOf(maxValue);

	}

	public List<String> getListOfBookPriceByFilter() {
		List<String> list = getProductList().stream()
				.map(i -> i.findElement(By.xpath(".//p")).getText().split(".00")[0].replaceAll("\\D", "")).toList();
		return list;

	}

	public List<Integer> isListOfPricesSmallerThanMaxValue(int maxValue) {

		List<Integer> filteredValues = getListOfBookPriceByFilter().stream().map(Integer::parseInt)
				.filter(num -> num > maxValue).collect(Collectors.toList());
		return filteredValues;
	}

	public void enterSearchKeyword(String word) {
		waitToElementBeAppear(searchBox);
		searchBox.sendKeys(word);
		searchOption.click();

	}

	public List<WebElement> getSearchResults(String word) {
		enterSearchKeyword(word);
		waitToAllElementsBeAppear(searchResultList);
		return searchResultList;
	}

	public Boolean verifySearchResult(String word) {

		Boolean match = getSearchResults(word).stream()
				.anyMatch(i -> i.findElement(By.xpath(".//strong")).getText().contains(word));
		return match;
	}
}
