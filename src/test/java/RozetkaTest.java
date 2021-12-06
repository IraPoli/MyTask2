import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import java.util.concurrent.TimeUnit;

public class RozetkaTest {
    private WebDriver driver;
    private Actions actions;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrom.driver", "src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

        actions = new Actions(driver);

    }

    @Test(priority = 1)
    public void googleImgTest() throws Exception {

        driver.findElements(By.xpath("//div[contains(@class,'sidebar')]//li[contains(@class,'menu')]")).get(0).click(); //xpass
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("rz-widget-producer  li:nth-child(3)")).click();                              //css
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        Select se = new Select(driver.findElement(By.cssSelector("select.ng-pristine")));                               //css
        se.selectByValue("3: expensive");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[contains(@class,'buy-button')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath(" //button[@opencart]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



        int sum = Integer.parseInt(driver.findElement(By.xpath("//div[@class= 'cart-receipt__sum-price']/span")).getText());
        Assert.assertTrue(sum  > 500);





        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        driver.findElement(By.xpath(" //a[contains(@class,'continue ')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[contains(@class,'button--medium')]")).click();

        WebElement element= driver.findElement(By.xpath("//li[contains(@class,'__item ng-')]/a[contains(@href,'instrumenty')]"));
        actions.moveToElement(element).perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[contains(@href,'teplovie-pushki')]")).click();


        se = new Select(driver.findElement(By.xpath("//select")));
        se.selectByValue("3: popularity");

        driver.findElements(By.xpath("//a[@class='goods-tile__heading ng-star-inserted' ]")).get(3).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(@class,'green button_size_large')]")).click();


        sum = Integer.parseInt(driver.findElement(By.xpath("//div[@class= 'cart-receipt__sum-price']/span")).getText());
        Assert.assertTrue(sum  > 1000);





        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        driver.findElement(By.xpath(" //a[contains(@class,'continue ')]")).click();

        driver.findElement(By.xpath(" //input[contains(@class,'search-form')]")).sendKeys("Стиральные машины");
        driver.findElement(By.xpath("//button[contains(@class,' search-form__submit')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        se = new Select(driver.findElement(By.xpath("//select")));
        se.selectByValue("4: novelty");

        driver.findElements(By.xpath("//a[@class='goods-tile__heading ng-star-inserted' ]")).get(6).click();

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[contains(@class,'green button_size_large')]")).click();

        sum = Integer.parseInt(driver.findElement(By.xpath("//div[@class= 'cart-receipt__sum-price']/span")).getText());
        takeSnapShot(driver, "./screenshots/test1.png");
        Assert.assertTrue(sum  > 1000);



    }

    public static void takeSnapShotWebElement(WebElement element, String fileName) throws Exception {
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./screenshots/" + fileName + ".png");
        FileUtils.copyFile(srcFile, destFile);
    }


    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile, destFile);
    }


    @AfterMethod
    public void closeDriver() throws Exception {

        driver.close();
    }
}