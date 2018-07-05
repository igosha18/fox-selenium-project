package selenium.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.Pages;

public class ShowsPage extends Pages {

	public ShowsPage(final WebDriver driver) {
		super(driver);
	}
	
	private String link = ".//a[text()='%s']";
	private String linkActive = ".//a[text()='%s' and contains(@class,'PageHeaderBrowse_active')]";
		

	private By showTitles = By.xpath(".//a[contains(@class,'MovieTile_title')]"
			                     + "//div[contains(@class,'MovieTile_titleText')]");
	
	private String showTitlePath = ".//a[contains(@class,'MovieTile_title')]"
			                      + "//div[contains(@class,'MovieTile_titleText') and text()='%s']";


	public List<String> findLastShows() {
		waitForPageLoad(20);
		waitForElement(showTitles,20);
		List<String> names = driver.findElements(showTitles)
				.stream().map(webEl -> webEl.getText())
				.collect(Collectors.toList());
		return names.subList(names.size() - 4, names.size());
	}

	public boolean findShow(String showTitle) {
		By showTitleXpath = By.xpath(String.format(showTitlePath, showTitle));
		waitForPageLoad();
		waitForElement(showTitles,20);
		return driver.findElements(showTitleXpath).size() > 0;
	}
	
	public void clickNavLink(String name){
		By linkXpath = By.xpath(String.format(link, name));
		By linkActiveXpath = By.xpath(String.format(linkActive, name));
		waitForElement(linkXpath, 20);
		WebElement link = driver.findElement(linkXpath);
		link.click();
		waitForPageLoad(20);
		waitForElement(linkActiveXpath, 20);
	}
}
