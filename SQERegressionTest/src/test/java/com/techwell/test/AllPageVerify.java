package com.techwell.test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by robbiebridgewater on 8/22/14.
 */
public class AllPageVerify implements Runnable{
    static WebDriver driver;
    static String homeString;
    static HelperMethods h;
    //static String[] ext=new String[]{"http://www.stickyminds.com/","http://www.stickyminds.com/q-and-a","http://www.stickyminds.com/topics/agile-stickyminds","http://www.stickyminds.com/resources/articles","http://www.stickyminds.com/article/why-can-t-it-tell-me-what-project-will-cost","http://www.stickyminds.com/resources/magazine-articles","http://www.stickyminds.com/better-software-magazine-article/how-does-security-testing-fit-my-qa-process","http://www.stickyminds.com/books-guide","http://www.stickyminds.com/book-topic/agile-lean-software-development","http://www.stickyminds.com/resources/presentations","http://www.stickyminds.com/presentation/video-agile-throwdown-munich-takes-columbus-agile-benchmark-study","http://www.stickyminds.com/resources/interviews","http://www.stickyminds.com/interview/take-authBioCheck-drive-acceptance-authBioCheck-driven-development-interview-jared-richardson","http://www.stickyminds.com/tools-guide","http://www.stickyminds.com/events/conferences-sm","http://www.stickyminds.com/resources/whitepapers-downloads","http://www.stickyminds.com/blog","http://www.stickyminds.com/book/ux-lean-startups-faster-smarter-user-experience-research-and-design","http://www.stickyminds.com/tool/category/agile-lean-development/agile-project-management","http://www.stickyminds.com/about-us-stickyminds","http://www.stickyminds.com/webform/contact-us","http://www.stickyminds.com/webform/advertise-us-1","http://www.stickyminds.com/terms-of-use-stickyminds","http://www.stickyminds.com/privacy-policy-stickyminds","http://www.stickyminds.com/webform/contribute-community","http://www.stickyminds.com/webform/site-feedback","http://www.stickyminds.com/stickyminds-sitemap","http://www.stickyminds.com/rss"};
    static String[] ext=new String[]{"http://www.cmcrossroads.com/","http://www.cmcrossroads.com/q-and-a","http://www.cmcrossroads.com/topics/application-lifecycle-management","http://www.cmcrossroads.com/resources/articles","http://www.cmcrossroads.com/article/why-can-t-it-tell-me-what-project-will-cost","http://www.cmcrossroads.com/resources/magazine-articles","http://www.cmcrossroads.com/better-software-magazine-article/how-does-security-testing-fit-my-qa-process","http://www.cmcrossroads.com/books-guide","http://www.cmcrossroads.com/book-topic/agile-lean-software-development","http://www.cmcrossroads.com/resources/presentations","http://www.cmcrossroads.com/presentation/video-agile-throwdown-munich-takes-columbus-agile-benchmark-study","http://www.cmcrossroads.com/resources/interviews","http://www.cmcrossroads.com/interview/take-authBioCheck-drive-acceptance-authBioCheck-driven-development-interview-jared-richardson","http://www.cmcrossroads.com/tools-guide","http://www.cmcrossroads.com/events/conferences-cmcrossroads","http://www.cmcrossroads.com/resources/whitepapers-downloads","http://www.cmcrossroads.com/blog","http://www.cmcrossroads.com/book/ux-lean-startups-faster-smarter-user-experience-research-and-design","http://www.cmcrossroads.com/tool/category/agile-lean-development/agile-project-management","http://www.cmcrossroads.com/about-us-cmcrossroads","http://www.cmcrossroads.com/webform/contact-us","http://www.cmcrossroads.com/webform/advertise-us","http://www.cmcrossroads.com/terms-of-use-cmcrossroads","http://www.cmcrossroads.com/privacy-policy-cmcrossroads","http://www.cmcrossroads.com/webform/contribute-community","http://www.cmcrossroads.com/webform/site-feedback","http://www.cmcrossroads.com/cmcrossroads-sitemap","http://www.cmcrossroads.com/user/login?destination=cmcrossroads-sitemap","http://www.cmcrossroads.com/rss"};
    //static String[] ext=new String[]{"http://www.agileconnection.com/","http://www.agileconnection.com/q-and-a","http://www.agileconnection.com/topics/design-code","http://www.agileconnection.com/resources/articles","http://www.agileconnection.com/article/why-can-t-it-tell-me-what-project-will-cost","http://www.agileconnection.com/resources/magazine-articles","http://www.agileconnection.com/better-software-magazine-article/how-does-security-testing-fit-my-qa-process","http://www.agileconnection.com/books-guide","http://www.agileconnection.com/book-topic/agile-lean-software-development","http://www.agileconnection.com/resources/presentations","http://www.agileconnection.com/presentation/video-agile-throwdown-munich-takes-columbus-agile-benchmark-study","http://www.agileconnection.com/resources/interviews","http://www.agileconnection.com/interview/take-authBioCheck-drive-acceptance-authBioCheck-driven-development-interview-jared-richardson","http://www.agileconnection.com/tools-guide","http://www.agileconnection.com/events/conferences-agileconnection","http://www.agileconnection.com/resources/whitepapers-downloads","http://www.agileconnection.com/blog","http://www.agileconnection.com/book/ux-lean-startups-faster-smarter-user-experience-research-and-design","http://www.agileconnection.com/tool/category/agile-lean-development/agile-project-management","http://www.agileconnection.com/about-us-agileconnection","http://www.agileconnection.com/webform/contact-us","http://www.agileconnection.com/webform/advertise-us","http://www.agileconnection.com/terms-of-use-agile","http://www.agileconnection.com/privacy-policy-agileconnection","http://www.agileconnection.com/webform/contribute-community","http://www.agileconnection.com/webform/site-feedback","http://www.agileconnection.com/agileconnection-sitemap","http://www.agileconnection.com/user","http://www.agileconnection.com/rss"};

