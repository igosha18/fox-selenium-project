package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.Pages;

public class StartPage extends Pages {

	private By accountLink = By.xpath(".//a[contains(@href,'/account/')]");
	private By signInLink = By.xpath(".//div[contains(@class,'Account_perksButtonContainer')]/button[contains(@class,'Account_signIn')]");
	private By signInButton = By.xpath(".//div[contains(@class,'Account_signinButtonDesktop')]/button[text()='Sign In']");
	private By signInName = By.xpath(".//input[contains(@class,'Account_signinField') and @type='email']");
	private By signInPassword = By.xpath(".//input[contains(@class,'Account_signinField') and @type='password']");
	private By showsLink = By.xpath(".//a[contains(@href,'/shows/')]");

	public StartPage(final WebDriver driver) {
		super(driver);
	}

	public void open(){
		driver.manage().window().maximize();
		super.open();
	}

	public void signIn(){
		waitForElement(accountLink, 20);
		WebElement link = driver.findElement(accountLink);
		link.click();
		waitForPageLoad(20);
		waitForElement(signInLink, 20);
		driver.findElement(signInLink).click();
		waitForPageLoad(20);
		
		waitForElement(signInButton, 20);
		WebElement name = driver.findElement(signInName);
		String userName = getTestData("user.name");
		name.click();
		name.sendKeys(userName);

		WebElement pwd = driver.findElement(signInPassword);
		String password = getTestData("user.password");
		pwd.click();
		pwd.sendKeys(password);

		driver.findElement(signInButton).click();
		waitForPageLoad(20);
		waitForElement(showsLink, 20);

	}
}
