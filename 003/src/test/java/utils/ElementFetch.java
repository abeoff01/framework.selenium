package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ElementFetch {
	
	
	   

	public WebElement getWebElement(String locatorType, String locatorValue) {
		  try {
	            switch (locatorType.toLowerCase()) {
	                case "id":
	                    return BaseTest.driver.findElement(By.id(locatorValue));
	                case "name":
	                    return BaseTest.driver.findElement(By.name(locatorValue));
	                case "xpath":
	                    return BaseTest.driver.findElement(By.xpath(locatorValue));
	                case "css":
	                    return BaseTest.driver.findElement(By.cssSelector(locatorValue));
	                case "class":
	                    return BaseTest.driver.findElement(By.className(locatorValue));
	                default:
	                    System.out.println("Invalid locator type: " + locatorType);
	                    return null;
	            }
	        } catch (Exception e) {
	            System.out.println("Element not found: " + e.getMessage());
	            return null;
	        }

		
	}
	
	public List<WebElement> getListWebElement(String locatorType, String locatorValue) {
		  try {
	            switch (locatorType.toLowerCase()) {
	                case "id":
	                    return BaseTest.driver.findElements(By.id(locatorValue));
	                case "name":
	                    return BaseTest.driver.findElements(By.name(locatorValue));
	                case "xpath":
	                    return BaseTest.driver.findElements(By.xpath(locatorValue));
	                case "css":
	                    return BaseTest.driver.findElements(By.cssSelector(locatorValue));
	                case "class":
	                    return BaseTest.driver.findElements(By.className(locatorValue));
	                default:
	                    System.out.println("Invalid locator type: " + locatorType);
	                    return null;
	            }
	        } catch (Exception e) {
	            System.out.println("Element not found: " + e.getMessage());
	            return null;
	        }

		
	}

}
