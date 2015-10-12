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
public class PresentationResource {


    static WebDriver driver;
    static boolean runningFromCentral=true;
    static String homeString;
    static HelperMethods h;
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

        homeString = homeString + "/resources/presentations";
        h = new HelperMethods(driver, homeString);
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
    public void allPages() {
        new AllPages(driver);
    }

    @Test
    public void summaryCheck() {

        String summary = driver.findElement(By.xpath(".//*[@id='content']/div[3]/div/div[1]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/p/span")).getText();
        summary = summary.substring(0, summary.length() - 3);
        driver.findElement(By.xpath(".//*[@id='content']/div[3]/div/div[1]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
        if (h.amILoggedIn())
            assertTrue(h.easyWait(By.xpath(".//*[@id='content']/div[1]")).getText().contains(summary));
        else {
            assertTrue(h.easyWait(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div/div/div[4]")).getText().contains(summary));
        }

        if (!h.amILoggedIn()) {
            h.login();
            goHome();
            summaryCheck();
            h.logout();
        } else {
            h.logout();
        }
    }

    @Test
    public void searchCheck() {
        String summary = driver.findElement(By.xpath(".//*[@id='content']/div[3]/div/div[1]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/p/span")).getText();
        driver.findElement(By.id("edit-title")).sendKeys(SearchCheck.shrinkString128(summary));
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/form/div/div/div[2]/div/div[2]/div[2]/input")).click();
        h.easyWait(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div/div[3]/div/div/p"));
        assertEquals(summary, driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div/div[3]/div/div/p")).getText());
        if (!h.amILoggedIn()) {
            h.login();
            goHome();
            searchCheck();
            h.logout();
        } else {
            h.logout();
        }


    }

    @Test
    public void verifyAuthorLink() {
        h.assertElementExists(By.xpath(".//*[@id='content']/div[3]/div/div[1]/div/div[2]/div[1]/table/tbody/tr[1]/td[3]/div"));
    }


    @Test
    public void verifyPreLinks() {
        List<WebElement> buttons=driver.findElements(By.cssSelector(".presentations-top-block a"));;
        assertTrue(buttons.get(0).getAttribute("href").contains("/events/conferences"));
        assertTrue(buttons.get(1).getAttribute("href").contains("/be-conference-speaker"));
        assertTrue(buttons.get(2).getAttribute("href").contains("/conference-archive"));

        if (!h.amILoggedIn()) {
            h.login();
            goHome();
            verifyPreLinks();
            h.logout();
        } else {
            h.logout();
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
