package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task1And2 {

        /*
        Navigate to the "http://icehrm-open.gamonoid.com/login.php?"
        Enter username "admin"
    Enter password "admin"
    Click sign-in button
    Click Switch button
    Validate "Switch Employee" is displayed and text is matching Validate
     "Select The Employee to Switch Into" is displayed and text is matching
    Select "Lala Lamees"
    Click Switch button
    Validate "Lala Lamees" name is displayed and name is matching with selected name
    Validate "Changed to" text is correct
    Validate color of circle is brown (#8a6d3b)
         */
        WebDriver driver;
        Actions actions;
        SoftAssert softAssert;
        @BeforeMethod
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            softAssert = new SoftAssert();
            actions = new Actions(driver);
        }
        @Parameters({"MyUsername", "MyPassword"})
        @Test
        public void test1(String username, String password) throws InterruptedException {
            driver.get("http://icehrm-open.gamonoid.com/login.php?");
            // softAssert.assertTrue(driver.getCurrentUrl().contains("gamanoid"), "Url doesn't contain text");
            WebElement userName= driver.findElement(By.id("username"));
            actions.sendKeys(userName,username).perform();
            WebElement inputPassWord= driver.findElement(By.id("password"));
            actions.sendKeys(inputPassWord,username).perform();
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Log in')]"));
            loginButton.click();
            WebElement switchButton= driver.findElement(By.xpath("//span[text()='Switch']"));
            switchButton.click();
            Thread.sleep(2000);
            WebElement switchDisplayed= driver.findElement(By.xpath("//h4[@id='myModalLabel']"));
            String expectedSwitch= switchDisplayed.getText();
            String actualSwitch= "Switch Employee";
            softAssert.assertEquals(expectedSwitch,actualSwitch, "Switch Employee is not equal");
            try {
                WebElement selectDisplay = driver.findElement(By.xpath("//p[text()='Select The Employee to Switch Into']"));
//        String expectedSelect= selectDisplay.getText();
//        String actualSelect= "Select The Employee to Switch Into";
//        softAssert.assertEquals(expectedSelect,actualSelect);
            }catch(NoSuchElementException e){
                softAssert.fail("There is no element with text Select The Employee to Switch Into");
            }
            WebElement dropdownSearch= driver.findElement(By.xpath("//select[@id='switch_emp']"));
            Select select= new Select(dropdownSearch);
            select.selectByIndex(1);

            WebElement switchClick=driver.findElement(By.xpath("//button[contains(text(),'Switch')]"));
            switchClick.click();
            Thread.sleep(2000);

            WebElement lalaValidation= driver.findElement(By.xpath("//a[contains(text(),'Lala Lamees')]"));
            String expectedLala= lalaValidation.getText();
            String actulLala= "Lala Lamees";
            softAssert.assertEquals(expectedLala,actulLala, "Name is not maching");
            Thread.sleep(2000);

            try {
                WebElement validateChangedTo= driver.findElement(By.xpath("//a[contains(text(), 'Changed To')]"));
            } catch (NoSuchElementException e) {
                softAssert.fail("There is no element with text Changed To");
            }
            Thread.sleep(2000);

            WebElement clickName= driver.findElement(By.xpath("//a[contains(text(),'Lala Lamees')]"));
            clickName.click();

            String url=  driver.getCurrentUrl();
            Assert.assertTrue(url.contains("Personal") && url.contains("Information"));
            Thread.sleep(2000);

            WebElement jobDetails= driver.findElement(By.xpath("//*[contains(text(),'Job Details')]"));
            String expectedOne= jobDetails.getText();
            String actualOne="Job Details";
            softAssert.assertEquals(expectedOne,actualOne);

            Thread.sleep(2000);
            WebElement jobTitle= driver.findElement(By.xpath("//td[@colspan='3'][contains(text(),'Pre-Sales Executive')]"));
            String expectedJobTitle=jobTitle.getText();
            String actualJobTitle="Pre-Sales Executive";
            softAssert.assertEquals(expectedJobTitle,actualJobTitle);

            Thread.sleep(2000);
            WebElement fullTimeContractText=driver.findElement(By.xpath("//td[text()='Full Time Contract']"));
            softAssert.assertEquals(fullTimeContractText.getText(),"Full Time Contract");
            Thread.sleep(2000);

            WebElement headOfficeText=driver.findElement(By.xpath("//td[text()='Head Office']"));
            softAssert.assertEquals(headOfficeText.getText(),"Head Office");

            softAssert.assertAll();
        }
    }
