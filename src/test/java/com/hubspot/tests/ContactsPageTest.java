package com.hubspot.tests;

import java.util.Properties;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hubspot.base.BasePage;
import com.hubspot.pages.ContactsPage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;
import com.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initilaize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.gotoContactsPage();
	}
	@Test(priority=1)
	public void verifyContactPageTitle(){
		String title = contactsPage.getConctactsPageTitle();
		System.out.println("contact page title: "+ title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	//Hangi method ile datayi cekicem getContacData
	@Test(priority=2, dataProvider="getContactData")
	public void createNewPOMContactTest( String email, String firstname, String lastname,  String jobtitle) throws InterruptedException{
		contactsPage.createNewContact(email, firstname, lastname, jobtitle);
	}
	
	//TestNG nin icinde dataprovider keywordu var,bu keywordu assign etmek gerek.
		//Data Driven in connect oldugu kisim bu method
	
	@DataProvider()//Data Driven Test te yapmam gereken adim
	public Object[][] getContactData(){
		Object data[][] = ExcelUtil.getTestData("contact");
		return data;
	}

	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}
}

	//Allure Report alabiliyoruz, dependecy ekledigimiz icin.
	//Terminalden- cd- pwd yazdiktan sonra "allure-serve" yazarak reprt creat ediliyor.
	
	//2 farkli report/result alabiliriz. There are two different reportings.
	
	//Report = Test bittikten sonra alabiliriz. After test finished. We can have.
	//Listener = Testle beraber baslar, test suresince devam eder ve test bittiginde o da biter.
	//Listener testi dinliyor.
	



