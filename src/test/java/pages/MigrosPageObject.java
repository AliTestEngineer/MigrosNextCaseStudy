
package pages;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class MigrosPageObject {

	WebDriver driver;
	WebElement element;
	WebDriverWait wait;


	By loginButton = By.xpath("//a[@id='membership-login-link']");
	By InputMail = By.xpath("//form[@id='signInForm']//input[@placeholder='E-Posta Adresi']");
	By InputPassword = By.xpath("//form[@id='signInForm']//input[@placeholder='Şifre']");
	By SubmitButton = By.xpath("//button[contains(text(),'Giriş Yap')]");
	By closeCookies = By.xpath("//*[@id=\"cookie-popup\"]/div[1]");
	By PickItem = By.xpath("//a[contains(text(),'Dana Yemeklik Et Kg')]");
	By ProductDetailNote = By.xpath("//textarea[@id='product-detail-note-textarea']");
	By SaveProductNote = By.xpath("//button[@id='product-detail-add-note-button']");
	By SubmitCartButton = By.xpath("//div[@class='product-detail-center--actions single-select']//button[@class='button orange add-basket product-card-button show'][contains(text(),'Sepete Ekle')]");
	By CloseBanner = By.xpath("//div[@id='cartCampaignModal']//button[@class='close'][contains(text(),'×')]");
	By MinimumRequirePrice = By.xpath("//span[@class='minimum-required-price']");
	By TotalCartValue = By.xpath("//span[@id='summaryRevenue']");
	By AddProductPlus = By.xpath("//div[@class='add-product show']//button[@class='part product-plus']");
	By ConfirmCart = By.xpath("//button[@id='in-cart-next-button']");
	By ConfirmCartValue = By.xpath("//span[@id='summaryRevenue']");

	public MigrosPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void GetURL(String url) {
		driver.get(url);

	}
	
	public void loginToMigros(String email, String password) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(loginButton).click();
		Thread.sleep(3000);
		driver.findElement(InputMail).sendKeys(email);
		driver.findElement(InputPassword).sendKeys(password);
		driver.findElement(SubmitButton).click();

	}

	public void AddItemtoCart() throws InterruptedException {

		
		Thread.sleep(5000);

		driver.navigate().to("https://www.migros.com.tr/kirmizi-et-c-68");
		Thread.sleep(2000);
		driver.findElement(closeCookies).click();

		wait = new WebDriverWait(driver, 20);
		element = wait.until(ExpectedConditions.elementToBeClickable(PickItem));

		element.click();

		driver.findElement(ProductDetailNote).sendKeys("120 gramlık paketler şeklinde hazırlanmasını istiyorum");
		driver.findElement(SaveProductNote).click();
		driver.findElement(SubmitCartButton).click();

	}

	public void ProductInCart() throws InterruptedException {
		driver.navigate().to("https://www.migros.com.tr/sepetim");
		driver.findElement(CloseBanner).click();

		String minimum_price = driver.findElement(MinimumRequirePrice).getText().substring(24, 29);
		int minimum_price_i = Integer.parseInt(minimum_price.replaceAll("\\D", ""));

		String cart_total = driver.findElement(TotalCartValue).getText().substring(0, 5);
		int cart_total_i = Integer.parseInt(cart_total.replaceAll("\\D", ""));
		Thread.sleep(4000);

		while (cart_total_i <= minimum_price_i) {

			driver.findElement(AddProductPlus).click();
			Thread.sleep(4000);
			cart_total = driver.findElement(TotalCartValue).getText().substring(0, 5);
			cart_total_i = Integer.parseInt(cart_total.replaceAll("\\D", ""));

		}

		Thread.sleep(2000);
		driver.findElement(ConfirmCart).click();

		String cart_after = driver.findElement(ConfirmCartValue).getText().substring(0, 5);
		int cart_after_i = Integer.parseInt(cart_after.replaceAll("\\D", ""));

		Assert.assertEquals(cart_total_i, cart_after_i);

	}

	

}

