package basetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.LoggerManager;

public class BaseTest {
	
	
	protected RequestSpecification requestSpec;
	protected Properties config;
	protected LoggerManager logger = new LoggerManager();

//	protected Logger log = Log.getLogger(this.getClass());

    @BeforeClass(groups = {"smoke"})
    public void setup() {
    	
  
		config = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			config.load(fis);
			fis.close(); // closing the file stream
			} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties file", e);
		}
		

		String baseURI = config.getProperty("base.uri");
		String authKey = config.getProperty("auth.key");
		
		RestAssured.baseURI = "https://reqres.in";

        requestSpec = new RequestSpecBuilder()
        		.setBaseUri(RestAssured.baseURI)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", authKey)
                .build();
        
   //     log.info("Base URI set to: " + RestAssured.baseURI);
       
    }
    
    public static void main(String[] args) {
		System.out.println("popop");
	}

}
