package POM_Files;

import org.testng.TestNG;
import java.util.Collections;
import java.util.logging.Logger;

public class Calling_POM {
    private static final Logger logger = Logger.getLogger(Calling_POM.class.getName());

    public static void main(String[] args) {
        TestNG testng = new TestNG();
        String testngXmlPath = System.getProperty("testng.xml.path", "file:///D:/Intelij_Project_program/Makemytrip_POM_TestNG/testng.xml");
        try {
            testng.setTestSuites(Collections.singletonList(testngXmlPath));
            testng.run();
            logger.info("TestNG tests executed successfully.");
        } catch (Exception e) {
            logger.severe("Error running TestNG tests: " + e.getMessage());
        }
    }
}

