package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;
import static com.techwell.test.HelperMethods.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robbiebridgewater on 10/15/14.
 */
public class Blog {
    @Rule
    public Retry retry = new Retry(CentralRunner.retrycount);

    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    @BeforeClass
    public static void createDriver() {
        driver = CentralRunner.driver;
        if(driver==null){driver =new FirefoxDriver();}
        homeString=CentralRunner.homeString;
        if(homeString==null){
            homeString = "http://www.stickyminds.com/";
        }
        homeString=homeString+"/blog";
        h=new HelperMethods(driver,homeString);
        //Logger needs to be turned off if using HTMLUnitDriver, as it gives many verbose statements
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
    }



    @Before
    public void goHome() {
        //At the beginning of each authBioCheck go to the home, have the same start point.
        driver.get(homeString);
        if(amILoggedIn())
        {
            logout();
            driver.get(homeString);
        }
    }

    @Test
    public void allPages()
    {
        new AllPages(driver);
        easyWait(By.xpath(".//*[@id='content']/div[1]/div[1]/div[1]/div/div/h2/a")).click();
        easyWait(By.id("page-title"));
        new AllPages(driver);
        if(!amILoggedIn()){
        login();
        driver.get(homeString);
        allPages();
            logout();
        }
    }

    @Test
    public void summaryCheck()
    {
        String title=easyWait(By.xpath(".//*[@id='content']/div[1]/div[1]/div[1]/div/div/h2/a")).getText();
        driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[1]/div[1]/div/div/h2/a")).click();
        assertEquals(easyWait(By.id("page-title")).getText().toLowerCase(),title.toLowerCase());
        if(!amILoggedIn()){
        login();
        driver.get(homeString);
        summaryCheck();
            logout();
        }
    }

    @Test
    public void personalBlog()
    {
        String authName=driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[2]/div[2]/div/div/a")).getText();
        driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[1]/div[1]/div/div/h2/a")).click();
        assertEquals(easyWait(By.xpath(".//*[@id='content']/div/div[3]/div/div/a")).getText().toLowerCase(),authName.toLowerCase());
        easyWait(By.xpath(".//*[@id='content']/div/ul/li/a")).click();
        easyWait(By.xpath(".//*[@id='content']/div[1]/div[2]/div[2]/div/div/a"));
       List<WebElement> items=driver.findElements(By.xpath(".//*[@id='content']/div/div[2]/div[2]/div/div/a"));
        for(WebElement item:items)
        {
            String authName2=item.getText();
            assertEquals(authName,authName2);
        }
        if(!amILoggedIn()){
        login();
        driver.get(homeString);
        personalBlog();
            logout();
        }
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
