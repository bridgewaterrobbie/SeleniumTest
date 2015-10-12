package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

/**
 * Created by robbiebridgewater on 9/29/14.
 */
public class ArticleResource {

    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    static boolean runningFromCentral=true;
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
        homeString=homeString+"/resources/articles";
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
    public void summaryCheck()
    {
        String summary=driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[1]/table/tbody/tr[1]/td[2]/p")).getText();
        driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[1]/table/tbody/tr[1]/td[2]/a")).click();

        h.easyWait(By.className("summary"));
        assertEquals(summary,driver.findElement(By.className("summary")).getText().substring(9));
        driver.get(homeString+"?page=6");

        summary=driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[1]/table/tbody/tr[1]/td[2]/p")).getText();
        driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
        h.easyWait(By.className("summary"));
        assertEquals(summary,driver.findElement(By.className("summary")).getText().substring(9));

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
        String summary=driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[1]/table/tbody/tr[1]/td[2]/p")).getText();
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
        h.assertElementExists(By.xpath(".//*[@id='content']/div[2]/div[1]/table/tbody/tr[1]/td[3]/div/a"));
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
