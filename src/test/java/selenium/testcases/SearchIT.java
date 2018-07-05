package selenium.testcases;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import selenium.SeleniumTestWrapper;
import selenium.pageobjects.SearchResultPage;
import selenium.pageobjects.ShowsPage;
import selenium.pageobjects.StartPage;
import utils.ExcelUtils;

public class SearchIT extends SeleniumTestWrapper {

	StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
	SearchResultPage searchResultPage = PageFactory.initElements(getDriver(), SearchResultPage.class);
	
	private final List<String> linkNames = Arrays.asList("FX", "National Geographic", "FOX Sports", "All Shows");

	@Before
	public void setup() {
		startPage.open();
		startPage.signIn();
	}	
	
	@Test
	public void testShows() {
			
		SearchResultPage searchResultPage = new SearchResultPage(getDriver());		
		searchResultPage.clickNavLink("Shows");
		
		ShowsPage showsPage = new ShowsPage(getDriver());
		List<String> showNames = showsPage.findLastShows();
		System.out.println("Last 4 shows on FOX page found: " + showNames);		
		ExcelUtils.writeExcel(showNames);

		HashMap<String, String> showsPages = findDuplicates(showNames);
		
		for (int row = 0; row < showNames.size(); row++) {
			if (showsPages.containsKey(showNames.get(row))) {
				ExcelUtils.writeExcelDuplicate(row + 1, showNames.get(row));
			}
		}
		
//		List<String> shows = ExcelUtils.readExcel();
//		System.out.println("From Excel: " + shows);
		
		assertThat("No duplicates", showsPages.isEmpty());

	}
	
	private HashMap<String, String> findDuplicates(List<String> shows) {
		HashMap<String, String> showsPages = new HashMap<String, String>();

		ShowsPage showsPage = new ShowsPage(getDriver());
		
		for (int row = 0; row < linkNames.size(); row++) {
			System.out.println("Navigating to page: " + linkNames.get(row));
			showsPage.clickNavLink(linkNames.get(row));
			showsPage = new ShowsPage(getDriver());

			for (String showName: shows) {
				if (showsPage.findShow(showName)) {
					if (showsPages.containsKey(showName)) {
						String pages = showsPages.get(showName);
						pages += "," + linkNames.get(row);
						showsPages.put(showName, pages);					
					} else {
						showsPages.put(showName, linkNames.get(row));						
					}
				}
			}
		}
		return showsPages;
	}
	
}
