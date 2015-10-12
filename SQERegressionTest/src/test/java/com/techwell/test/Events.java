package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by robbiebridgewater on 9/8/14.
 */
public class Events {
    static WebDriver driver;
    static String homeString;
    static boolean runningFromCentral=true;
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
    public void conferences()
    {
        String wholeString=goToEventPageAndGetString(1);
        h.assertContains(wholeString,"Agile Development Conferences - At Agile Development Conferences, explore agile development practices, agile processes and organization, agile projects, agile design and programming,  transitions to agile, and leadership principles. Your registration includes full access to the Better Software Conference. Agile Development Conferences take place in two great places every year-pick your destination.");
        h.assertContains(wholeString,"At Better Software Conferences, learn about the latest tools, trends, and issues regarding software development, process improvement and measurement, plan-driven development, testing and quality assurance, security, and managing projects and teams. Your registration includes full access to the Agile Development Conference. Better Software Conferences take place in two great locations every year—pick your destination");
        h.assertContains(wholeString,"As the industry's premier software testing conferences, the STAR conferences offer a gathering place for software testers, developers, and managers to interact and learn how to improve software testing. The STAR conferences cover test management, test techniques, test automation, agile testing, mobile testing, cloud testing, performance testing, security testing, test metrics, and more.  STAR Conferences take place in three great locations every year— pick your destination");
        h.assertContains(wholeString,"The Mobile Dev + Test Conference addresses the most relevant topics in the rapidly evolving mobile and smart device software market. Learn the latest techniques in analysis, design, development, and testing. Hear from experts in the field about where the future of smart and mobile software is headed");
    }

    @Test
    public void virtualConferences()
    {
        String wholeString;

            wholeString=goToEventPageAndGetString(3);



        assertTrue(wholeString.contains("Our virtual conferences offer complimentary access to live presentations, the latest software development solutions, and networking opportunities—straight to your desktop. Virtual conferences are streamed live from many of our STAR, Better Software, or Agile Development conference events."));
        assertTrue(wholeString.contains("Using online learning technologies, you will take a “seat” at the conference of your choice and view industry-leading keynotes and live interviews with conference speakers to learn about the most current products and services for software development professionals."));
    }




    @Test
    public void training()
    {
        String wholeString;
            wholeString=goToEventPageAndGetString(2);


        assertTrue(wholeString.contains("is one of the world's largest providers of software improvement training. We provide the industry’s best resources to help organizations meet their software testing, development, management, security, configuration management, and requirements training needs."));
        assertTrue(wholeString.contains("SQE Training provides a wide selection of specialized courses that deliver field-proven techniques for producing high-quality software. These courses are developed and delivered by industry experts who have years of specialized experience, covering techniques used by top software organizations worldwide"));
        assertTrue(wholeString.contains("Our public classroom training is offered in more than fifty cities each year and allows the learner to interact one-on-one with instructors who have an average of fifteen to thirty years of experience. Attendees also benefit from the opportunity to network and interact with others while learning. Our public classroom environment allows the learner a distraction-free learning environment with access to immediate answers to questions and an opportunity to focus on training content."));
        assertTrue(wholeString.contains("On-site training is becoming more and more popular today as corporations look for ways to train their personnel—without the expense of travel. Companies who are looking to train six or more employees often find on-site training the most attractive option. There are many benefits to this type of training that help not only the corporation but also the employee who is being trained. SQE Training has more than sixty on-site courses available to train your company's employees."));
        assertTrue(wholeString.contains("Learn anywhere! Experience the benefits of classroom instruction at your location. Live, instructor-led classes are now available right from your computer! SQE Training uses Cisco’s WebEx technology to provide you with all the benefits and personal contact of classroom instruction—from your desktop. This convenient format allows for less disruption by limiting the time spent away from work and home. You get the same valuable content and instructor interaction as you would in the classroom but with the convenience and cost effectiveness of being online."));
        assertTrue(wholeString.contains("SQE Training offers several options for gaining or maintaining your certification in the software development field:"));
    }

    @Test
    public void webSeminar()
    {
        WebElement events;
        WebElement conferences;
        if(driver.getCurrentUrl().contains("cmcrossroads")&& !h.amILoggedIn())
        {
            events=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]"));
            conferences=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]/div/div/div/div/div/ul/li[1]/a"));
            events.click();
            h.clickInvisibleTopNavItem(5, 4);
        }
        else
        {
            events=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]"));
            conferences=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]/div/div/div/div/div/ul/li[1]"));
            events.click();
            h.clickInvisibleTopNavItem(6, 4);
        }

        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[1]")));

        String wholeString=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]")).getText();
        assertTrue(wholeString.contains("Learn More..."));
        assertTrue(wholeString.contains("Upcoming Web Seminars"));
        assertTrue(wholeString.contains("On Demand Web Seminars"));
    }




    public static String goToEventPageAndGetString(int menuNumber)
    {
        WebElement events;
        WebElement conferences;
        if(driver.getCurrentUrl().contains("cmcrossroads")&& !h.amILoggedIn())
        {
            events=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]"));
            conferences=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]/div/div/div/div/div/ul/li[1]/a"));
            events.click();
            h.clickInvisibleTopNavItem(5, menuNumber);
        }
        else
        {
            events=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]"));
            conferences=driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]/div/div/div/div/div/ul/li[1]"));
            events.click();
            h.clickInvisibleTopNavItem(6, menuNumber);
        }
        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/article/div/div/div")));
        new AllPages(driver);
        return driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/article/div/div/div")).getText();

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


