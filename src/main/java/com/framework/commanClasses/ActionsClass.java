package com.framework.commanClasses;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClass {
	
	static WebDriver driver;
	
	static void setup(String url) throws IOException, InterruptedException
	{
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		
		WebDriverManager.chromedriver().setup(); //added webdrivermanager api
		driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		WebElement login = driver.findElement(By.id("login1"));
		login.sendKeys(Keys.LEFT_SHIFT+"shubham");
		login.sendKeys(Keys.F5);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeSelected(By.id("login1")));
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.navigate().refresh();
		
		/*Actions a = new Actions(driver);
		a.moveToElement(login).click().keyDown(Keys.LEFT_SHIFT).sendKeys("shubham").keyUp(Keys.LEFT_SHIFT).build().perform();
		*/
		Thread.sleep(5000);
		login.sendKeys(Keys.F5);
		System.out.println("End of Execution, Thanks!");
		/*TakesScreenshot s = (TakesScreenshot)driver;
		File srcFile = s.getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\Users\\Shubham\\Desktop\\fullimage.png)");
		FileUtils.copyFile(srcFile, destFile);*/
	}
	
/*	static void refresh(String url)
	{
		driver.navigate().to(url);
		driver.get(driver.getCurrentUrl());
		driver.navigate().to(driver.getCurrentUrl());
		driver.findElement(By.id("login1")).sendKeys(Keys.F5);
		driver.navigate().refresh();
	}*/

	
	public static void main(String[] args) throws IOException, InterruptedException {
		setup("https://mail.rediff.com/cgi-bin/login.cgi");
	}

}
