package com.techwell.test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.techwell.test.HelperMethods.doesElementExist;
import static com.techwell.test.HelperMethods.easyWait;
import static org.junit.Assert.assertEquals;


/**
 * Created by erlebridgewater on 3/4/15.
 */
public class eventsCheckCompare {
    static WebDriver driver;
    static Scanner breaker = new Scanner(System.in);
    static String homeString;
    static HelperMethods h;
    static String currentd6Address;
    static String currentd7Address;



    public static void main(String[] args) {

        setup();
        login();
        checkContentType(2, 1, "conference_activity",50);

        //checkConferences(1, 2);

        shutdown();
    }

    //https://app.cvent.com/Subscribers/Account/default.aspx?page=70F93A2A-C8BA-411e-AA1B-0B46CBC6F6EB
    public static void setup()
    {
        driver = CentralRunner.driver;
        if(driver==null){driver =new FirefoxDriver();}
        homeString=CentralRunner.homeString;
        if(homeString==null){
            homeString = "http://events.techwelldev.com/admin/content";
        }
        h=new HelperMethods(driver,homeString);
        //Logger needs to be turned off if using HTMLUnitDriver, as it gives many verbose statements
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        driver.get("http://twadmin:Welcome123@events.techwelldev.com/admin/content");

    }
    public static void login()
    {
        driver.get("http://twadmin:Welcome123@events.techwelldev.com/admin/content");
        easyWait(By.cssSelector("#edit-name")).sendKeys("admin");
        breaker.next();
        driver.get("http://members.techwell.com");
        driver.get("http://members.techwell.com/user/login");
        easyWait(By.cssSelector("#edit-name")).sendKeys("admin");
        breaker.next();
    }
    public static void checkConferences(int startd7,int startd6)
    {
        int d7Pos=startd7;
        int d6Pos=startd6;
        setD7Mode("conference");
        setD6Mode("conference");
        String d6Page;
        String d7Page;
        currentd7Address="http://events.techwelldev.com/admin/content";
        for(int i=0;i<25;i++) {
            String D6Loc = ".//*[@id='edit-objects-wrapper']/table[2]/tbody/tr[" + d6Pos % 21 + "]/td[10]/a";
            String D7Loc = ".//*[@id='node-admin-content']/div/table[2]/tbody/tr[" + d7Pos % 50 + "]/td[8]/ul/li[1]/a";
            while(d6Pos>21)
            {
                driver.get(currentd6Address);
                easyWait(By.xpath(".//*[@id='content']/div[3]/div[4]/ul/li[3]/a")).click();
                d6Pos=d6Pos-19;
                currentd6Address=driver.getCurrentUrl();
            }

            while(d7Pos>50)
            {
                driver.get(currentd7Address);
                easyWait(By.cssSelector(".pager-next>a")).click();
                d7Pos=d7Pos-49;
                currentd7Address=driver.getCurrentUrl();
            }

            driver.get(currentd6Address);
            //Sort Alphebetically
            //driver.findElement(By.xpath(".//*[@id='edit-objects-wrapper']/table[2]/thead/tr/th[3]/a")).click();

            d6Page=easyWait(By.xpath(D6Loc)).getAttribute("href");

            driver.get(currentd7Address);
            //set alphebetically
            //driver.findElement(By.xpath(".//*[@id='node-admin-content']/div/table[2]/thead/tr/th[2]/a")).click();

            d7Page=easyWait(By.xpath(D7Loc)).getAttribute("href");

            try {
                compareConferencePages(d6Page, d7Page);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("D6 "+d6Pos+" D7 "+d7Pos);
                System.out.println("D6: "+d6Page+" D7: "+d7Page);
                System.out.println(driver.getCurrentUrl());
                System.out.println();
            }
            d6Pos++;
            d7Pos++;
        }

    }

