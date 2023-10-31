package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods {
    public static WebDriver driver;

    public static void setUp(){
        ConfigReader.readProperties(Constants.CONFIG_FILE_PATH);

        switch (ConfigReader.getPropertiesValue("browser")){

            case "chrome":
                //  System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;

            case "firefox":
                //  System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser name");
        }
        driver.get(ConfigReader.getPropertiesValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }



    public static void sendText(WebElement element, String textToSend){
      element.clear();
      element.sendKeys(textToSend);
    }

    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait;
    }


    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public  static byte [] takeScreenShot(String fileName){
        TakesScreenshot ts=(TakesScreenshot) driver;

        byte [] picBytes=ts.getScreenshotAs(OutputType.BYTES);

        File sourceFile=ts.getScreenshotAs(OutputType.FILE);
       try{
           FileUtils.copyFile(sourceFile, new File(Constants.screenShotPath +
                   fileName + " " + timeStamp("YYYY-MM-dd-HH-mm-ss")+ ".png"));
       }catch(IOException e){
           e.printStackTrace();
       }
      return picBytes;
    }

    public static String timeStamp( String pattern){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    public static void closeBrowser(){
        driver.quit();
    }


}
