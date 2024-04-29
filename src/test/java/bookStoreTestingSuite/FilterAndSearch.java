package bookStoreTestingSuite;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import bookStore.PageObjects.ProductList;

public class FilterAndSearch extends BaseTest {

	int xOffset = -100;
	int yOffset = -100;
	String word = "Slayer";

//	@Test
//	public void FilterBooksByPrice() {
//
//		ProductList productlist = landingpage.LoginPage("shirin", "shi12345678");
//		int max = productlist.changePriceFiletr(xOffset, yOffset);
//		Assert.assertTrue(productlist.isListOfPricesSmallerThanMaxValue(max).isEmpty());
//
//	}

	@Test(groups = { "SomkeTesting" })
	public void SearchBooks() {

		ProductList productlist = landingpage.LoginPage("shirin", "shi12345678");
		Assert.assertTrue(productlist.verifySearchResult(word));

	}
}
