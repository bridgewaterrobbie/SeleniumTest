package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.techwell.test.HelperMethods.*;
import static junit.framework.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by robbiebridgewater on 9/22/14.
 */
public class UserProfile {

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
    public void LDAPSyncCheck()
    {


        clearThe1();
        logout();
        goHome();

            login();
            driver.get(homeString + "/user/edit");
            String firstNameBoxLoc = "edit-field-first-name-und-0-value";
            String lastNameBoxLoc = "edit-field-last-name-und-0-value";
            String address1BoxLoc = "edit-field-address1-und-0-value";
            String address2BoxLoc = "edit-field-address2-und-0-value";
            String cityBoxLoc = "edit-field-city-und-0-value";
            String CountryBoxLoc = "edit-field-country-und";
            String stateBoxLoc = "edit-field-state-und-0-us";
            String zipBoxLoc = "edit-field-zipcode-und-0-value";
            String phoneBoxLoc = "edit-field-business-phone-und-0-value";
            String companyBoxLoc = "edit-field-company-name-und-0-value";
            String jobLevelBoxLoc = "edit-field-job-level-und";
            String linkedInBoxLoc = "edit-data-connect-linkedin";
            String twitterBoxLoc = "edit-data-connect-twitter";
            String facebookBoxLoc = "edit-data-connect-facebook";
            String blogBoxLoc = "edit-data-connect-personal-url";
            String jobTitleBoxLoc = "edit-field-job-title-und-0-value";

            String firstNameBox = findElementById(firstNameBoxLoc).getAttribute("value");
            String lastNameBox = findElementById(lastNameBoxLoc).getAttribute("value");
            String address1Box = findElementById(address1BoxLoc).getAttribute("value");
            String address2Box = findElementById(address2BoxLoc).getAttribute("value");
            String cityBox = findElementById(cityBoxLoc).getAttribute("value");
            String CountryBox = findElementById(CountryBoxLoc).getAttribute("value");
            //String stateBox=findElementById(stateBoxLoc).getAttribute("value");
            String zipBox = findElementById(zipBoxLoc).getAttribute("value");
            String phoneBox = findElementById(phoneBoxLoc).getAttribute("value");
            String companyBox = findElementById(companyBoxLoc).getAttribute("value");
            String jobLevelBox = findElementById(jobLevelBoxLoc).getAttribute("value");
            String linkedInBox = findElementById(linkedInBoxLoc).getAttribute("value");
            String twitterBox = findElementById(twitterBoxLoc).getAttribute("value");
            String facebookBox = findElementById(facebookBoxLoc).getAttribute("value");
            String blogBox = findElementById(blogBoxLoc).getAttribute("value");
            String jobTitleBox = findElementById(jobTitleBoxLoc).getAttribute("value");

            add1toField(firstNameBoxLoc);
            add1toField(lastNameBoxLoc);
            add1toField(address1BoxLoc);
            add1toField(address2BoxLoc);
            add1toField(cityBoxLoc);
            //add1toField(CountryBoxLoc);
           // findElementById(CountryBoxLoc).sendKeys("za");
        new Select(findElementById(CountryBoxLoc)).selectByIndex(244);
           // findElementById(jobLevelBoxLoc).sendKeys("director");
        new Select(findElementById(jobLevelBoxLoc)).selectByIndex(4);

        //add1toField(stateBoxLoc);
            add1toField(zipBoxLoc);
            add1toField(phoneBoxLoc);
            add1toField(companyBoxLoc);
            //add1toField(jobLevelBoxLoc);
            add1toField(linkedInBoxLoc);
            add1toField(twitterBoxLoc);
            add1toField(facebookBoxLoc);
            add1toField(blogBoxLoc);
            add1toField(jobTitleBoxLoc);

            findElementById("edit-submit--2").click();
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div")));


            d6LoginProcess();

            String firstNameBoxLoc6 = "edit-field-firstname-0-value";
            String lastNameBoxLoc6 = "edit-field-lastname-0-value";
            String address1BoxLoc6 = "edit-field-profile-address-1-0-value";
            String address2BoxLoc6 = "edit-field-profile-address-2-0-value";
            String cityBoxLoc6 = "edit-field-profile-city-0-value";
            String CountryBoxLoc6 = "edit-field-profile-country-value";
            String stateBoxLoc6 = "edit-field-profile-state-province-value";
            String zipBoxLoc6 = "edit-field-zipcode-0-value";
            String phoneBoxLoc6 = "edit-field-phone-0-value";
            String companyBoxLoc6 = "edit-field-profile-company-0-value";
            String jobLevelBoxLoc6 = "edit-taxonomy-31";
            String linkedInBoxLoc6 = "edit-field-linkedin-0-url";
            String twitterBoxLoc6 = "edit-field-twitter-0-url";
            String facebookBoxLoc6 = "edit-field-facebook-0-url";
            String blogBoxLoc6 = "edit-field-profile-link-web-0-url";
            String jobTitleBoxLoc6 = "edit-field-jobtitle-0-value";

            String firstNameBox6 = findElementById(firstNameBoxLoc6).getAttribute("value");
            String lastNameBox6 = findElementById(lastNameBoxLoc6).getAttribute("value");
            String address1Box6 = findElementById(address1BoxLoc6).getAttribute("value");
            String address2Box6 = findElementById(address2BoxLoc6).getAttribute("value");
            String cityBox6 = findElementById(cityBoxLoc6).getAttribute("value");
            String CountryBox6 = new Select(findElementById(CountryBoxLoc6)).getFirstSelectedOption().getText();
            // String stateBox6=findElementById(stateBoxLoc6).getAttribute("value");
            String zipBox6 = findElementById(zipBoxLoc6).getAttribute("value");
            String phoneBox6 = findElementById(phoneBoxLoc6).getAttribute("value");
            String companyBox6 = findElementById(companyBoxLoc6).getAttribute("value");
            // String jobLevelBox6=findElementById(jobLevelBoxLoc6).getAttribute("value");
            String jobLevelBox6 = new Select(findElementById(jobLevelBoxLoc6)).getFirstSelectedOption().getText();
            String linkedInBox6 = findElementById(linkedInBoxLoc6).getAttribute("value");
            String twitterBox6 = findElementById(twitterBoxLoc6).getAttribute("value");
            String facebookBox6 = findElementById(facebookBoxLoc6).getAttribute("value");
            String blogBox6 = findElementById(blogBoxLoc6).getAttribute("value");
            String jobTitleBox6 = findElementById(jobTitleBoxLoc6).getAttribute("value");


            assertEquals("Item not synced", firstNameBox6, (firstNameBox + "1"));
            assertEquals("Item not synced", lastNameBox6, lastNameBox + "1");
            assertEquals("Item not synced", address1Box6, address1Box + "1");
            assertEquals("Item not synced", address2Box6, address2Box + "1");
            assertEquals("Item not synced", cityBox6, cityBox + "1");
            //assertEquals("Item not synced",stateBox6,stateBox);
            assertEquals("Item not synced", zipBox6, zipBox + "1");
            assertEquals("Item not synced", phoneBox6, phoneBox + "1");
            assertEquals("Item not synced", companyBox6, companyBox + "1");
            assertEquals("Item not synced", jobLevelBox6, "Director");

            assertEquals("Item not synced", CountryBox6, "Zambia");
            assertEquals("Item not synced", linkedInBox6, linkedInBox + "1");
            assertEquals("Item not synced", twitterBox6, twitterBox + "1");
            assertEquals("Item not synced", facebookBox6, facebookBox + "1");
            assertEquals("Item not synced", blogBox6, blogBox + "1");
            assertEquals("Item not synced", jobTitleBox6, jobTitleBox + "1");


            remove1FromField(firstNameBoxLoc6);
            remove1FromField(lastNameBoxLoc6);
            remove1FromField(address1BoxLoc6);
            remove1FromField(address2BoxLoc6);
            remove1FromField(cityBoxLoc6);
            //remove1FromField(CountryBoxloc6);
        //    findElementById(CountryBoxLoc6).sendKeys("a");
        new Select(findElementById(CountryBoxLoc6)).selectByIndex(3);
            //remove1FromField(stateBoxloc6);
            remove1FromField(zipBoxLoc6);
            remove1FromField(phoneBoxLoc6);
            remove1FromField(companyBoxLoc6);
            //remove1FromField(jobLevelBoxloc6);
           // findElementById(jobLevelBoxLoc6).sendKeys("v");
        new Select(findElementById(jobLevelBoxLoc6)).selectByIndex(8);

        remove1FromField(linkedInBoxLoc6);
            remove1FromField(twitterBoxLoc6);
            remove1FromField(facebookBoxLoc6);
            remove1FromField(blogBoxLoc6);
            remove1FromField(jobTitleBoxLoc6);

            jobTitleBox6 = findElementById(jobTitleBoxLoc6).getAttribute("value");

            firstNameBox6 = findElementById(firstNameBoxLoc6).getAttribute("value");
            lastNameBox6 = findElementById(lastNameBoxLoc6).getAttribute("value");
            address1Box6 = findElementById(address1BoxLoc6).getAttribute("value");
            address2Box6 = findElementById(address2BoxLoc6).getAttribute("value");
            cityBox6 = findElementById(cityBoxLoc6).getAttribute("value");
            CountryBox6 = new Select(findElementById(CountryBoxLoc6)).getFirstSelectedOption().getText();
            // stateBox6=findElementById(stateBoxLoc6).getAttribute("value");
            zipBox6 = findElementById(zipBoxLoc6).getAttribute("value");
            phoneBox6 = findElementById(phoneBoxLoc6).getAttribute("value");
            companyBox6 = findElementById(companyBoxLoc6).getAttribute("value");
            jobLevelBox6 = new Select(findElementById(jobLevelBoxLoc6)).getFirstSelectedOption().getText();
            linkedInBox6 = findElementById(linkedInBoxLoc6).getAttribute("value");
            twitterBox6 = findElementById(twitterBoxLoc6).getAttribute("value");
            facebookBox6 = findElementById(facebookBoxLoc6).getAttribute("value");
            blogBox6 = findElementById(blogBoxLoc6).getAttribute("value");


            findElementById("edit-submit").click();
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div[4]/div/div/div/div/div[2]")));
            d6logOutProcess();

            driver.get(homeString + "/user/edit");

            firstNameBox = findElementById(firstNameBoxLoc).getAttribute("value");
            lastNameBox = findElementById(lastNameBoxLoc).getAttribute("value");
            address1Box = findElementById(address1BoxLoc).getAttribute("value");
            address2Box = findElementById(address2BoxLoc).getAttribute("value");
            cityBox = findElementById(cityBoxLoc).getAttribute("value");
            CountryBox = findElementById(CountryBoxLoc).getAttribute("value");
            // stateBox=findElementById(stateBoxLoc).getAttribute("value");
            zipBox = findElementById(zipBoxLoc).getAttribute("value");
            phoneBox = findElementById(phoneBoxLoc).getAttribute("value");
            companyBox = findElementById(companyBoxLoc).getAttribute("value");
            jobLevelBox = findElementById(jobLevelBoxLoc).getAttribute("value");
            linkedInBox = findElementById(linkedInBoxLoc).getAttribute("value");
            twitterBox = findElementById(twitterBoxLoc).getAttribute("value");
            facebookBox = findElementById(facebookBoxLoc).getAttribute("value");
            blogBox = findElementById(blogBoxLoc).getAttribute("value");
            jobTitleBox = findElementById(jobTitleBoxLoc).getAttribute("value");


            assertEquals("Item not synced", firstNameBox6, firstNameBox);
            assertEquals("Item not synced", lastNameBox6, lastNameBox);
            assertEquals("Item not synced", address1Box6, address1Box);
            assertEquals("Item not synced", address2Box6, address2Box);
            assertEquals("Item not synced", cityBox6, cityBox);
            assertEquals("Item not synced", "AF", CountryBox);
            // assertEquals("Item not synced",stateBox6,stateBox);
            assertEquals("Item not synced", zipBox6, zipBox);
            assertEquals("Item not synced", phoneBox6, phoneBox);
            assertEquals("Item not synced", companyBox6, companyBox);
            assertEquals("Item not synced", linkedInBox6, linkedInBox);
            assertEquals("Item not synced", twitterBox6, twitterBox);
            assertEquals("Item not synced", facebookBox6, facebookBox);
            assertEquals("Item not synced", blogBox6, blogBox);
            assertEquals("Item not synced", jobTitleBox6, jobTitleBox);

            assertEquals("Item not synced", jobLevelBox6, jobLevelBox);
            logout();
            goHome();

    }


