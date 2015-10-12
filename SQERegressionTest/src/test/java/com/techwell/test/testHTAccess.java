package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import sun.jvm.hotspot.utilities.AssertionFailure;

import static com.techwell.test.HelperMethods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robbiebridgewater on 9/8/14.
 */
public class testHTAccess{

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
        //driver.get(homeString);
    }

    @Test
    public void test()
    {
        if(homeString.contains("stickyminds")) {
            testURL("http://agileconnection.communities.techwelldev.com", "Agile Dev");
            testURL("http://cmcrossroads.communities.techwelldev.com", "CMC Dev");
            testURL("http://stickyminds.communities.techwelldev.com", "Stickyminds Dev");
            testURL("http://admin.communities.techwelldev.com", "Admin Dev");


            testURL("http://agileconnection.communities.techwellstage.com", "Agile stage");
            testURL("http://cmcrossroads.communities.techwellstage.com", "CMC stage");
            testURL("http://stickyminds.communities.techwellstage.com", "Stickyminds stage");
            testURL("http://admin.communities.techwellstage.com", "Admin stage");

            testURL("http://admin.communities.techwell.com", "Admin Live");

            testURL("http://members.techwellstage.com", "Members stage");

            testURL("http://techwellstage.com", "Members stage");
        }

    }

    public void testURL(String url, String name)
    {
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

        try {
            driver.get(url);
            assertTrue(name+" has no HTacess", 0 == 1);
        }
        catch (org.openqa.selenium.TimeoutException e)
        {
        }

        driver.quit();
        driver =new FirefoxDriver();
    }

    @AfterClass
    public static void closeEverything() {
        CentralRunner.driver=driver;
        if(!CentralRunner.runningFromCentral)
        {
            driver.close();
            driver.quit();
            CentralRunner.driver=null;
        }
    }


}






