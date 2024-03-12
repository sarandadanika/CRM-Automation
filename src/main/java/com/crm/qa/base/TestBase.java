package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.util.TimeUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
//import com.crm.qa.util.WebEventListener;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
//    public  static EventFiringWebDriver e_driver;
//    public static WebEventListener eventListener;

    public TestBase() {
        try {
            //create object of property file
            prop = new Properties();
            //create object of file FileInputStream
            FileInputStream ip = new FileInputStream("C:/Users/USER/IdeaProjects/CRMTestAutomation/src/main/java/com/crm/qa/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void initialization() {

        //access properties
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\IdeaProjects\\CRMTestAutomation\\src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.out.println("no browser value is given");
        }


//        WebDriverEventListener eventListener = new WebEventListener();
//        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
//        eventDriver.register(eventListener);
//        driver = eventDriver;


//        e_driver = new EventFiringWebDriver(driver);
//        // Now create object of EventListerHandler to register it with EventFiringWebDriver
//        eventListener = new WebEventListener();
//        e_driver.register(eventListener);
//        driver = e_driver;
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //synchronization means sync between selenium script and web application speed.
        //selenium not provide default synchronization.
        //PAGE_LOAD_TIMEOUT wait until web page load.
        //IMPLICIT_WAIT it apply globally  it's always applicable for all web element
        //it will wait until web element load if element find early wait will remove and go for next web element
        //it always applied with driver 

        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

}
