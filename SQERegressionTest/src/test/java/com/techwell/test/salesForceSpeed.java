package com.techwell.test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.techwell.test.HelperMethods.doesElementExist;
import static com.techwell.test.HelperMethods.easyWait;

/**
 * Created by erlebridgewater on 2/17/15.
 */
public class salesForceSpeed {
    static WebDriver driver;
    static Scanner breaker = new Scanner(System.in);
    static String homeString;
    static HelperMethods h;

    public static void main (String[] args) throws IOException {
        setup();

        sfLogin();

        //alumFinder("alumfinder.txt","alumFound.txt");
        generateURLSFromEmails("deleteList.txt","deleteURLS.txt");
        generateURLSFromEmails("AlumList.txt", "AlumURLS.txt");
        deleteFromFile("deleteURLS.txt");
        alumFromFile("AlumURLS.txt");


        shutdown();




    }


    public static void alumFinder(String inFileName, String outFileName) throws IOException {
        ArrayList<String> input = new ArrayList<String>();
        PrintWriter out = new PrintWriter(new FileWriter(outFileName));


        BufferedReader in = new BufferedReader(new FileReader(inFileName));

        while (in.ready()) {
            String text = in.readLine();
            input.add(text);
        }


        for(String s:input)
        {
            easyWait(By.cssSelector("#phSearchInput")).sendKeys(s);
            easyWait(By.cssSelector("#phSearchButton")).click();
            if(!doesElementExist(By.cssSelector("#searchResultsWarningMessageBox")))
            {
                easyWait(By.cssSelector(".dataCell>a")).click();
                if(boxIsChecked("00N50000002mrts_chkbox")||boxIsChecked("00N50000002mru2_chkbox")||boxIsChecked("00N50000003UhCw_chkbox")||boxIsChecked("00N500000032yXf_chkbox")||boxIsChecked("00N50000003UhD1_chkbox")||boxIsChecked("00N50000002mru7_chkbox")||boxIsChecked("00N50000002mruH_chkbox")||boxIsChecked("00N50000002mruM_chkbox")||boxIsChecked("00N50000002mruR_chkbox"))
                    out.println("Alumni");
                else
                    out.println("NotAlum");
            }
            else
            {
                out.println("already gone");
            }
        }
        out.close();

    }

    public static boolean boxIsChecked(String e)
    {
        if(easyWait(By.id(e)).getAttribute("src").contains("checkbox_checked.gif"))
            return true;
        return false;
    }
    public void checkEmails() throws IOException {
        ArrayList<String> input=new ArrayList<String>();
        PrintWriter out = new PrintWriter(new FileWriter("emailoutputfile.txt"));


        BufferedReader in = new BufferedReader(new FileReader("emailChecker.txt"));

        while (in.ready()) {
            String text = in.readLine();
            input.add(text);
        }
        driver.get("http://www.verifyemailaddress.org/");


        for(String s:input)
        {
            out.print(s+" : ");
            easyWait(By.xpath(".//*[@id='email']")).clear();
            easyWait(By.xpath(".//*[@id='email']")).sendKeys(s);
            easyWait(By.xpath(".//*[@id='intro']/form/input[2]")).click();
            //out.println(easyWait(By.xpath(".//*[@id='content']/div/ul[1]/li[4]")).getText());
            String output=(easyWait(By.xpath(".//*[@id='content']/div/ul[1]")).getText().split("More details")[0]);
            if(output.contains("View Email Owner"))
                output=output.split("View Email Owner")[0]+"\n";
            out.println(output);
        }
        out.close();
    }

    public static void alumFromFile(String inputName) throws IOException
    {
        ArrayList<String> input = new ArrayList<String>();


        BufferedReader in = new BufferedReader(new FileReader(inputName));

        while (in.ready()) {
            String text = in.readLine();
            input.add(text);
        }

        for(String s:input)
        {
            String b=s.split("\\?")[0]+"/e";
            driver.get(b);
            String type=easyWait(By.xpath(".//*[@id='ep']/div[1]/table/tbody/tr/td[1]/h2")).getText();
            System.out.println(type);


            if(type.toLowerCase().contains("lead")) {
                easyWait(By.xpath(".//*[@id='00N50000002oIN2']")).clear();
                easyWait(By.xpath(".//*[@id='00N50000002oIN2']")).sendKeys("Alumni");
                if(easyWait(By.xpath(".//*[@id='00N50000002mZDG']")).isSelected())
                {
                    easyWait(By.xpath(".//*[@id='00N50000002mZDG']")).click();
                }
                easyWait(By.xpath(".//*[@id='00N50000002oISC']")).clear();
                easyWait(By.xpath(".//*[@id='topButtonRow']/input[1]")).click();
                easyWait(By.cssSelector("#phHeaderLogoImage"));

                //breaker.next();
            }
            else
            {//its a contact
                easyWait(By.xpath(".//*[@id='00N50000002oIMd']")).clear();
                easyWait(By.xpath(".//*[@id='00N50000002oIMd']")).sendKeys("Alumni");
                if(easyWait(By.xpath(".//*[@id='00N50000002mZDL']")).isSelected())
                {
                    easyWait(By.xpath(".//*[@id='00N50000002mZDL']")).click();
                }
                easyWait(By.xpath(".//*[@id='00N50000002oIS2']")).clear();
                breaker.next();

                easyWait(By.xpath(".//*[@id='topButtonRow']/input[1]")).click();
                easyWait(By.cssSelector("#phHeaderLogoImage"));

            }
        }

    }

    public static  void deleteFromFile(String inputName) throws IOException {
        ArrayList<String> input = new ArrayList<String>();


        BufferedReader in = new BufferedReader(new FileReader(inputName));

        while (in.ready()) {
            String text = in.readLine();
            input.add(text);
        }

        for(String s:input)
        {
            driver.get(s);
            if(!doesElementExist(By.cssSelector("#bodyCell>table>tbody>tr>td>span"))) {
                easyWait(By.xpath(".//*[@id='topButtonRow']/input[4]")).click();
//            new Actions(driver).sendKeys(Keys.ENTER).perform();
                Alert alert = driver.switchTo().alert();
                alert.accept();
                easyWait(By.cssSelector("#phHeaderLogoImage"));
                // breaker.next();
            }
        }
    }


    public static void generateURLSFromEmails(String inFileName, String outFileName) throws IOException {
        ArrayList<String> input = new ArrayList<String>();
        PrintWriter out = new PrintWriter(new FileWriter(outFileName));


        BufferedReader in = new BufferedReader(new FileReader(inFileName));

        while (in.ready()) {
            String text = in.readLine();
            input.add(text);
        }


        for(String s:input)
        {
            easyWait(By.cssSelector("#phSearchInput")).sendKeys(s);
            easyWait(By.cssSelector("#phSearchButton")).click();
            if(!doesElementExist(By.cssSelector("#searchResultsWarningMessageBox")))
            {
                easyWait(By.cssSelector(".dataCell>a")).click();
                out.println(driver.getCurrentUrl());
                //openNewTab(easyWait(By.cssSelector(".dataCell>a")));
            }
        }
        out.close();

    }

    public static void setup()
    {
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

    public static void openNewTab(WebElement e)
    {
        new Actions(driver).moveToElement(e).keyDown(Keys.COMMAND).click().keyUp(Keys.COMMAND).perform();

    }

    public static void sfLogin()
    {
        driver.get("https://login.salesforce.com");
        System.out.println("Hit enter after moving to next page");
        breaker.next();

    }

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
