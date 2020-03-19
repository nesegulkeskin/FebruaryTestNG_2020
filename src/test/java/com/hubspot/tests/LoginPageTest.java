package com.hubspot.tests;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.hubspot.base.BasePage;
import com.hubspot.pages.LoginPage;


@Listeners(com.hubspot.listeners.TestAllureListener.class)
public class LoginPageTest {
	
	//LOG4 icin = mutlaka bu import edilmeli, yanlis olmamali
	//import org.apache.log4j.Logger;
	
	Logger log=Logger.getLogger("LoginPageTest");
	
	WebDriver driver;
	Properties prop; 
	//config file ni kullanabilmek icin initilaze yapiyoruz prop u ve 
	//Webdriver in altina dahil ediyoruz.
	//Beforemethodu etkiler.
	
	BasePage basePage;
	LoginPage loginPage;
	
	//basepage i propa esitliyoruz.
	//properties prop u yazdiktan sonra buranin icine prop yazdik ama hata verdi, 
	//baspage gidip webdriver icini properties prop yapinca hata duzeldi.
	//properties prop u ekledikten sonra diyorum ki;
	//once prop a git,ordan url i al,sonra browseri al ve testi baslat diyecegim.
	
	@BeforeMethod
	public void setUp(){
		basePage=new BasePage();
		log.info("Browser is launching...");
		prop=basePage.initilaize_properties();
		driver=basePage.initialize_driver(prop);
		loginPage=new LoginPage(driver);
		}
	
	@Test(priority=1, enabled=true, description="Hubspot Login get title")
	public void getTitle(){
	String title=	loginPage.getLoginPageTitle();
	System.out.println(title);	
	Assert.assertEquals(title, "HubSpot Login");
	}
		
	@Test(priority=2, enabled=true, description="Login test using correct username and pasword")
	public void loginTest1(){
		log.info("Logintest1 is starting...");
		loginPage.doLogin("ngulkeskin@gmail.com","12344556");
		log.info("Logintest1 is ending..");
	}
	
	@Test(priority=3, enabled=true, description="Login test using correct username and incorrect pasword")
	public void loginTest2(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("incorrectpassword"));
	}
	
	@Test(priority=4, enabled=true, description="Login test using incorrect username and correct pasword")
	public void loginTest3(){
		loginPage.doLogin(prop.getProperty("incorrectuser"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
		//driver.quit();
	}
	
	
	

	
}