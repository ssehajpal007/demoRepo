package propertyFile_screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.framework.commanClasses.PropertyFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class PropertyFileTest {
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;

	public void startBrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(PropertyFile.read("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void login() throws IOException, InterruptedException {
		driver.findElement(By.id("login1")).sendKeys(PropertyFile.read("uname"));
		driver.findElement(By.id("password")).sendKeys(PropertyFile.read("pwd"));
		driver.findElement(By.name("proceed")).click();
		Thread.sleep(5000);
	}

	public void titleValidation() throws IOException {
		String title = PropertyFile.read("title");
		if (title.equalsIgnoreCase(driver.getTitle())) {
			System.out.println("Correct Title");
		} else {
			System.out.println("Wrong Title");
		}
	}
	
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "//test-output//ExtentReport.html", true);
		extent.addSystemInfo("Host", "Shubham Windows");
		extent.addSystemInfo("UserName", "Shubham Sehajpal");
		extent.addSystemInfo("Environment", "QA");
	}
	
	public void endReport() {
		extent.flush();
		extent.close();
	}

	public void getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String date = new SimpleDateFormat("ddmmmyyyyhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "//FailedTestScreenshots//" + screenshotName + date + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		PropertyFileTest pft = new PropertyFileTest();
		pft.setExtent();
		pft.startBrowser();
		pft.login();
		pft.titleValidation();
		pft.setExtent();
		pft.endReport();
	}

}
