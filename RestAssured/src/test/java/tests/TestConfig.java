package tests;

import org.testng.TestNG;

public class TestConfig {

//test stage
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { tests.CrudTest.class });

        // Set groups to include
        testng.setGroups("positive");

        testng.run();
    }
}
