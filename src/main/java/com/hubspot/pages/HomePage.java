package com.hubspot.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	//*[@class='private-page__title']===header
	//*[@class='account-name '] // accountname
	
	//Locators 
	By header=By.xpath("//*[@class='private-page__title']");
	By accountName=By.xpath("//*[@class='account-name ']");
	//By contactLoginBtn=By.xpath("//*[@class='cls-3']");
	By contactMainTab = By.id("nav-primary-contacts-branch");
	By contatcChildTab = By.id("nav-secondary-contacts");
	
	
	//Constructor
	public HomePage(WebDriver driver){
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	
	//Title, Header ve AccountName methodlari (Action Method)
	
	//Title
	public String getHomePageTitle(){
		return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
		//return driver.getTitle();
	}
	
	//Header find
	public String getHomePageHeader(){
	return elementUtil.doGetText(header);
	//return driver.findElement(header).getText();
	}
	
	//AccountName find
	public String verifyLoggedInAccountName(){
		return elementUtil.doGetText(accountName);
		//return driver.findElement(accountName).getText();
	}
	
	//Contact click
	
	//public void ContactLoginBtn(){
	//driver.findElement(contactLoginBtn).click();
	//}
	
	
	//helper method
			private void clickOnContacts(){
				elementUtil.doClick(contactMainTab);
				elementUtil.doClick(contatcChildTab);
			}
			
			public ContactsPage gotoContactsPage(){
				clickOnContacts();
				return new ContactsPage(driver);
			}		
	
	
}
