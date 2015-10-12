package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.techwell.test.HelperMethods.doesElementExist;
import static com.techwell.test.HelperMethods.easyWait;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by robbiebridgewater on 9/24/14.
 */
public class Article {

    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    static boolean runningFromCentral=true;
    @BeforeClass
    public static void createDriver() {

        driver = CentralRunner.driver;
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

    @Rule
    public Retry retry = new Retry(CentralRunner.retrycount);

    @Before
    public void goHome() {
        //At the beginning of each authBioCheck go to the home, have the same start point.
        driver.get(homeString);
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/table/tbody/tr[1]/td[2]/a")).click();
        easyWait(By.id("main-content"));

    }

    @Test
    public void authBioCheck()
    {

        WebElement aboutAuthorBox=easyWait(By.className("about-the-author"));
        String bio=null;
        try {
          bio=aboutAuthorBox.findElement(By.cssSelector("p")).getText();
        }
        catch(Exception e)
        {
            assertTrue("This author has no bio at all!",false);
        }
        Random r=new Random();
        if(r.nextBoolean())
        {
            aboutAuthorBox.findElement(By.cssSelector(".user-picture a img")).click();
        }
        else {
            aboutAuthorBox.findElement(By.cssSelector(".profile a")).click();
        }
        String authBio = null;
        try {
            authBio= easyWait(By.cssSelector(".field-item.even p")).getText();
        }
        catch (Exception e)
        {
            assertTrue("THIS AUTHOR HAS NO BIO",false);
        }
        h.assertContains("Author bio",authBio,bio);

        driver.navigate().back();

        if(driver.findElements(By.linkText("next ›")).size()>0)
        {
            driver.findElement(By.linkText("next ›")).click();
            easyWait(By.id("main-content"));
            authBioCheck();
        }
        if(!h.amILoggedIn())
        {
            h.login();
            goHome();
            authBioCheck();
            h.logout();
        }
    }

@Test
public void pageItemCheck()
{
    h.login();
    h.assertElementExists(By.xpath("/html/body/div[1]/div/div[1]/article/div[2]/div[1]"),"Summary");
    h.assertElementExists(By.xpath("/html/body/div[1]/div/div[1]/article/footer"),"Byline");

    try {
        h.assertElementExists(By.xpath(".//*[@id='content']/article/div[2]/div/div/div/a[1]/span"), "Facebook");
        h.assertElementExists(By.xpath(".//*[@id='content']/article/div[2]/div/div/div/div[1]/a"),"ShareURL");

    }
    catch(junit.framework.AssertionFailedError e)
    {
        h.assertElementExists(By.xpath("/html/body/div[1]/div/div[1]/article/div[1]/div/div/div/a[1]/span"), "Facebook");
        h.assertElementExists(By.xpath("/html/body/div[1]/div/div[1]/article/div[1]/div/div/div/div[1]/a"),"ShareURL");


    }
    new AllPages(driver);


    if(driver.findElements(By.linkText("next ›")).size()>0)
    {
        driver.findElement(By.linkText("next ›")).click();
        easyWait(By.id("main-content"));
        pageItemCheck();
    }

    if(!h.amILoggedIn())
    {
        h.login();
        goHome();
        pageItemCheck();
        h.logout();
    }

}


    @Test
    public void commentBoxCheck()
    {
//        driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(5));

        if(!h.amILoggedIn()) h.login();


        driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(2));
        WebElement body=driver.findElement(By.tagName("body"));
        assertEquals(body.getText(),"");
        ((JavascriptExecutor)driver).executeScript("arguments[0].innerHTML = 'Hello'", driver.findElement(By.tagName("body")));
        assertEquals(body.getText(),"Hello");
        body.clear();
        driver.switchTo().defaultContent();
        if(driver.findElements(By.linkText("next ›")).size()>0)
        {
            driver.findElement(By.linkText("next ›")).click();
            easyWait(By.id("main-content"));
            commentBoxCheck();
        }
//        h.logout();

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



