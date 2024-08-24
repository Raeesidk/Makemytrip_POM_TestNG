package POM_Files;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FlightBookingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By popup = By.xpath("//span[contains(@class, 'commonModal__close')]");
    private By FlightsBtn = By.xpath("//li[@class='menu_Flights']/child::span/child::a");
    private By roundtripButton = By.xpath("//li[@data-cy='roundTrip']/child::span");
    private By fromCityInput = By.id("fromCity");
    private By fromCityPlaceholder = By.xpath("//div[@role='combobox']/child::input");
    private By Departure = By.id("departure");
    private By NMArrow = By.xpath("//span[@aria-label='Next Month']");
    private By SelectDate = By.xpath("//div[@aria-label='Sun Oct 06 2024']");
    private By RNMArrow = By.xpath("(//span[@aria-label='Next Month'])[1]");
    private By ReturnDate = By.xpath(" (//div[@aria-label='Fri Nov 01 2024'])[2]");
    private By Passenger = By.xpath("//label[@for='travellers']");
    private By Adults = By.xpath("//li[@data-cy='adults-2']");
    private By Apply = By.xpath("//button[normalize-space()='APPLY']");
    private By Fare = By.xpath("(//span[contains(@class,'customRadioBtn sizeSm primary ')])[2]");
    private By SearchBtn = By.xpath("//a[normalize-space()='Search']");
    public FlightBookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Increase timeout

    }

    private void captureScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File target = new File(System.getProperty("java.io.tmpdir") + fileName);
            FileHandler.copy(src, target);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public void closePopup() throws IOException {
        try {
            WebElement popups = wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popups); // Use JavaScript click
        } catch (TimeoutException e) {
            System.out.println("Popup element not found: " + e.getMessage());
            captureScreenshot("PopupError.jpeg");
        }
    }

    public void ClickonFlightBtn() throws IOException {
        try {
            WebElement Flight = wait.until(ExpectedConditions.visibilityOfElementLocated(FlightsBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",Flight );
            String actualTitle = driver.getTitle();
            System.out.println("The Actual Title  ✈️ is : " + actualTitle);
            String expectedTitle = "Flight Booking, Cheap Flights , Air Ticket Booking at Lowest Airfare | MakeMyTrip";
            if(expectedTitle.equals(actualTitle))
            {
                System.out.println(" The Validation after clicking on Flight ✈️ Functionality page Title  is Successful  👍 ");
            }
            else
            {
                System.out.println("Test case is fail");
            }
        } catch (Exception e) {
            System.out.println("FlightsBtn element not found: " + e.getMessage());
            captureScreenshot("FlightBtnError.jpeg");
        }
    }

    public void selectRoundtrip() {
        try {

            WebElement roundtrip = wait.until(ExpectedConditions.elementToBeClickable(roundtripButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", roundtrip); // Use JavaScript click
        } catch (TimeoutException e) {
            System.out.println("Roundtrip button not clickable: " + e.getMessage());
        }
    }

    public void selectFromCity(String BLR) throws InterruptedException {
        try {
            // Wait for the 'fromCity' input field to be visible and click it
            WebElement fromCity = wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityInput));
            fromCity.click();
            WebElement fromtext = wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityPlaceholder));
            fromtext.sendKeys(BLR);
            Thread.sleep(3000);
            Actions actf = new Actions(driver);
            actf.sendKeys(Keys.ARROW_DOWN);
            actf.sendKeys(Keys.ENTER);
            actf.build().perform();

        } catch (TimeoutException e) {
            System.out.println("Element interaction failed: " + e.getMessage());
            captureScreenshot("SelectFromCityError.jpeg");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void SelectTocity () throws InterruptedException {
        driver.findElement(By.xpath("//label[@for='toCity']")).click();
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("HYD");
        Thread.sleep(4000);
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ARROW_DOWN);
        act.sendKeys(Keys.ENTER);
        act.build().perform();
    }

   public void SelectDeparture_Return() throws InterruptedException{
       try {
           WebElement ClickNextmonth = driver.findElement(NMArrow);
           ClickNextmonth.click();
           Thread.sleep(2000);
           WebElement ClickonDate = wait.until(ExpectedConditions.visibilityOfElementLocated(SelectDate));

           ((JavascriptExecutor) driver).executeScript( "arguments[0].click();", ClickonDate);
           WebElement ReturnArrow = wait.until(ExpectedConditions.visibilityOfElementLocated(RNMArrow));
           ReturnArrow.click();
           WebElement ClickonRdate = wait.until(ExpectedConditions.elementToBeClickable(ReturnDate));
           ClickonRdate.click();

       } catch (Exception e) {
           System.out.println("Element interaction failed: " + e.getMessage());
       }
  }

   public void Select_Passenger_Class(){
       try {
           driver.findElement(Passenger).click();
           driver.findElement(Adults).click();
           WebElement ClickonApplyBtn = wait.until(ExpectedConditions.elementToBeClickable(Apply));
           ((JavascriptExecutor) driver).executeScript( "arguments[0].click();", ClickonApplyBtn);

       } catch (Exception e) {
           System.out.println("Element interaction failed: " + e.getMessage());
       }
   }

   public void Click_SearchBtn() throws InterruptedException {
        WebElement Student = wait.until(ExpectedConditions.visibilityOfElementLocated(Fare));
        Student.click();
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(SearchBtn));
       ((JavascriptExecutor) driver).executeScript( "arguments[0].click();", search);
       Thread.sleep(3000);
       System.out.println("-------------------------------------------------------------------------------------------------------------------------");
       String currentUrl = driver.getCurrentUrl();
       System.out.println("Current URL 🔗 after clicking Search : " + currentUrl);
       driver.navigate().to(" https://www.makemytrip.com/flight/search?itinerary=BLR-HYD-06/10/2024_HYD-BLR-01/11/2024&tripType=R&paxType=A-2_C-0_I-0&intl=false&cabinClass=E&ccde=IN&lang=eng&pft=STU");





   }
}

