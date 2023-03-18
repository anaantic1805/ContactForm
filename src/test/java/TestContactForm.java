import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class TestContactForm {

    WebDriver driver;
    WebDriverWait wdWait;

    @BeforeMethod
    public void init() {
        System.getProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ultimateqa.com/automation");
        wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


  @AfterTest
  public void tearDown() {
      driver.quit();

  }


    @Test
    public void enteredAllValidDetails(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("Pera");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("Peric");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfd@hgfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String messageSubmited = driver.findElement(By.xpath("//div/p[text()=\"Thanks for contacting us\"]")).getText();

        Assert.assertEquals(messageSubmited, "Thanks for contacting us");
    }

    @Test
    public void enteredInvalidEmailAddress(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("Pera");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("Peric");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfdhgfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Invalid email");
    }

    @Test
    public void emptyEmailAddressField(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("Pera");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("Peric");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Please, fill in the following fields:\n" + "Email Address");
    }

    @Test
    public void emptyFirstNameField(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("Peric");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfdh@gfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Please, fill in the following fields:\n" + "First Name");
    }

    @Test
    public void emptyLastNameField(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("Pera");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfdh@gfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Please, fill in the following fields:\n" + "Last Name");
    }

    @Test
    public void emptyFirstNameAndLastNameFields(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfd@hgfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Please, fill in the following fields:\n" + "First Name\n" + "Last Name");
    }

    @Test
    public void emptyFirstNameAndLastNameFieldsAndUseInvalidEmailAddress(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfdhgfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("Saljem neku poruku.");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Please, fill in the following fields:\n" + "First Name\n" + "Last Name\n" + "Please, fix the following errors:\n" + "Invalid email");
    }

    @Test
    public void emptyMessageField(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("Pera");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("Peric");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("jfdfd@hgfddf.com");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String submitMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(submitMessage, "Please, fill in the following fields:\n" + "Message");
    }


    @Test
    public void emptyAllRequiredFields(){
        driver.findElement(By.xpath("//li[@id=\"menu-item-216842\"]/a[text()=\"Contact Us\"]")).click();
        driver.findElement(By.cssSelector("#et_pb_contact_first_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_last_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_email_0")).sendKeys("");
        driver.findElement(By.cssSelector("#et_pb_contact_message_0")).sendKeys("");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=\"et_builder_submit_button\"]"))).click();
        String contactMessage = driver.findElement(By.xpath("//div[@class=\"et-pb-contact-message\"]")).getText();

        assertEquals(contactMessage, "Please, fill in the following fields:\n" + "First Name\n" + "Last Name\n" + "Email Address\n" + "Message");
    }



}
