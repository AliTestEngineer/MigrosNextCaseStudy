package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class MigrosPageObject {

	WebDriver driver;
	WebElement element;



	By loginButton = By.xpath("//a[@id='membership-login-link']");
	By InputMail = By.xpath("//form[@id='signInForm']//input[@placeholder='E-Posta Adresi']");
	By InputPassword = By.xpath("//form[@id='signInForm']//input[@placeholder='Þifre']");
	By SubmitButton = By.xpath("//button[contains(text(),'Giriþ Yap')]");
	

	public MigrosPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void GetURL(String url) {
		driver.get(url);

	}

	

}
