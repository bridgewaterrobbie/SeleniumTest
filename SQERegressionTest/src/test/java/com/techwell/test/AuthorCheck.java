package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by robbiebridgewater on 9/16/14.
 */
public class AuthorCheck {
    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    static boolean runningFromCentral = true;
    @Rule
    public Retry retry = new Retry(CentralRunner.retrycount);
    @BeforeClass
    public static void createDriver() {
        driver = CentralRunner.driver;
        if (driver == null) {
            CentralRunner.driver = new FirefoxDriver();
            driver = CentralRunner.driver;
        }
        homeString = CentralRunner.homeString;
        if (homeString == null) {
            homeString = "http://www.stickyminds.com/";
            runningFromCentral = false;
        }
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
    public void ContributionCheck() {
        driver.get(homeString + "/users/bob-aiello");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div[1]/div/div[2]/p/a")).click();
        String allWorks = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div/div[1]")).getText();
        assertTrue(allWorks.contains("Getting Started Scaling DevOps"));
    }

    @Test
    public void bioCheck() {
        driver.get(homeString + "/users/bob-aiello");
        //String bio = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div/fieldset/div/div[2]/div[2]/div/p")).getText();
        String bio = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div/fieldset/div/div[2]/div[2]/div/p")).getText();
        assertTrue(bio, bio.contains("Technical Editor of CM Crossroads and author of Configuration Management Best Practices: Practical Methods that Work in the Real World, Bob Aiello is a consultant and software engineer specializing in software process improvement, including software configuration and release management. He has more than twenty-five years of experience as a technical manager at top New York City financial services firms, where he held company-wide responsibility for configuration management. He is vice chair of the IEEE 828 Standards Working Group on CM Planning and a member of the IEEE Software and Systems Engineering Standards Committee (S2ESC) Management Board. Contact Bob"));

    }


    @Test
    public void questionAnswerCheck() {

        if(h.amIonCMC())
        driver.get(homeString + "/users/bob-aiello");
        if(h.amIonSM())
            driver.get(homeString+"/users/rajini-padmanaban");
        if(h.amIonAgile())
            driver.get(homeString+"/users/eric-king");

        WebElement more=driver.findElement(By.cssSelector(".view-more-view-less"));
        if(more.isDisplayed())
     //   driver.findElements(By.cssSelector(".view-more-view-less")).get(0).click();
        more.click();
        String qA = driver.findElement(By.xpath(".//*[@id='content']/div/div[3]/div/div[2]/div/div[1]")).getText();
        if(h.amIonCMC())
        h.assertContains(qA.toLowerCase(), "Join the CM Wiki Team".toLowerCase());
        if(h.amIonAgile())
            h.assertContains(qA.toLowerCase(), "submit articles".toLowerCase());
        if(h.amIonSM())
            h.assertContains(qA.toLowerCase(), "Mobile Performance Testing".toLowerCase());
    }

    @AfterClass
    public static void closeEverything() {
        if(!CentralRunner.runningFromCentral)
        {
            driver.close();
            driver.quit();
            CentralRunner.driver = null;
        }
    }
}