    @Ignore
    public void clearThe1()
    {
        login();
        driver.get(homeString+"/user/edit");
        String firstNameBoxLoc="edit-field-first-name-und-0-value";
        String lastNameBoxLoc="edit-field-last-name-und-0-value";
        String address1BoxLoc="edit-field-address1-und-0-value";
        String address2BoxLoc="edit-field-address2-und-0-value";
        String cityBoxLoc="edit-field-city-und-0-value";
        String CountryBoxLoc="edit-field-country-und";
        String stateBoxLoc="edit-field-state-und-0-us";
        String zipBoxLoc="edit-field-zipcode-und-0-value";
        String phoneBoxLoc="edit-field-business-phone-und-0-value";
        String companyBoxLoc="edit-field-company-name-und-0-value";
        String jobLevelBoxLoc="edit-field-job-level-und";
        String linkedInBoxLoc="edit-data-connect-linkedin";
        String twitterBoxLoc="edit-data-connect-twitter";
        String facebookBoxLoc="edit-data-connect-facebook";
        String blogBoxLoc="edit-data-connect-personal-url";
        String jobTitleBoxLoc="edit-field-job-title-und-0-value";

        removeAll1sFromField(firstNameBoxLoc);
        removeAll1sFromField(lastNameBoxLoc);
        removeAll1sFromField(address1BoxLoc);
        removeAll1sFromField(address2BoxLoc);
        removeAll1sFromField(cityBoxLoc);
        //add1toField(CountryBoxLoc);
        //findElementById(CountryBoxLoc).sendKeys("afg");
        new Select(findElementById(CountryBoxLoc)).selectByIndex(1);
        //add1toField(stateBoxLoc);
        removeAll1sFromField(zipBoxLoc);
        removeAll1sFromField(phoneBoxLoc);
        removeAll1sFromField(companyBoxLoc);
        new Select(findElementById(jobLevelBoxLoc)).selectByIndex(0);
        //add1toField(jobLevelBoxLoc);
        removeAll1sFromField(linkedInBoxLoc);
        removeAll1sFromField(twitterBoxLoc);
        removeAll1sFromField(facebookBoxLoc);
        removeAll1sFromField(blogBoxLoc);
        removeAll1sFromField(jobTitleBoxLoc);



        findElementById("edit-submit--2").click();
        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div")));

    }

