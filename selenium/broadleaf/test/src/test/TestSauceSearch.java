package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tools.PageAddress;

import com.testify.ecfeed.junit.StaticRunner;
import com.testify.ecfeed.junit.annotations.EcModel;

@RunWith(StaticRunner.class)
@EcModel("src/model.ect")
public class TestSauceSearch extends ParentTest{
	
	public TestSauceSearch(){
		fBaseUrl = PageAddress.BASE;
	}

	@Test
	public void testSearchSuccess(String keywords, boolean expected) throws Exception {
		try {
			setUp();
			fDriver.get(fBaseUrl);
			fDriver.findElement(By.name("q")).clear();
			fDriver.findElement(By.name("q")).sendKeys(keywords);
			fDriver.findElement(By.id("search_button")).click();
			
			if(expected){
				assertTrue("Expected results, found nothing", isElementPresent(By.xpath("id('products')/li[1]/div[1]/a")) 
						|| isElementPresent(By.xpath("id('products')/li[1]/div[2]/a")));
			} else {
				assertFalse("Expected nothing",isElementPresent(By.xpath("id('products')/li[1]/div[1]/a")) 
						|| isElementPresent(By.xpath("id('products')/li[1]/div[2]/a")));
			}
		} finally {
			tearDown();
		}
	}
	
	@Test
	public void testSearchKeywords(String keywords) throws Exception {
		try {
			setUp();
			fDriver.get(fBaseUrl);

			fDriver.findElement(By.name("q")).clear();
			fDriver.findElement(By.name("q")).sendKeys(keywords);
			fDriver.findElement(By.id("search_button")).click();
			
			int page = 1;
			int position = 1;
			while(page > 0){
				while(position > 0){
					if(isElementPresent(By.xpath("id('products')/li["+position+"]/div[1]/a"))){
						assertTrue("not found any keyword in resulting page!",
								openInNewTabAndScanForKeywords(fDriver.findElement(By.xpath("id('products')/li["+position+"]/div[1]/a")), keywords));
						position +=1;
					} else if(isElementPresent(By.xpath("id('products')/li["+position+"]/div[2]/a"))){
						assertTrue("not found any keyword in resulting page!",
								openInNewTabAndScanForKeywords(fDriver.findElement(By.xpath("id('products')/li["+position+"]/div[2]/a")), keywords));
						position +=1;
					} else {
						position = 1;
						page += 1;
						break;
					}	
				}
				if(isElementPresent(By.xpath("id('left_column')/div/ul/li["+page+"]/a"))){
					fDriver.findElement(By.xpath("id('left_column')/div/ul/li["+page+"]/a")).click();
					position = 1;
				} else{
					break;
				}
			}		
		}
		finally {	
			tearDown();
		}
	}
	
	protected boolean openInNewTabAndScanForKeywords(WebElement element, String keywords){
		boolean containsAny = false;
		WebDriver secondDriver = tools.DriverFactory.getDriver();
		String[] searchKeys = keywords.split("\\s+");
		try{
			secondDriver.navigate().to(element.getAttribute("href"));
			for(String s: searchKeys){
				if(StringUtils.containsIgnoreCase(secondDriver.findElement(By.id("description")).getText(), s)
						|| StringUtils.containsIgnoreCase(secondDriver.findElement(By.cssSelector("h2")).getText(), s)){
					containsAny = true;
					break;
				}				
			}

		} finally{
			secondDriver.close();
		}

		return containsAny;
	}
	

}
