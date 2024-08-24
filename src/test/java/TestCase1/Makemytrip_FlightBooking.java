package TestCase1;

import POM_Files.FlightBookingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class Makemytrip_FlightBooking {
    WebDriver driver;
    FlightBookingPage flightBookingPage;


    @BeforeTest
    @Parameters("browserName")
    public void InitialiseBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser name is invalid");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        flightBookingPage = new FlightBookingPage(driver);
    }

    @Test (priority = 1)
    public void Url() throws InterruptedException {
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(2000);
        String actualTitle = driver.getTitle();
        System.out.println("The Actual Title is :" + actualTitle);
        String expectedTitle = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
        if(expectedTitle.equals(actualTitle))
        {
            System.out.println(" The Validation of page Title Before Clicking on Flight Btn is Successful 👍");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");

        }
        else
        {
            System.out.println("Test case is fail");
        }
    }

    @Test (priority = 2)
    public void Popup() throws IOException {
        flightBookingPage.closePopup();
    }

    @Test (priority = 3)
    public void ClickonFlightBtn()throws IOException {
        flightBookingPage.ClickonFlightBtn();
    }

    @Test (priority = 4)
    public void roundtripBtn() throws IOException, InterruptedException {
        flightBookingPage.selectRoundtrip();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File target = new File("D:\\makemytrip_Project_Screenshots\\Roundtrip.jpeg");
        Thread.sleep(5000);
        FileHandler.copy(src, target);
    }

    @Test (priority = 5)
    public void locationFrom() throws IOException, InterruptedException {
        flightBookingPage.selectFromCity("BLR");
    }

    @Test (priority = 6)
    public void locationTo() throws InterruptedException {
        flightBookingPage.SelectTocity();
    }

    @Test(priority = 7)
    public void Departurebtn () throws InterruptedException {
        flightBookingPage.SelectDeparture_Return();
    }

    @Test (priority = 8)
    public void Travellers_Class(){
       flightBookingPage.Select_Passenger_Class();
    }

    @Test (priority = 9)
    public void Search () throws InterruptedException {
        flightBookingPage.Click_SearchBtn();
    }

    @AfterTest
    public void Teardown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(8000);
            driver.quit();
        }
    }
}