    @Test
    public void passwordChange()
    {
        login("Seleniumtester","Seleniumtester");
        driver.get(homeString+"/user/edit");
        easyWait(By.xpath(".//*[@id='edit-account']/legend/span/a")).click();
        new WebDriverWait(driver, 100).until(visibilityOf(easyWait(By.id("edit-mail"))));

        easyWait(By.id("edit-current-pass")).sendKeys("Seleniumtester");
        driver.findElement(By.id("edit-pass-pass1")).sendKeys("Seleniumtester1");
        driver.findElement(By.id("edit-pass-pass2")).sendKeys("Seleniumtester1");
        driver.findElement(By.id("edit-submit")).click();
        assertContains(easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase(),"The changes have been saved.".toLowerCase());
        logout();

        login("Seleniumtester","Seleniumtester1");

        driver.get(homeString+"/user/edit");
        easyWait(By.xpath(".//*[@id='edit-account']/legend/span/a")).click();
        new WebDriverWait(driver, 100).until(visibilityOf(easyWait(By.id("edit-mail"))));

        easyWait(By.id("edit-current-pass")).sendKeys("Seleniumtester1");
        driver.findElement(By.id("edit-pass-pass1")).sendKeys("Seleniumtester");
        driver.findElement(By.id("edit-pass-pass2")).sendKeys("Seleniumtester");
        driver.findElement(By.id("edit-submit")).click();
        assertContains(easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase(),"The changes have been saved.".toLowerCase());
        logout();
        login("Seleniumtester","Seleniumtester");
    }




