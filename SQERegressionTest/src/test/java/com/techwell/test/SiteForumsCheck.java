package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;
import static com.techwell.test.HelperMethods.*;

import java.math.BigInteger;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robbiebridgewater on 10/15/14.
 */
public class SiteForumsCheck {


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
        if(driver.getCurrentUrl()!=homeString && driver.getCurrentUrl()!=homeString.substring(0,homeString.length()-1))
            driver.get(homeString);
        logout();
    }

    @Test
    public void siteFeedback()
    {
        driver.get(homeString+"/site-feedback");
        WebElement name=driver.findElement(By.id("edit-submitted-your-name"));
        WebElement subject=driver.findElement(By.id("edit-submitted-subject"));
        WebElement message=driver.findElement(By.id("edit-submitted-message"));
        WebElement button= driver.findElement(By.id("edit-submitted-send-me-a-copy-1"));

        if(amILoggedIn()) {
            assertEquals("Selenium tester".toLowerCase(), name.getAttribute("value").toLowerCase());
            button.click();
        }
        else {
            typeAndCheckField("edit-submitted-email");
            typeAndCheckField(name);
        }
        subject.sendKeys("Subject");
        message.sendKeys("message");
        assertEquals("Subject",subject.getAttribute("value"));
        assertEquals("message",message.getAttribute("value"));
        typeAndCheckField("edit-mollom-captcha");

        if(!amILoggedIn())
        {login();
        siteFeedback();}
    }

    @Test
    public void contactUs()
    {
        driver.get(homeString+"/webform/contact-us");

        WebElement name=driver.findElement(By.id("edit-submitted-your-name"));
        WebElement email=driver.findElement(By.id("edit-submitted-email"));
        WebElement subject=driver.findElement(By.id("edit-submitted-subject"));
        WebElement message=driver.findElement(By.id("edit-submitted-message"));


        if(amILoggedIn())
        {
            assertEquals("Selenium tester".toLowerCase(), name.getAttribute("value").toLowerCase());
        }
        else {
            typeAndCheckField(email);
            typeAndCheckField(name);
        }

        subject.sendKeys("Subject");
        message.sendKeys("message");
        assertEquals("Subject",subject.getAttribute("value"));
        assertEquals("message",message.getAttribute("value"));
        typeAndCheckField("edit-mollom-captcha");

        if(!amILoggedIn())
        {login();
            contactUs();}
    }


    @Test
    public void advertiseWithUs()
    {
        if(amIonCMC())
        driver.get(homeString+"/webform/advertise-us");
        if(amIonAgile())
            driver.get(homeString+"/webform/advertise-us-0");
        if(amIonSM())
            driver.get(homeString+"/webform/advertise-us-1");

        assertElementExists(By.id("edit-submitted-product-request-5"));
        assertElementExists(By.id("edit-submitted-product-request-4"));
        assertElementExists(By.id("edit-submitted-product-request-3"));
        assertElementExists(By.id("edit-submitted-product-request-2"));
        assertElementExists(By.id("edit-submitted-product-request-1"));

        if(!amILoggedIn())
            typeAndCheckField("edit-submitted-email");

        typeAndCheckField("edit-submitted-first-name");
        typeAndCheckField("edit-submitted-last-name");
        typeAndCheckField("edit-submitted-last-name");
        typeAndCheckField("edit-submitted-title");
        typeAndCheckField("edit-submitted-phone");
        typeAndCheckField("edit-submitted-company");
        typeAndCheckField("edit-submitted-website");
        typeAndCheckField("edit-submitted-address-1");
        typeAndCheckField("edit-submitted-address-2");
        typeAndCheckField("edit-submitted-city");
        typeAndCheckField("edit-submitted-zip-or-postal-code");
        typeAndCheckField("edit-submitted-comments");
        WebElement state=driver.findElement(By.id("edit-submitted-state-or-province"));
        state.sendKeys("Florida");
        assertEquals(state.getAttribute("value").toLowerCase(),"fl");

       if(!amILoggedIn())
       {
           login();
           advertiseWithUs();
       }
    }

    @Test
    public void contribute()
    {
        driver.get(homeString+"/webform/contribute-community");
        WebElement notloggedin=easyWait(By.xpath(".//*[@id='content']/article/div/div/div/div"));
        assertEquals(notloggedin.getText().toLowerCase().substring(15),"You must login or register to contribute to the community.".toLowerCase());
        login();
        driver.get(homeString+"/webform/contribute-community");

        driver.findElement(By.id("edit-submitted-send-me-a-copy-1")).click();
        typeAndCheckField("edit-submitted-name");
        typeAndCheckField("edit-submitted-subject");
        typeAndCheckField("edit-submitted-article-description");
        typeAndCheckField("edit-mollom-captcha");
    }

    public void typeAndCheckField(String id)
    {

        WebElement item=driver.findElement(By.id(id));
        item.clear();
        assertEquals(item.getAttribute("value"), "");
        String randomString= UUID.randomUUID().toString();
        randomString=randomString.substring(0,10);
        item.sendKeys(randomString);
        assertEquals(item.getAttribute("value"),randomString);
    }

    public void typeAndCheckField(WebElement item)
    {

        item.clear();
        assertEquals(item.getAttribute("value"), "");
        String randomString= UUID.randomUUID().toString();
        randomString=randomString.substring(0,10);
        item.sendKeys(randomString);
        assertEquals(item.getAttribute("value"),randomString);
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