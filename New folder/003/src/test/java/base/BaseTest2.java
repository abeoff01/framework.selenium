package base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;
import utils.DriverFactory;

public class BaseTest2 {
	
//	use this for driverfactory and
//	do not forget to change it in he inherited class and events
	public ExtentReports extent;
	public ExtentSparkReporter sparkReporter;
	public static WebDriver driver;
	public ExtentTest logger;

	
	
	public void test() {
		broweserSetup("chrome");
		DriverFactory.getDriver().manage().window().maximize();
	}
	public void broweserSetup(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			
			
			
			
		}

		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("mozilla")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
		} else {
			System.out.println(" brower name was un specified");
		}
		
		  DriverFactory.setDriver(driver);
	        DriverFactory.getDriver().manage().window().maximize();
	        DriverFactory.getDriver().get("https://example.com");
	}

	@BeforeTest
	public void beforeTestMethod() {
		// initialize extent reports for all report
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "firstExtenReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "RHEL8");
		extent.setSystemInfo("UserName", "root");
		sparkReporter.config().setDocumentTitle("Automation report");
		sparkReporter.config().setReportName("Automation result by me");

	}

	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser, Method testMethod) {
		// creating a log for test method
		logger = extent.createTest(testMethod.getName());
		broweserSetup(browser);

		// initilizing driver using a method
		 DriverFactory.getDriver().manage().window().maximize();
		 DriverFactory.getDriver().get(Constants.url);
		 DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		// capturing the report
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test case Failed", ExtentColor.ORANGE));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test case Failed", ExtentColor.GREEN));

		}
		DriverFactory.getDriver().quit();

	}

	@AfterTest
	public void afterTest() {
		extent.flush();
	}
}
