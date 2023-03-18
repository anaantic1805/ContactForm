import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestContactForm {

    WebDriver driver;
    WebDriverWait wdWait;

    @BeforeMethod
    public void init() {
        System.getProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://ultimateqa.com/automation");
        wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


   @AfterMethod
   public void tearDown() {
       driver.quit();

   }


    @Test
    public void contactForm(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("Pera");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("Peric");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfd@hgfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String messageSubmited = driver.findElement(By.xpath("//div/p[text()=\"Thanks for contacting us\"]")).getText();

        Assert.assertEquals(messageSubmited, "Thanks for contacting us");

    }


}