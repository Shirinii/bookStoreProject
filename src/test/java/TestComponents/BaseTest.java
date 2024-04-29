package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import bookStore.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Resources//GlobalData.properties");
		prop.load(fis);
		String browserName;
		String OsName;
		if (System.getProperty("Os") != null) {
			OsName = System.getProperty("Os");
		} else {
			OsName = prop.getProperty("Os");
		}
		if (System.getProperty("browser") != null) {
			browserName = System.getProperty("browser");
		} else {
			browserName = prop.getProperty("browser");
		}

		if (browserName.contains("chrome")) {
			if (OsName.contains("mac")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/chromedriver");
			} else {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\chromedriver.exe");
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);

		} else if (browserName.contains("firefox")) {

//			System.setProperty("webdriver.gecko.driver", "/Users/shirin/Desktop/geckodriver");
//			FirefoxOptions options = new FirefoxOptions();
//			if (browserName.contains("headless")) {
//				options.setHeadless(true);
		}
		// driver = new FirefoxDriver(options);

		driver.manage().window().maximize();
		return driver;
	}

	public String takeScreenshot(String screenshotName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
		String currentDate = format.format(date);

		String destinationPath = System.getProperty("user.dir") + "//screenshot//" + currentDate + ".png";
		File destination = new File(destinationPath);
		FileUtils.copyFile(source, destination);
		return destinationPath;

	}

	@BeforeMethod(alwaysRun = true)

	public LandingPage lunchApp() throws IOException, InterruptedException {

		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goToPage();
		landingpage.goToLoginPage();
		return landingpage;

	}

	@AfterMethod
	public void quitDrivers() {

		driver.quit();
	}

}