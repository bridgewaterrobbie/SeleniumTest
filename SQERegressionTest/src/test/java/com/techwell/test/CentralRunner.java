package com.techwell.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.GregorianCalendar;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        Article.class,
        ArticleResource.class,
        AuthorCheck.class,
        BSMArticle.class,
        Events.class,
        HomePageCheck.class,
        Interview.class,
        InterviewResource.class,
        MagazineResource.class,
        Presentation.class,
        PresentationResource.class,
        QAMainCheck.class,
        SearchCheck.class,
        SiteForumsCheck.class,
        Jobs.class,
        UserProfile.class,
        SocialCheck.class,
        testHTAccess.class,
})
public class CentralRunner
{
    private final static Class[] allClasses = new Class[]{
            Article.class,
            ArticleResource.class,
            AuthorCheck.class,
            BSMArticle.class,
            Events.class,
            HomePageCheck.class,
            Interview.class,
            InterviewResource.class,
            MagazineResource.class,
            Presentation.class,
            PresentationResource.class,
            QAMainCheck.class,
            SearchCheck.class,
            SocialCheck.class,
            SiteForumsCheck.class,
            Jobs.class,
            UserProfile.class

    };




    public static void main (String[] args)
    {
        GregorianCalendar start=new GregorianCalendar();

        if(args.length>0)
        {
            for(String newHomeString:args)
            {
                System.out.println("Now running tests for: "+newHomeString);
                newHomeString=optimizeURL(newHomeString);
                CentralRunner.homeString=newHomeString;
                Class[] a=new Class[]{com.techwell.test.CentralRunner.class};
                org.junit.runner.JUnitCore.runClasses(a);
                System.out.println("Now ending tests for: "+newHomeString);
            }
        }
        if(args.length==0)
        {
            System.out.println("Tests for "+CentralRunner.homeString);
            org.junit.runner.JUnitCore.main("com.techwell.test.CentralRunner");
        }

        GregorianCalendar end=new GregorianCalendar();
        long milliDif=end.getTimeInMillis()-start.getTimeInMillis();
        long seconds=milliDif/1000;
        long minutes=seconds/60;
        long hours=minutes/60;
        milliDif=milliDif%1000;
        seconds=milliDif%60;
        minutes=minutes%60;
        System.out.println("Runtime of: "+hours+" Hours, "+minutes+" Minutes,"+seconds+" Seconds and "+milliDif+" milliseconds");

    }


    public static String optimizeURL(String preURL)
    {
        if(!preURL.contains("http://"))
            preURL="http://"+preURL;
        if((preURL.contains("communities.techwellstage")||preURL.contains("communities.techwelldev"))&&(!preURL.contains("twadmin:Welcome!23@")))
        {
            preURL=preURL.replace("http://","http://twadmin:Welcome!23@");
        }
        return preURL;
    }

    public static String homeString="http://agileconnection.com";
    public static WebDriver driver=null;
    public static boolean runningFromCentral=false;
    public static int retrycount=3;
    public static int loginPanicButton=0;


    @BeforeClass
    public static void setUp()
    {

        runningFromCentral=true;

        driver=new FirefoxDriver();
     //   driver=new HtmlUnitDriver(true);

    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

}

/*
        SearchCheck my2=new SearchCheck();
        Thread searchCheck=new Thread(my2);
        searchCheck.start();

        Events e=new Events();
        Thread eventsCheck=new Thread(e);
        eventsCheck.start();

        AllPageVerify a=new AllPageVerify();
        Thread aPVC=new Thread(a);
        aPVC.start();

        HomePageCheck h=new HomePageCheck();
        Thread hPC=new Thread(h);
        hPC.start();
*/





