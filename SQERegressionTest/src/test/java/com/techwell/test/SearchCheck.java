package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by robbiebridgewater on 9/8/14.
 */
public class SearchCheck {

    String title;
    String description;
    String authName;
    String contentType;

    static WebDriver driver;
    static boolean runningFromCentral=true;
    static String homeString;
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
      //  logger.setLevel(Level.OFF);
    }



    @Before
    public void goHome() {
        driver.get(homeString);

        //At the beginning of each authBioCheck go to the home, have the same start point.
    }


    @Test
    public void mainSearchCheck()
    {
        String searchString;
        String resultTitle;
        if(driver.getCurrentUrl().contains("cmcrossroads.com")) {
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")));
            title = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")).getText();
            description = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/p")).getText();
            authName = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr[1]/td[3]/div/a")).getText();

            contentType = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr[1]/td[2]")).getText().split("\\[")[1].split("\\]")[0];
            boolean article=false;
            if(contentType.equals("article"))
                article=true;
            if (contentType.equals("article") || h.amILoggedIn() || contentType.equals("interview")) {
                driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")).click();
                String paragraphText;
                if(article)
                {
                   // paragraphText = h.easyWait(By.xpath(".//*[@id='content']/article/div[3]/div[2]/div/div/p[3]")).getText();
                    paragraphText = h.easyWait(By.cssSelector(".field-item.even p:nth-child(3)")).getText();
                }
                    else
                {
                 //   paragraphText=h.easyWait(By.xpath(".//*[@id='content']/article/div[2]/div[2]/div/div/p[3]")).getText();
                    paragraphText = h.easyWait(By.cssSelector(".field-item.even p:nth-child(3)")).getText();

                }


                searchString = shrinkString128(paragraphText.split("\\.")[0]);// + " " + title);
                WebElement searchBar = driver.findElement(By.id("edit-search-api-views-fulltext"));
                searchBar.sendKeys(searchString);
                searchBar.submit();

                new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div/p")));
                resultTitle = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/h3/a")).getText();
                if(!driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]")).getText().contains(title)) {
                    assertEquals("Searched item not found", title, resultTitle);
                }
                else {
                    if(!title.equals(resultTitle))
                        System.out.println("Searched item found but not first");
                }

            }
        }
        else
        {
            if(h.amIonSM())
            {
                new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[2]/a")));
                title= driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[2]/a")).getText();
                description=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[2]/div/p")).getText();
                authName=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[3]/div/a")).getText();

                contentType = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[2]")).getText().split("\\[")[1].split("\\]")[0];

            }
            else {
                new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")));
                title = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")).getText();
                description = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/p")).getText();
                authName = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]/div/a")).getText();
                contentType = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]")).getText().split("\\[")[1].split("\\]")[0];
            }
            if(contentType.equals("article")||h.amILoggedIn()||contentType.equals("interview"))
            {
                if(h.amIonSM()) {
                    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[2]/a")).click();
                }
                else{
                    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")).click();

                }

                new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/article/div[2]/div[2]/div/div/p[3]")));
                String paragraphText=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/article/div[2]/div[2]/div/div/p[3]")).getText();
                searchString=shrinkString128(paragraphText.split("\\.")[0]+" "+title);
                WebElement searchBar=driver.findElement(By.id("edit-search-api-views-fulltext"));
                searchBar.sendKeys(searchString);
                searchBar.submit();

                new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div/p")));
                resultTitle=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/h3/a")).getText();
                assertEquals("Titles are not the same, search failed",title,resultTitle);

            }

        }





        WebElement searchBar=driver.findElement(By.id("edit-search-api-views-fulltext"));
        searchString=shrinkString128(description);
        searchBar.clear();
        searchBar.sendKeys(searchString);
        searchBar.submit();

        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div/p")));
        resultTitle=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/h3/a")).getText();
        assertEquals("Titles are the same",title,resultTitle);
        if(contentType.equals("interview"))
        {
            WebElement intCount=driver.findElement(By.xpath("/html/body/div[1]/div/aside/section/div[1]/div/ul/li[5]/a"));
            assertNotEquals(intCount.getText(),"Interviews (0) Apply Interviews filter");
        }


        if(contentType.equals("[article]"))
        {
            WebElement intCount=driver.findElement(By.xpath("/html/body/div[1]/div/aside/section/div[1]/div/ul/li[1]/a"));
            assertNotEquals(intCount.getText(),"Articles (0) Apply Articles filter");
        }

        if(contentType.equals("[magazine]"))
        {
            WebElement intCount=driver.findElement(By.xpath("/html/body/div[1]/div/aside/section/div[1]/div/ul/li[2]/a"));
            assertNotEquals(intCount.getText(),"Articles (0) Apply Articles filter");
        }
        if(contentType.equals("[presentation]"))
        {
            WebElement intCount=driver.findElement(By.xpath("/html/body/div[1]/div/aside/section/div[1]/div/ul/li[4]/input"));
            assertNotEquals(intCount.getText(),"Articles (0) Apply Articles filter");
        }



        searchBar=driver.findElement(By.id("edit-search-api-views-fulltext"));
        searchBar.clear();

        searchString=shrinkString128(title);
        searchBar.sendKeys(searchString);
        searchBar.submit();
        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div/p")));
        resultTitle=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/h3/a")).getText();
        assertEquals("Titles are the same",title,resultTitle);

        searchBar=driver.findElement(By.id("edit-search-api-views-fulltext"));
        searchBar.clear();
        searchString=shrinkString128(authName+" "+title+" "+description);
        searchBar.sendKeys(searchString);
        searchBar.submit();
        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div/p")));
        resultTitle=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/h3/a")).getText();
        assertEquals("Titles are the same",title,resultTitle);

        searchBar=driver.findElement(By.id("edit-search-api-views-fulltext"));
        searchBar.clear();
        searchString=shrinkString128("adsfasdadfxcvcxds "+title+" "+description);
        searchBar.sendKeys(searchString);
        searchBar.submit();
        new WebDriverWait(driver,100).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/aside/section/div[1]/div/ul/li[3]/a")));
        List<WebElement> emptyList=driver.findElements(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/h3/a"));
        assertEquals(emptyList.size(),0);
        if(!h.amILoggedIn())
        {
            //h.login();
           // driver.get(homeString);
         //   authBioCheck();
        }




    }

    //@Test    public void willFail()    {        assertTrue("testing",false);    }

    public static String shrinkString128(String source)
    {
        if(source.length()<128)
            return source;
        source=source.substring(0,128);
        return source.substring(0,source.lastIndexOf(' '));
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