    @BeforeClass
    public static void createDriver() {
        driver = CentralRunner.driver;
        if(driver==null){driver =new FirefoxDriver();}

        if(homeString==null){homeString = "http://www.stickyminds.com/";}
        h=new HelperMethods(driver,homeString);

        //Logger needs to be turned off if using HTMLUnitDriver, as it gives many verbose statements
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
    }


    @Before
    public void goHome() {
        //At the beginning of each authBioCheck go to the home, have the same start point.
        //        driver.get(homeString+"/other-keywords/interpersonal-skills");
//        driver.get(homeString+"/presentation/video-agile-throwdown-munich-takes-columbus-agile-benchmark-study");
    }

    @Test
    public void test()
    {
      //  new AllPages(driver,AllPages.homePageSideStrings,AllPages.homePageBottomStringsSM);
       // h.login();
        //driver.get(homeString);
        for(String a:ext) {
            driver.get(a);
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]/div/div/div/div/div/ul/li[1]/a")));
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]/div/div/div/div/div/ul/li[1]/a")));
//            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]/div/div/div/div/div/ul/li[1]/a")));


            new AllPages(driver);
        }
        driver.get(homeString);
        h.login();
        driver.get(homeString);
        //System.out.println("Logged In");
        for(String a:ext)
        {
            driver.get(a);
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]/div/div/div/div/div/ul/li[1]/a")));
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]/div/div/div/div/div/ul/li[1]/a")));
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]/div/div/div/div/div/ul/li[1]/a")));
            new AllPages(driver);
        }
    }


    @Test
    public void wat()
    {
        driver.get("http://cmcrossroads.com");
        new AllPages(driver);
    }
    @AfterClass
    public static void closeEverything() {
        driver.close();
      //  driver.quit();
    }

    @Override
    public void run() {
        homeString=CentralRunner.homeString;
        Result aTest;
        aTest=org.junit.runner.JUnitCore.runClasses(this.getClass());

        for(Failure i:aTest.getFailures())
        {
            System.out.println(i.getException()+"\nat: "+i.getDescription());
            System.out.println("trace: "+i.getTrace());
            System.out.println();
        }

    }
}
