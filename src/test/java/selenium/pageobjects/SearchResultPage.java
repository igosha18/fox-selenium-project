package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.Pages;
import utils.TestUtils;

public class SearchResultPage extends Pages {

	public SearchResultPage(final WebDriver driver) {
		super(driver);
	}

	private String link = ".//div[contains(@class,'Header_navLinks')]/a[text()='%s']";
	private String linkActive = ".//a[text()='%s' and contains(@class,'categoryItemActive')]";
		
	public void clickNavLink(String name){
		By linkXpath = By.xpath(String.format(link, name));
		By linkActiveXpath = By.xpath(String.format(linkActive, name));
		TestUtils.sleep(3000);
		waitForElement(linkXpath, 20);
		WebElement link = driver.findElement(linkXpath);
		link.click();
		waitForPageLoad(20);
		waitForElement(linkActiveXpath, 20);
	}

}
