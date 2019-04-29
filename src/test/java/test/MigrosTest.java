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
	
	@AfterTest // finish the test
	public void TestDown() {
		// close browser

	 driver.close(); driver.quit();

		// System.out.println("Test Completed Successfully");
	}

	

}
