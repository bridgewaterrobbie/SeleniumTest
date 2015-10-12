package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by robbiebridgewater on 7/2/14.
 */
public class HomePageCheck {
    static WebDriver driver;
    public static String homeString;
    static HelperMethods h;
    static boolean runningFromCentral=true;
    //Create a generic string of the home location. This will allow me to run the same authBioCheck on different sites by changing this string

    public static void main (String[] args)
    {
        if(args.length==1)
            CentralRunner.homeString=args[0];
        org.junit.runner.JUnitCore.main("com.techwell.test.CentralRunner");

    }


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
        h=new HelperMethods(driver);

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
    public void checkSummary() {
        //WebElement element1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/p"));
        //Use * in xpath to avoid problems of SM
        //Find the first summary
        WebElement element1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/*/div[2]/table/tbody/tr[1]/td[2]/div/p"));
        String summary = element1.getText();
        //click the first item
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/*/div[2]/table/tbody/tr[1]/td[2]/a")).click();

        //Wait for things to load(long time for slow internet)
        new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("summary")));

        // System.out.println(authBioCheck.getText());
        //Find the new summary
        WebElement element2 = driver.findElement(By.className("summary"));
        //Remove the string "Summary: " from the front
        String summary2 = element2.getText().substring(9);
        //The two summaries should be the same, if not say what each of them are
        assertEquals("" + summary + " vs " + summary2, summary, summary2);
    }


    @Test
    public void recentCheck()
    {
        int month=13;
        int day;
        int year;
        String date=driver.findElement(By.cssSelector(".field-post-date")).getText().toLowerCase();

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
        day=Integer.parseInt(date.split(" ")[1].split(",")[0]);
        year=Integer.parseInt(date.split(", ")[1]);
        GregorianCalendar current=new GregorianCalendar();
        GregorianCalendar latest=new GregorianCalendar(year,month,day);
        long milliDif=current.getTimeInMillis()-latest.getTimeInMillis();
        assertTrue("more than a week old, "+milliDif/86400000+" days old",milliDif<604800000);


    }

    @Test
    public void checkAllTypesShow()
    {
        boolean seenArticle=false;
        boolean seenMagazine=false;
        boolean seenInterview=false;
        boolean seenPresentation=false;
        for(int i=1;i<6;i++)
        {
            String content=getContentTypeFromHome(i);
            if(content.contains("article")){seenArticle=true;}
            if(content.contains("interview")){seenInterview=true;}
            if(content.contains("presentation")){seenPresentation=true;}
            if(content.contains("magazine")){seenMagazine=true;}

            if(seenArticle&&seenInterview&&seenPresentation&&seenMagazine){return;}
        }
        driver.get(homeString+"/latest");
        for(int a=1;a<15;a++)
        {

            for(int i=1;i<16;i++)
            {
                String content=getContentTypeFromLatest(i);
                if(content.contains("article")){seenArticle=true;}
                if(content.contains("interview")){seenInterview=true;}
                if(content.contains("presentation")){seenPresentation=true;}
                if(content.contains("magazine")){seenMagazine=true;}

                if(seenArticle&&seenInterview&&seenPresentation&&seenMagazine){return;}
            }
        driver.findElement(By.partialLinkText("next")).click();
            new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/table/tbody/tr[1]/td[2]")));
        }

        assertTrue("Couldn't find all content types in first 15 pages. This could be an erorr, or just that we arent using one type of content.",false);
    }

    public static String getContentTypeFromLatest(int num)
    {
        return driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/table/tbody/tr["+num+"]/td[2]")).getText().split("\\[")[1].split("\\]")[0];

    }

    public static String getContentTypeFromHome(int num)
    {
        if(h.amIonAgile())
        {
            return driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr["+num+"]/td[2]")).getText().split("\\[")[1].split("\\]")[0];
        }
        if(h.amIonCMC())
        {
            return driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr["+num+"]/td[2]")).getText().split("\\[")[1].split("\\]")[0];
        }

        new WebDriverWait(driver,100).until(presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[2]")));
        return driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr["+num+"]/td[2]")).getText().split("\\[")[1].split("\\]")[0];
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
