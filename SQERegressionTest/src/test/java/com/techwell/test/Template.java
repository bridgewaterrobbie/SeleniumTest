package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static com.techwell.test.HelperMethods.*;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robbiebridgewater on 9/8/14.
 */
public class Template{

    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    @Rule
    public Retry retry = new Retry(CentralRunner.retrycount);
    @BeforeClass
    public static void createDriver() {
        driver = CentralRunner.driver;
        if(driver==null){driver =new FirefoxDriver();}
        homeString=CentralRunner.homeString;
        if(homeString==null){
            homeString = "http://www.stickyminds.com/";
        }
        h=new HelperMethods(driver,homeString);
        //Logger needs to be turned off if using HTMLUnitDriver, as it gives many verbose statements
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
    }



    @Before
    public void goHome() {
        //At the beginning of each authBioCheck go to the home, have the same start point.
        driver.get(homeString);
    }

    @Test
    public void test()
    {

    }


    @AfterClass
    public static void closeEverything() {
        if(!CentralRunner.runningFromCentral)
        {
            driver.close();
            driver.quit();
            CentralRunner.driver=null;
        }
    }


}






