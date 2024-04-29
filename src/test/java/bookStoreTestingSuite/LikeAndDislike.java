package bookStoreTestingSuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import bookStore.PageObjects.ProductList;

public class LikeAndDislike extends BaseTest {
	String productname = "Rot & Ruin";

	@Test
	public void LikeBooks() throws InterruptedException {
		ProductList productlist = landingpage.LoginPage("shirin", "shi12345678");
		productlist.likeBooks(productname);
		Assert.assertEquals(productlist.getToastMsm(), "Item added to your Wishlist");
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "LikeBooks" })
	public void DislikeBooks() {
		ProductList productlist = landingpage.LoginPage("shirin", "shi12345678");
		productlist.likeBooks(productname);
		Assert.assertEquals(productlist.getToastMsm(), "Item removed from your Wishlist");

	}

}