    @Test
    public void emailChange()
    {
        login("bridgewaterrobbie+Seleniumtester@gmail.com", "Seleniumtester");
        driver.get(homeString + "/user/edit");
        easyWait(By.xpath(".//*[@id='edit-account']/legend/span/a")).click();
        new WebDriverWait(driver, 100).until(visibilityOf(easyWait(By.id("edit-mail"))));
        easyWait(By.id("edit-current-pass")).sendKeys("Seleniumtester");
        easyWait(By.id("edit-mail"), 100).clear();

        driver.findElement(By.id("edit-mail")).sendKeys("bridgewaterrobbie+Seleniumtester1@gmail.com");
        driver.findElement(By.id("edit-submit")).click();
        assertContains(easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase(),"The changes have been saved.".toLowerCase());
        logout();

        login("bridgewaterrobbie+Seleniumtester1@gmail.com","Seleniumtester");

        driver.get(homeString+"/user/edit");
        easyWait(By.xpath(".//*[@id='edit-account']/legend/span/a")).click();
        new WebDriverWait(driver, 100).until(visibilityOf(easyWait(By.id("edit-mail"))));
        easyWait(By.id("edit-current-pass")).sendKeys("Seleniumtester");
        easyWait(By.id("edit-mail"), 100).clear();
        driver.findElement(By.id("edit-mail")).sendKeys("bridgewaterrobbie+Seleniumtester@gmail.com");
        driver.findElement(By.id("edit-submit")).click();
        assertContains(easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase(),"The changes have been saved.".toLowerCase());
        logout();
        login("bridgewaterrobbie+Seleniumtester@gmail.com","Seleniumtester");
        logout();
    }


