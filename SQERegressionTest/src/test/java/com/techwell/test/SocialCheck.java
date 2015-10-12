package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robbiebridgewater on 10/9/14.
 */
public class SocialCheck {

    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    static boolean runningFromCentral=true;
    static boolean didIFBLogin=false;
    @Rule
    public Retry retry = new Retry(CentralRunner.retrycount);
    @BeforeClass
    public static void createDriver() {
        driver = CentralRunner.driver;
        if (driver == null) {
            CentralRunner.driver=new FirefoxDriver();
            driver = CentralRunner.driver;
        }
        homeString = CentralRunner.homeString;
        if(homeString==null){
            homeString = "http://www.stickyminds.com/";
            runningFromCentral=false;
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
    public void facebookCheck()
    {
        h.login();
        facebookLogin();

        driver.findElement(By.cssSelector(".views-field.views-field-title.cmc-title-bold a")).click();

        String summary=h.easyWait(By.cssSelector(".summary")).getText();

        h.easyWait(By.cssSelector(".at16nc.at300bs.at15nc.at15t_facebook.at16t_facebook")).click();
        String title= h.easyWait(By.cssSelector("#page-title")).getText();
        String handle= (String) driver.getWindowHandles().toArray()[1];
        driver.switchTo().window(handle);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        String fbSummary=h.easyWait(By.xpath("/html/body/div[1]/form/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/span/div/div/div[2]/div[1]")).getText();
        String fbTitle=driver.findElement(By.xpath("/html/body/div[1]/form/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/span/div/div/div[1]/a")).getText();
        if(title.length()>77)
        {
            h.assertContains(fbTitle,title.substring(0,77));
        }
        else
        {
            h.assertContains(fbTitle,title);
        }
        if(fbSummary.length()>300) {
            h.assertContains(summary, fbSummary.substring(0, 300));
        }
        else {
            h.assertContains(summary, fbSummary.substring(0,fbSummary.length()-3));
        }
        driver.close();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
    }



    public void facebookLogin()
    {

        if(!didIFBLogin) {
            String current = driver.getCurrentUrl();
            driver.get("http://facebook.com");
            driver.findElement(By.id("email")).sendKeys("bridgewaterrobbiesqe+selenium@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("Seleniumtester");
            driver.findElement(By.id("loginbutton")).click();

            h.easyWait(By.cssSelector(".fbxWelcomeBoxName"));
            driver.get(current);
            didIFBLogin = true;
        }
    }


    public void loginToFacebook()
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