    public static void compareConferencePages(String D6Page, String D7Page)
    {
        driver.get(D6Page);
        String title=easyWait(By.cssSelector("#edit-title")).getAttribute("value");
        String fromDate=driver.findElement(By.cssSelector("#edit-field-event-date-time-0-value-datepicker-popup-0")).getAttribute("value");
        String toDate=driver.findElement(By.cssSelector("#edit-field-event-date-time-0-value2-datepicker-popup-0")).getAttribute("value");

        String eventLocation=driver.findElement(By.cssSelector("#edit-field-event-location-0-value")).getAttribute("value");
        String venue=driver.findElement(By.cssSelector("#edit-field-event-locations-ref-0-nid-nid")).getAttribute("value").split("\\[")[0];

        List<WebElement> typesOfEvents=driver.findElements(By.xpath("/html/body/div[2]/div[4]/div/div/div/div/div[2]/form/div/div/div[1]/div[2]/div/div/div"));
        String trainingEvents=getTrainingEvent(typesOfEvents);
        String url=driver.findElement(By.cssSelector("#edit-field-conference-site-url-0-url")).getAttribute("value");
        //driver.findElement(By.cssSelector(".vertical-tabs-list-group_proceedings.vertical-tabs-nosummary>strong")).click();
        driver.findElement(By.cssSelector(".collapse-processed>a")).click();
        driver.findElements(By.cssSelector(".collapse-processed>a")).get(1).click();
        String procedingsTitle=easyWait(By.cssSelector("#edit-field-proceedings-title-0-value")).getAttribute("value");
        //easyWait(By.cssSelector(".vertical-tabs-list-group_registration.vertical-tabs-nosummary>strong")).click();
        String altText=easyWait(By.cssSelector("#edit-field-registration-image-0-data-alt")).getAttribute("value");
        String title2=easyWait(By.cssSelector("#edit-field-registration-image-0-data-title")).getAttribute("value");
        String url2=easyWait(By.cssSelector("#edit-field-registration-link-0-url")).getAttribute("value");
        String url3=easyWait(By.cssSelector("#edit-field-expo-registration-link-0-url")).getAttribute("value");


        //D6 Done
        driver.get(D7Page);
        easyWait(By.xpath(".//*[@id='conference-node-form']/div/fieldset[1]/legend/span/a")).click();
        easyWait(By.xpath(".//*[@id='conference-node-form']/div/fieldset[2]/legend/span/a")).click();

        verifyEquals(easyWait(By.cssSelector("#edit-title")).getAttribute("value"), title, "Title "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-event-date-time-und-0-value-datepicker-popup-0")).getAttribute("value"),fromDate, "fromDate "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-event-date-time-und-0-value2-datepicker-popup-0")).getAttribute("value"),toDate, "toDate "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-event-location-und-0-value")).getAttribute("value"),eventLocation, "eventLocation "+title);



        verifyEquals(easyWait(By.cssSelector("#edit-field-event-locations-ref-und-0-nid")).getAttribute("value").split("\\[]")[0],venue, "venue "+title);

