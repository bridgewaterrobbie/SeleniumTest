package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.techwell.test.HelperMethods.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by robbiebridgewater on 10/1/14.
 */
public class QAMainCheck {
    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    static boolean runningFromCentral=true;
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
        mainString=homeString+"/q-and-a";
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
    public void summaryCheck()
    {
        String summary=driver.findElement(By.xpath(".//*[@id='block-views-q-and-a-recent-activity-block']/div/div[1]/div[1]/div[2]/div[2]/div/p")).getText();
        driver.findElement(By.xpath(".//*[@id='block-views-q-and-a-recent-activity-block']/div/div[1]/div[1]/div[1]/span[2]/div[2]/a")).click();
        easyWait(By.xpath(".//*[@id='content']/question/div[2]/div[1]/div/div/p"));
        String summary2 = driver.findElement(By.xpath(".//*[@id='content']/question/div[2]/div[1]/div/div/p")).getText();
        assertContains(summary2,summary);
    }
    @Test
    public void searchCheck()
    {
        String title=driver.findElement(By.xpath(".//*[@id='block-views-q-and-a-recent-activity-block']/div/div[1]/div[1]/div[1]/span[2]/div[2]/a")).getText();
        String summary=driver.findElement(By.xpath(".//*[@id='block-views-q-and-a-recent-activity-block']/div/div[1]/div[1]/div[2]/div[2]/div/p")).getText();
        summary=summary.split("[.]")[0];
        driver.findElement(By.xpath(".//*[@id='quicktabs-ask_a_question']/ul/li[2]/a")).click();
        driver.findElement(By.id("edit-title--2")).sendKeys(SearchCheck.shrinkString128(summary));
        driver.findElement(By.id("edit-submit-q-and-a")).click();
        easyWait(By.xpath(".//*[@id='content']/div[1]/div[2]/div[1]/div[1]/span[2]/div[1]/a"));
        assertContains(driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[2]/div[1]/div[1]/span[2]/div[1]/a")).getText(),title);


    }


    @Test
    public void allPages()
    {
        new AllPages(driver);
        String qText=easyWait(By.xpath("/html/body/div[1]/div/div[1]/div[2]")).getText();
        if(qText.toLowerCase().contains("no unanswered questions"))
            return;
        assertContains(qText,"These unanswered questions need your expertise!");
    }

    @Test
    public void checkQuestionAge()
    {
        String qText=easyWait(By.xpath("/html/body/div[1]/div/div[1]/div[2]")).getText();
        if(qText.toLowerCase().contains("no unanswered questions"))
            return;

        String clickString=".field-content.raq-bullet a";
        if(amIonCMC())
        {
            if(easyWait(By.cssSelector(".field-content.raq-bullet a")).getText().contains("Call for Articles"))
            {
                if(!doesElementExist(By.xpath(".//*/div/div[2]/div[2]/div/span/a")))
                {
                    return;
                }
                clickString=".//*/div/div[2]/div[2]/div/span/a";
            }
        }

        easyWait(By.cssSelector(clickString)).click();


        int month=13;
        int day;
        int year;
        String date=driver.findElement(By.cssSelector(".submitted")).getText().toLowerCase();

        if(date.contains("jan"))
        {
            month=1;
        }
        if(date.contains("feb"))
        {
            month=2;
        }
        if(date.contains("mar"))
        {
            month=3;
        }
        if(date.contains("apr"))
        {
            month=4;
        }
        if(date.contains("may"))
        {
            month=5;
        }
        if(date.contains("june"))
        {
            month=6;
        }
        if(date.contains("july"))
        {
            month=7;
        }
        if(date.contains("aug"))
        {
            month=8;
        }
        if(date.contains("sep"))
        {
            month=9;
        }
        if(date.contains("oct"))
        {
            month=10;
        }
        if(date.contains("nov"))
        {
            month=11;
        }
        if(date.contains("dec"))
        {
            month=12;
        }


        month=month-1;
        day=Integer.parseInt(date.split(" ")[5].split(",")[0]);
        year=Integer.parseInt(date.split(", ")[1].split(" ")[0]);
        GregorianCalendar current=new GregorianCalendar();
        GregorianCalendar latest=new GregorianCalendar(year,month,day);
        long milliDif=current.getTimeInMillis()-latest.getTimeInMillis();
        assertTrue("more than a week old, "+milliDif/86400000+" days old",milliDif<604800000);

    }

    @Test
    public void askAQuestion()
    {

        String title=driver.findElement(By.xpath(".//*[@id='block-views-q-and-a-recent-activity-block']/div/div[1]/div[1]/div[1]/span[2]/div[2]/a")).getText();
        driver.findElement(By.id("edit-title")).sendKeys(title);
        driver.findElement(By.id("edit-submit")).click();

        if(!amILoggedIn())
        {
            assertEquals("You must be logged in to continue.".toLowerCase(),easyWait(By.xpath(".//*[@id='block-quicktabs-user-login']/h2")).getText().toLowerCase());
            login();
            goHome();
            askAQuestion();
        }

        else {
            assertContains(easyWait(By.xpath(".//*[@id='question-node-form']/div/div[1]/div[2]/table/tbody/tr[1]/td/div/span/a")).getText(),title);
            assertElementExists(By.xpath(".//*[@id='edit-body-und-0-value_tbl']/tbody/tr[1]/td"));
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