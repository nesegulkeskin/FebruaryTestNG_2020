package com.hubspot.tests;

import java.util.Properties;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;



public class HomePageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basepage;
	LoginPage loginPage;
	HomePage homePages;
	
	
	
	@BeforeMethod
	public void setUp(){
		basepage=new BasePage();
		prop=basepage.initilaize_properties();
		driver=basepage.initialize_driver(prop);
		loginPage=new LoginPage(driver);
		homePages=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		//LoginPage sayfasindaki doLogin methodunu homepages e esitledim.
		
		//Yukariya dikkat et.....
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		
		}
	

	
	@Test(priority=1, description="home page title")

	public void verifyHomePage(){
		String title=homePages.getHomePageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE );
	}
	
	@Test(priority=2, description="home page header")
	public void verifyHomePages(){
		String header= homePages.getHomePageHeader();//string doner
		System.out.println(header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER );
		
	}
	
	//@Test(priority=1)
	//	public void LoginBtn(){
	//	homePages.ContactLoginBtn();
	//}
	
	@Test(priority=3, description="home page account verify")
	public void verifyAccounName(){
		String accountName= homePages.verifyLoggedInAccountName();
		System.out.println(accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}
	
	
	@AfterMethod
	public void tearDown(){
		basepage.quitBrowser();
		//driver.quit();
	}

}
