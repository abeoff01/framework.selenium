package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.IAnnotationTransformer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;
import qa.test.TestCase1;

//suite listener is to take screenshot and to retry the test again
//use test ng annotations transformer
public class SuiteListener implements ITestListener, IAnnotationTransformer {

	// to get screenshot of the test fails
	public void onTestFailure(ITestResult result) {
		// get the file
		String filename = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ result.getMethod().getMethodName();
		System.out.println("test");
		File fl = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(fl, new File(filename + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// to retry the test if it fails
	// open iannotationtransfor declaration

	public void transform(ITestAnnotation annotation, Method method) {
		// not implemented
		annotation.setRetryAnalyzer(RetryAnalyser.class);
	}

}
