
package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.MigrosPageObject;

public class MigrosTest {

	WebDriver driver;
	WebElement element;

	@BeforeTest
	public void Setup() // setup the test
	{
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test(priority = 1) // test to open main page
	public void openMainPage() 
	{
		MigrosPageObject open = PageFactory.initElements(driver, MigrosPageObject.class);
		open.GetURL("https://www.migros.com.tr/");

	}
	
	@Test(priority = 2)// test to login with defines username and password
	public void LoginToPage() throws InterruptedException {
		MigrosPageObject login = PageFactory.initElements(driver, MigrosPageObject.class);
		login.loginToMigros("keep.alive.me@gmail.com", "test123");

	}

	@Test(priority = 3)// test to add item to cart
	public void AddItemtoCart() throws InterruptedException {
		MigrosPageObject add = PageFactory.initElements(driver, MigrosPageObject.class);
		add.AddItemtoCart();

	}

	@Test(priority = 4)// test to buy product and validate total amount of cart
	public void BuyProduct() throws InterruptedException {
		MigrosPageObject buy = PageFactory.initElements(driver, MigrosPageObject.class);
		buy.ProductInCart();

	}
	
	@AfterTest // finish the test
	public void TestDown() {
		// close browser

	 driver.close(); 
	 driver.quit();
	 
	 

		// System.out.println("Test Completed Successfully");
	}

	

}

