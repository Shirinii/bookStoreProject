package bookStoreTestingSuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import bookStore.PageObjects.LandingPage;
import bookStore.PageObjects.ProductList;
import bookStore.PageObjects.ShoppingCartPage;
import bookStore.PageObjects.CheckoutPage;

public class OrderBooks extends BaseTest {

	String productname = "Rot & Ruin";

	@Test(groups = { "SomkeTesting" })
	public void OrderingBooks() throws InterruptedException {

		ProductList productlist = landingpage.LoginPage("shirin", "shi12345678");
		productlist.addProducts(productname);
		Assert.assertEquals(productlist.getToastMsm(), "One Item added to cart");
		Thread.sleep(2000);
		ShoppingCartPage shoppingcart = productlist.goToShoppingCart();
		Assert.assertTrue(shoppingcart.verifyProduct(productname));
		CheckoutPage checkoutpage = shoppingcart.goTocheckout();
		checkoutpage.insertShippingAdd("shirin", "add1", "add2", "123456", "canada");
		checkoutpage.placeOrder();
		AssertJUnit.assertEquals(checkoutpage.getToastMsm(), "Order placed successfully!!!");

	}

	@Test
	public void VerifyOrderSummary() throws InterruptedException {

		ProductList productlist = landingpage.LoginPage("shirin", "shi12345678");
		productlist.addProducts(productname);
		AssertJUnit.assertEquals(productlist.getToastMsm(), "One Item added to cart");
		ShoppingCartPage shoppingcart = productlist.goToShoppingCart();
		CheckoutPage checkoutpage = shoppingcart.goTocheckout();
		checkoutpage.getGrandTotalOfOrder();
		checkoutpage.getPricelistOfOrder().toString();
		Assert.assertTrue(checkoutpage.verifyGrnadTotal());
	}

}