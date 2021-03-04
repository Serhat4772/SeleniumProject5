package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task3 {

    WebDriver driver;
    SoftAssert softAssert;
    Actions actions;
    Select select;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        softAssert = new SoftAssert();
        actions = new Actions(driver);

    }


    @Test(dataProvider="SalaryDetails")
        public void test1(String component,String amount, String detail) throws InterruptedException {
        driver.get("https://icehrm-open.gamonoid.com/login.php?");


        WebElement loginUsername=driver.findElement(By.id("username"));
        loginUsername.sendKeys("admin");
        WebElement loginPassword=driver.findElement(By.id("password"));
        loginPassword.sendKeys("admin");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Log in')]"));
        loginButton.click();

        WebElement finance=driver.findElement(By.xpath("//span[text()='Finance']"));
        finance.click();

        WebElement salary=driver.findElement(By.xpath("//a[@href='https://icehrm-open.gamonoid.com/?g=modules&n=salary&m=module_Finance']"));
        salary.click();
        Thread.sleep(2000);
        WebElement addNewButton=driver.findElement(By.xpath("//button[@onclick='modJs.renderForm();return false;']"));
        addNewButton.click();
        Thread.sleep(2000);
        WebElement salaryComponent=driver.findElement(By.xpath("//a[@href='https://icehrm-open.gamonoid.com/?g=modules&n=salary&m=module_Finance']"));
        salaryComponent.click();

        WebElement ddSalary= driver.findElement(By.xpath("//*[contains(text(),'"+component+"')]"));
        ddSalary.click();

        WebElement amountDetail=driver.findElement(By.id("amount"));
        amountDetail.sendKeys(amount);

        WebElement detailDetails=driver.findElement(By.name("details"));
        detailDetails.sendKeys(detail);

        WebElement saveButton=driver.findElement(By.xpath("//button[text()=' Save']"));
        saveButton.sendKeys(detail);


    }

    @Test
    @DataProvider(name = "SalaryDetails")
    public Object[][] getTestData(){
        return new Object[][]{
                {"Fixed Allowance","200000","Test1"}
//                {"Car Allowance","1000000","Test2"},
//                {"Telephone Allowance", "100","Test3"},
//                {"Regular Hourly Pay","70","Test4"},
//                {"Overtime Hourly Pay", "80","Test5"},
        };
    }



}