        String eventType= new Select(easyWait(By.cssSelector("#edit-field-training-event-term-und"))).getFirstSelectedOption().getText();
        verifyEquals(eventType,trainingEvents, "trainingEvents "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-field-conference-site-url-und-0-url")).getAttribute("value"),url, "url "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-proceedings-title-und-0-value")).getAttribute("value"),procedingsTitle, "procedingsTitle "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-registration-image-und-0-alt")).getAttribute("value"), altText,"AltText "+title);
                verifyEquals(easyWait(By.cssSelector("#edit-field-registration-image-und-0-title")).getAttribute("value"),title2, "title2 "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-registration-link-und-0-url")).getAttribute("value"),url2, "url2 "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-expo-registration-link-und-0-url")).getAttribute("value"),url3, "url3 "+title);






    }


    public static void compareEventPages(String D6Page, String D7Page)
    {
        System.out.println();
        driver.get(D6Page);
        String title=easyWait(By.cssSelector("#edit-title")).getAttribute("value");
        String fromDate=driver.findElement(By.cssSelector("#edit-field-date-0-value-datepicker-popup-0")).getAttribute("value");
        String toDate=driver.findElement(By.cssSelector("#edit-field-date-0-value2-datepicker-popup-0")).getAttribute("value");

        driver.findElement(By.cssSelector(".collapse-processed>a")).click();
        easyWait(By.cssSelector("#edit-field-abstract-0-format-3-1")).click();


        boolean onlineSelected=driver.findElement(By.cssSelector("#edit-field-eventtype-value-Online")).isSelected();
        boolean regionalSelected=driver.findElement(By.cssSelector("#edit-field-eventtype-value-Regional")).isSelected();
        boolean globalSelected=driver.findElement(By.cssSelector("#edit-field-eventtype-value-Global")).isSelected();

        String abstractText=driver.findElement(By.cssSelector("#edit-field-abstract-0-value")).getAttribute("value");

        driver.findElement(By.xpath(".//*[@id='node-form']/div/div/div[1]/div[6]/fieldset/legend/a")).click();
        easyWait(By.cssSelector("#edit-format-3-1")).click();


        String description=driver.findElement(By.cssSelector("#edit-format-3-1")).getAttribute("value");

        String facebook=driver.findElement(By.cssSelector("#edit-field-event-facebook-0-url")).getAttribute("Value");
        String twitter=driver.findElement(By.cssSelector("#edit-field-event-twitter-0-url")).getAttribute("Value");

        String location=driver.findElement(By.cssSelector("#edit-field-simplelocation-0-value")).getAttribute("Value");
        //Not sure how to handle topics. Skipping them.



        //D6 Done
        driver.get(D7Page);


        verifyEquals(easyWait(By.cssSelector("#edit-title")).getAttribute("value"), title, "Title "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-date-und-0-value-datepicker-popup-0")).getAttribute("value"),fromDate, "fromDate "+title);
        if(fromDate.compareTo(toDate)!=0)
            verifyEquals(easyWait(By.cssSelector("#edit-field-date-und-0-value2-datepicker-popup-0")).getAttribute("value"),toDate, "toDate "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-field-eventtype-und-online")).isSelected(),onlineSelected,"Online "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-field-eventtype-und-regional")).isSelected(),regionalSelected,"regional "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-field-eventtype-und-global")).isSelected(),globalSelected,"global "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-field-simplelocation-und-0-value")).getAttribute("value"),location, "location "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-field-abstract-und-0-value")).getAttribute("value"),abstractText, "abstract "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-body-und-0-value")).getAttribute("value"),description, "description "+title);


        verifyEquals(easyWait(By.cssSelector("#edit-field-event-facebook-und-0-url")).getAttribute("value"),facebook, "facebook "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-field-event-twitter-und-0-url")).getAttribute("value"),twitter, "twitter "+title);



    }


    public static void compareConferenceActivityPages(String D6Page, String D7Page)
    {
        System.out.println();
        driver.get(D6Page);
        String title=easyWait(By.cssSelector("#edit-title")).getAttribute("value");


        easyWait(By.xpath(".//*[@id='node-form']/div/div/div[1]/div[2]/fieldset/legend/a")).click();
        easyWait(By.cssSelector("#edit-format-3-1")).click();
        String description=easyWait(By.cssSelector("#edit-body")).getAttribute("value");
        String fromDate=driver.findElement(By.cssSelector("#edit-field-class-date-time-0-value-datepicker-popup-0")).getAttribute("value");
        String fromTime=driver.findElement(By.cssSelector("#edit-field-class-date-time-0-value-timeEntry-popup-1")).getAttribute("value");

        String toDate=driver.findElement(By.cssSelector("#edit-field-class-date-time-0-value2-datepicker-popup-0")).getAttribute("value");

        String toTime=driver.findElement(By.cssSelector("#edit-field-class-date-time-0-value2-timeEntry-popup-1")).getAttribute("value");

        List<WebElement> events=driver.findElements(By.xpath("/html/body/div[1]/div[4]/div/div/div/div/div[2]/form/div/div/div[1]/div[3]/table[2]/tbody/tr"));
        events.get(0).findElement(By.xpath("td[2]/div/input")).getAttribute("value");
        ArrayList<String> eventNames=new ArrayList<String>();
       // for(int i=0;i<events.size();i++) {
        for(WebElement e:events){
            //eventNames.add(events.get(i).findElement(By.xpath("td[2]/div/input")).getAttribute("value"));
            eventNames.add(e.findElement(By.xpath("td[2]/div/input")).getAttribute("value"));
        }

        boolean techwellEvents=driver.findElement(By.cssSelector("#edit-domains--1")).isSelected();
        //boolean techwellConferencesAlumniHall=driver.findElement(By.cssSelector("#edit-domains--1")).isSelected();
        boolean agile=driver.findElement(By.cssSelector("#edit-domains-2")).isSelected();
        boolean hpSolutionCenter=driver.findElement(By.cssSelector("#edit-domains-3")).isSelected();
        boolean projectManagement=driver.findElement(By.cssSelector("#edit-domains-6")).isSelected();
        boolean testing=driver.findElement(By.cssSelector("#edit-domains-11")).isSelected();
        boolean sqeTraining=driver.findElement(By.cssSelector("#edit-domains-12")).isSelected();
        boolean conferenceMaster=driver.findElement(By.cssSelector("#edit-domains-16")).isSelected();
        boolean starEAST=driver.findElement(By.cssSelector("#edit-domains-21")).isSelected();
        boolean starWEST=driver.findElement(By.cssSelector("#edit-domains-26")).isSelected();
        boolean aDCoBSCEast=driver.findElement(By.cssSelector("#edit-domains-31")).isSelected();
        boolean aDCoBSCWest=driver.findElement(By.cssSelector("#edit-domains-36")).isSelected();
        boolean starCanada=driver.findElement(By.cssSelector("#edit-domains-47")).isSelected();
        boolean aDCEast=driver.findElement(By.cssSelector("#edit-domains-49")).isSelected();
        boolean bSCEast=driver.findElement(By.cssSelector("#edit-domains-50")).isSelected();
        boolean aDCWest=driver.findElement(By.cssSelector("#edit-domains-51")).isSelected();
        boolean bSCWest=driver.findElement(By.cssSelector("#edit-domains-52")).isSelected();
        boolean mobileDevANDTestConference=driver.findElement(By.cssSelector("#edit-domains-53")).isSelected();
        boolean starConferences=driver.findElement(By.cssSelector("#edit-domains-54")).isSelected();
        boolean agileDevelopmentConference=driver.findElement(By.cssSelector("#edit-domains-55")).isSelected();
        boolean betterSoftwareConference=driver.findElement(By.cssSelector("#edit-domains-56")).isSelected();
        boolean agileDevelopmentandBetterSoftware=driver.findElement(By.cssSelector("#edit-domains-57")).isSelected();
        boolean devOpsEast=driver.findElement(By.cssSelector("#edit-domains-58")).isSelected();
        boolean devOpsWest=driver.findElement(By.cssSelector("#edit-domains-59")).isSelected();
        boolean ioTDevANDTest=driver.findElement(By.cssSelector("#edit-domains-60")).isSelected();
        boolean mobileANDIoTDevTest=driver.findElement(By.cssSelector("#edit-domains-61")).isSelected();

        String sourceDomain=new Select(driver.findElement(By.cssSelector("#edit-domain-source"))).getAllSelectedOptions().get(0).getText();
        String metaCannonDomain=new Select(driver.findElement(By.cssSelector("#edit-domain-meta-canonical-domain"))).getAllSelectedOptions().get(0).getText();


        //D6 Done
        driver.get(D7Page);

        //getEvents at /html/body/div[3]/div[2]/div[2]/div/div/form/div/div[4]/div/div/table[2]/tbody

        new Select(easyWait(By.cssSelector("#edit-body-und-0-format--2"))).selectByValue("raw_html");

        List<WebElement> a=driver.findElements(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/form/div/div[4]/div/div/table[2]/tbody/tr"));
        ArrayList<String> d7Events=new ArrayList<String>();
        for(WebElement e:a)
        {
            d7Events.add(e.findElement(By.xpath("td[2]/div/input[1]")).getAttribute("value"));
        }


        for(int i=0;i<eventNames.size();i++)
        {
            verifyEquals(eventNames.get(i).split("\\[")[0],d7Events.get(i).split("\\[")[0],"Event list "+title);
        }
        verifyEquals(easyWait(By.cssSelector("#edit-title")).getAttribute("value"), title, "Title " + title);
        verifyEquals(easyWait(By.cssSelector("#edit-body-und-0-value")).getAttribute("value"),description,"Description "+title);

        verifyEquals(easyWait(By.cssSelector("#edit-domains-1")).isSelected(),techwellEvents,"techwellEvents "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-51")).isSelected(),aDCWest,"aDCWest "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-52")).isSelected(),bSCWest,"bSCWest "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-52")).isSelected(),mobileDevANDTestConference,"mobileDevANDTestConference "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-54")).isSelected(),starConferences,"starConferences "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-55")).isSelected(),agileDevelopmentConference,"agileDevelopmentConference "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-56")).isSelected(),betterSoftwareConference,"betterSoftwareConference "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-57")).isSelected(),agileDevelopmentandBetterSoftware,"agileDevelopmentandBetterSoftware "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-58")).isSelected(),devOpsEast,"devOpsEast "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-50")).isSelected(),bSCEast,"bSCEast "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-49")).isSelected(),aDCEast,"aDCEast "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-12")).isSelected(),sqeTraining,"sqeTraining "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-16")).isSelected(),conferenceMaster,"conferenceMaster "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-21")).isSelected(),starEAST,"starEAST "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-26")).isSelected(),starWEST,"starWEST "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-31")).isSelected(),aDCoBSCEast,"aDCoBSCEast "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-36")).isSelected(),aDCoBSCWest,"aDCoBSCWest "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-47")).isSelected(),starCanada,"starCanada "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-59")).isSelected(),devOpsWest,"devOpsWest "+title);
        verifyEquals(easyWait(By.cssSelector("#edit-domains-2")).isSelected(),ioTDevANDTest,"ioTDevANDTest "+title);

        String sourceDomain7=new Select(driver.findElement(By.cssSelector("#edit-domain-source"))).getAllSelectedOptions().get(0).getText();
        String metaCannonDomain7=new Select(driver.findElement(By.cssSelector("#edit-domain-meta-canonical-domain"))).getAllSelectedOptions().get(0).getText();
        verifyEquals(sourceDomain,sourceDomain7,title+" Source domain");
        verifyEquals(metaCannonDomain,metaCannonDomain7,title+" metaCannonDomain");



    }

    private static void verifyEquals(boolean a, boolean b, String situation) {
        if(a!=b)
            System.out.println(a+" and "+b+" do not match during "+situation);
    }


    public static void checkContentType(int startD6, int startD7, String contentType, int runTimes)
    {
        int d7Pos=startD7;
        int d6Pos=startD6;
        setD7Mode(contentType);
        setD6Mode(contentType);
        String d6Page;
        String d7Page;
        currentd7Address="http://events.techwelldev.com/admin/content";
        for(int i=0;i<runTimes;i++) {
            String D6Loc = ".//*[@id='edit-objects-wrapper']/table[2]/tbody/tr[" + d6Pos+ "]/td[10]/a";
            String D7Loc = ".//*[@id='node-admin-content']/div/table[2]/tbody/tr[" + d7Pos + "]/td[8]/ul/li[1]/a";
            while(d6Pos>21)
            {
                driver.get(currentd6Address);
                easyWait(By.xpath(".//*[@id='content']/div[3]/div[4]/ul/li[3]/a")).click();
                d6Pos=d6Pos-19;
                currentd6Address=driver.getCurrentUrl();
                D6Loc = ".//*[@id='edit-objects-wrapper']/table[2]/tbody/tr[" + d6Pos % 21 + "]/td[10]/a";

            }

            while(d7Pos>50)
            {
                driver.get(currentd7Address);
                easyWait(By.cssSelector(".pager-next>a")).click();
                d7Pos=d7Pos-49;
                currentd7Address=driver.getCurrentUrl();
                D7Loc = ".//*[@id='node-admin-content']/div/table[2]/tbody/tr[" + d7Pos % 50 + "]/td[8]/ul/li[1]/a";
            }

            driver.get(currentd6Address);
            //Sort Alphebetically
            driver.findElement(By.xpath(".//*[@id='edit-objects-wrapper']/table[2]/thead/tr/th[3]/a")).click();

            d6Page=easyWait(By.xpath(D6Loc)).getAttribute("href");

            driver.get(currentd7Address);
            //Sort alphebetically
            driver.findElement(By.xpath(".//*[@id='node-admin-content']/div/table[2]/thead/tr/th[2]/a")).click();

            d7Page=easyWait(By.xpath(D7Loc)).getAttribute("href");

            try {
                if(contentType.equals("event"))
                    compareEventPages(d6Page, d7Page);
                else if(contentType.equals("conference"))
                    compareConferencePages(d6Page,d7Page);
                else if(contentType.equals("conference_activity"))
                    compareConferenceActivityPages(d6Page,d7Page);
            }
            catch(UnsupportedClassVersionError e)
            {
                System.out.println(e.getMessage());
                System.out.println("D6 "+d6Pos+" D7 "+d7Pos);
                System.out.println(driver.getCurrentUrl());
                System.out.println();
            }
            d6Pos++;
            d7Pos++;
        }

    }



    public static void verifyEquals(String a, String b, String situation)
    {
                if(!a.toLowerCase().contains(b.toLowerCase()))
        //if(!a.toLowerCase().replace("\t","").replace("\n","").contains(b.toLowerCase().replace("\t","").replace("\n","")))
            System.out.println(a+" and HEREISDIFFERENCE "+b+" do not match during "+situation);
    }

    public static String getTrainingEvent(List <WebElement> list)
    {
        for(WebElement e: list)
        {

            if(e.findElement(By.xpath("label/input")).isSelected())
                return e.findElement(By.xpath("label")).getText();
        }
        return "";
    }
    public static void setD7Mode(String value)
    {
        driver.get("http://events.techwelldev.com/admin/content");
        resetD7ContentMode();
        Select picker=new Select(easyWait(By.cssSelector("#edit-type")));
        picker.selectByValue(value);
        easyWait(By.cssSelector("#edit-submit")).click();

        //Sort alphebetically
        //driver.findElement(By.xpath(".//*[@id='node-admin-content']/div/table[2]/thead/tr/th[2]/a")).click();
    }



    public static void setD6Mode(String value)
    {
        driver.get("http://members.techwell.com/admin/content/node/overview");
        Select picker=new Select(easyWait(By.cssSelector("#edit-type")));
        picker.selectByValue(value);
        easyWait(By.cssSelector("#edit-submit-sqe-admin-content")).click();
        currentd6Address=driver.getCurrentUrl();
        //sort alphebetically
        //driver.findElement(By.xpath(".//*[@id='edit-objects-wrapper']/table[2]/thead/tr/th[3]/a")).click();
    }

    public static void resetD7ContentMode()
    {
        driver.get("http://events.techwelldev.com/admin/content");
        if(doesElementExist(By.cssSelector("#edit-reset")))
        {
            easyWait(By.cssSelector("#edit-reset")).click();
        }
        easyWait(By.cssSelector("#edit-submit"));
    }



    public static void goHome() {
        driver.get(homeString);
    }

    //Use this one to search by ID, which should work

    public static void shutdown()
    {

        if(!CentralRunner.runningFromCentral)
        {
            driver.close();
            driver.quit();
            CentralRunner.driver=null;
        }
    }

}