    @Test
    public void pictureDoesntDelete()
    {
        login("Seleniumtester","Seleniumtester");
        driver.get(homeString+"/user/edit");

        if(!easyWait(By.xpath(".//*[@id='edit-select-avatar']/div[1]/label/img")).isDisplayed())
            driver.findElement(By.xpath(".//*[@id='edit-select-avatar-wrap']/legend/span/a")).click();
        easyWait(By.xpath(".//*[@id='edit-select-avatar']/div[1]/label/img")).click();


        driver.findElement(By.id("edit-submit--2")).click();
        assertContains(easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase(),"The changes have been saved.".toLowerCase());
        driver.findElement(By.id("edit-submit--2")).click();
        assertContains(easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase(),"The changes have been saved.".toLowerCase());
        WebElement currentPic;
        if(doesElementExist(By.xpath(".//*[@id='edit-picture']/div/span/a/img")))
            currentPic=easyWait(By.xpath(".//*[@id='edit-picture']/div/span/a/img"));
        else
            currentPic=easyWait(By.xpath(".//*[@id='edit-picture--2']/div/span/a/img"));
        String source=currentPic.getAttribute("src");
        if(source.contains("user_icon_default.jpg"))
        {
            Assert.fail("The bug involving losing a profile pic is back. LDAP related bug 7755.");
        }
    }


    public void d6logOutProcess()
    {

        driver.get("http://members.techwell.com/logout");

    }
    public void d6LoginProcess()
    {
        if(CentralRunner.homeString.contains("stage"))
            driver.get("http://training:training@members.techwellstage.com/");
        else
            driver.get("members.techwell.com");
        if(!doesElementExist(By.xpath(".//*[@id='content-profile-display-profile']/div[1]/div/div/img")))
        {
        h.easyWait(By.id("edit-name")).sendKeys("Seleniumtester");
        findElementById("edit-pass").sendKeys("Seleniumtester");
        findElementById("edit-submit").click();
        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.id("content-profile-title-profile")));}
        if(CentralRunner.homeString.contains("stage"))
            driver.get("http://training:training@members.techwellstage.com/user/102514/edit/profile");
        else
            driver.get("http://members.techwell.com/user/102514/edit/profile");
    }
    public WebElement findElementByXpath(String s)
    {
        //shorthand for oft repeated find element by xpath
        return driver.findElement(By.xpath(s));
    }


    public void add1toField(String s)
    {
        WebElement field=findElementById(s);
        String contents=field.getAttribute("value");
        field.clear();
        String newContents=contents+"1";
        field.sendKeys(newContents);
        //field.sendKeys("1");
    }

    public void removeAll1sFromField(String s)
    {
        WebElement field=findElementById(s);
        String contents=field.getAttribute("value");
        field.clear();
        String newContents=contents.split("1")[0];
        field.sendKeys(newContents);

    }

    public void remove1FromField(String s)
    {
        WebElement field=findElementById(s);
        String contents=field.getAttribute("value");
        field.clear();
        field.sendKeys(contents.substring(0,contents.length()-1));
    }

    public WebElement findElementById(String s)
    {
        //shorthand for oft repeated find element by xpath
        return driver.findElement(By.id(s));
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
