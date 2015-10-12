package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by robbiebridgewater on 9/29/14.
 */
public class MagazineResource {


    static WebDriver driver;
    static String homeString;
    static boolean runningFromCentral=true;
    static HelperMethods h;
    static String mainString;
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
        mainString=homeString+"/resources/magazine-articles";
        h=new HelperMethods(driver,homeString);
        //Logger needs to be turned off if using HTMLUnitDriver, as it gives many verbose statements
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
    }

    @Before
    public void goHome() {
        //At the beginning of each authBioCheck go to the home, have the same start point.
        driver.get(mainString);
    }

    @Test
    public void allPages()
    {
        if(h.amILoggedIn()) h.logout();
        new AllPages(driver);
        h.login();
        new AllPages(driver);
        h.logout();
    }

    @Test
    public void summaryCheck()
    {

        String summary=driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/p")).getText();
        if(!h.amILoggedIn()) {
            driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
            h.easyWait(By.cssSelector(".field-item.even p"));
            assertEquals(summary,driver.findElement(By.cssSelector(".field-item.even p")).getText());

        }
        else {
            driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
            h.easyWait(By.xpath(".//*[@id='content']/article/div[1]/div[3]"));
            assertEquals(summary,driver.findElement(By.xpath(".//*[@id='content']/article/div[1]/div[3]")).getText().substring(9));


        }
        driver.get(mainString+"?page=6");

        summary=driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/p")).getText();
        if(!h.amILoggedIn()) {
            driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
            h.easyWait(By.xpath(".//*[@id='content']/div[1]/div/div/div/div/div[5]/div[2]/div/p"));
            assertEquals(summary,driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div/div/div[5]/div[2]/div/p")).getText());

        }
        else {
            driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
            h.easyWait(By.xpath(".//*[@id='content']/article/div[1]/div[3]"));
            assertEquals(summary, driver.findElement(By.xpath(".//*[@id='content']/article/div[1]/div[3]")).getText().substring(9));

        }

        if(!h.amILoggedIn())
        {
            h.login();
            goHome();
            summaryCheck();
            h.logout();
        }
        else {
            h.logout();
        }
    }

    @Test
    public void searchCheck()
    {
        String summary=driver.findElement(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[2]/p")).getText();
        driver.findElement(By.id("edit-title")).sendKeys(SearchCheck.shrinkString128(summary));
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/form/div/div/div[2]/div/div[2]/div[2]/input")).click();
        h.easyWait(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div/div[3]/div/div/p"));
        assertEquals(summary,driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div/div[3]/div/div/p")).getText());

        if(!h.amILoggedIn())
        {
            h.login();
            goHome();
            searchCheck();
            h.logout();
        }


    }

    @Test
    public void verifyAuthorLink()
    {
        h.assertElementExists(By.xpath(".//*[@id='content']/div[3]/div[1]/table/tbody/tr[1]/td[3]/div/a"));
    }


    @Test
    public void verifyMagLinks()
    {
        List<WebElement> buttons=null;

        if(h.amIonCMC())
            buttons=driver.findElements(By.cssSelector(".summary a"));
        if(h.amIonSM())
            buttons=driver.findElements(By.cssSelector(".bsm-top-block a"));
        if(h.amIonAgile())
            buttons=driver.findElements(By.cssSelector(".bsm-top-block div a"));

        assertTrue((buttons.get(0).getAttribute("href")).contains("http://host.msgapp.com/"));
        String currentMag=buttons.get(1).getAttribute("href");
        assertTrue((buttons.get(2).getAttribute("href")).contains("/better-software-magazine-archive"));

        driver.get(homeString+"/better-software-magazine");
        assertEquals(driver.findElement(By.xpath(".//*[@id='content']/article/div/div/div/p[8]/strong/a")).getAttribute("href"),currentMag);

        /*
        assertTrue((driver.findElement(By.xpath(".//*[@id='block-block-"+blockNum+"']/div/a[1]")).getAttribute("href")).contains("http://host.msgapp.com/"));
        String currentMag=driver.findElement(By.xpath(".//*[@id='block-block-"+blockNum+"']/div/a[2]")).getAttribute("href");
        assertTrue((driver.findElement(By.xpath(".//*[@id='block-block-"+blockNum+"']/div/a[3]")).getAttribute("href")).contains("/better-software-magazine-archive"));
        driver.get("http://www.stickyminds.com/better-software-magazine");
        assertEquals(driver.findElement(By.xpath(".//*[@id='content']/article/div/div/div/p[8]/strong/a")).getAttribute("href"),currentMag);*/
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