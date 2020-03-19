package com.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

		public WebDriver driver;
		public Properties prop;
		public static String flash;
		/**
		 * @return 
		 *
		 * this method is used to initialize the driver on the basis of given browser
		 * @return this method return WebDriver instance
		 * @throws
		 */
		public WebDriver initialize_driver(Properties prop) {
			//String browser="chrome";
			String browser=prop.getProperty("browser"); // getproperty de key lerle calisir, browser i ekledik
			String headless=prop.getProperty("headless");
			flash=prop.getProperty("elementflash");
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				if (headless.equalsIgnoreCase("yes")) {
					ChromeOptions co=new ChromeOptions();
					co.addArguments("--headless");
					driver=new ChromeDriver(co);
				}else {
					driver=new ChromeDriver();
				}
			}
			else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				if (headless.equals("yes")) {
					FirefoxOptions fo=new FirefoxOptions();
					fo.addArguments("--headless");
					driver=new FirefoxDriver(fo);
				}else {
					driver=new FirefoxDriver();
				}
				
			}
			driver.manage().window().fullscreen();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
			
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return driver;
		}
		/**
		 * it is used to define properties
		 * @return This method returns Properties  with prop reference
		 */
		public Properties initilaize_properties(){
			prop= new Properties();
			try {
				FileInputStream ip=new FileInputStream("/Users/nesekeskin/Documents/workspace/FebruaryTestNG_2020/"
						+ "src/main/java/config/hubspot/config/config.properties");
			prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
		}
		
		public void quitBrowser(){
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("some exception occured while quiting browser");
			}
		}
		
		public void closeBrowser(){
			try {
				driver.close();
			} catch (Exception e) {
				System.out.println("some exception ocuure while closing browser");
			}
		}
		
		
	}