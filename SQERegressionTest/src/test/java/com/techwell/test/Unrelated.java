package com.techwell.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import sun.security.krb5.internal.EncASRepPart;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.techwell.test.HelperMethods.*;


/**
 * Created by robbiebridgewater on 9/8/14.
 */
public class Unrelated{

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

    @Test
    public void test()
    {
        driver.get("http://members.techwell.com/");
        easyWait(By.id("edit-name")).sendKeys("admin");
        easyWait(By.id("edit-pass")).sendKeys("$Mpu5TWell");
        easyWait(By.id("edit-submit")).click();


        String username;

        for(int i=1;i<50;i++)
        {
            driver.get("file:///Users/robbiebridgewater/Downloads/userUpdate.htm");
            username=easyWait(By.xpath("html/body/table/tbody/tr["+i+"]/td[1]")).getText();
            System.out.println(username);
            username=username.replace("_","");
            username=username.replace("@","");
            username=username.replace(".","");

            username=username.replace(" ","");

            System.out.println(username);
            driver.get("http://members.techwell.com/users/"+username);
            easyWait(By.xpath(".//*[@id='content-profile-title-profile']"));
        }

    }


    @Test
    public void runThroughList()
    {
        ArrayList<String> in = new ArrayList<String>();
        ArrayList<String> out = new ArrayList<String>();
        in.add("http://www.sqetraining.com/ways-save-2");
        in.add("http://www.sqetraining.com/training/course/managing-test-outsourcing");
        in.add("http://www.sqetraining.com/training/course/test-management");
        in.add("http://www.sqetraining.com/training/course/essential-test-management-and-planning");
        in.add("http://www.sqetraining.com/training/course/essential-test-management-and-planning");
        in.add("http://www.sqetraining.com/training/course/essential-test-management-and-planning");
        in.add("http://www.sqetraining.com/training/course/mastering-test-design");
        in.add("http://www.sqetraining.com/training/course/mastering-test-design");
        in.add("http://www.sqetraining.com/training/course/test-process-improvement");
        in.add("http://www.sqetraining.com/training/events/adcwest");
        in.add("http://www.sqetraining.com/training/events/adcwest");
        in.add("http://www.sqetraining.com/training/course/leadership-test-managers");
        in.add("http://www.sqetraining.com/training/course/leadership-test-managers");
        in.add("http://www.sqetraining.com/training/events/starwest");
        in.add("http://www.sqetraining.com/training/events/starwest");
        in.add("http://www.sqetraining.com/training/course/essential-software-requirements");
        in.add("http://www.sqetraining.com/training/course/systematic-software-testing");
        in.add("http://www.sqetraining.com/Veterans-Certification");
        in.add("http://www.sqetraining.com/training/course/software-tester-certification-foundation-level");
        in.add("http://www.sqetraining.com/training/course/software-tester-certification-foundation-level");
        in.add("http://www.sqetraining.com/training/course/measurement-and-metrics-test-managers");
        in.add("http://www.sqetraining.com/training/course/measurement-and-metrics-test-managers");
        in.add("http://www.sqetraining.com/sme-profiles/lee-copeland");

        out.add("http://www.sqe.com/Conferences/");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("http://www.sqe.com/AgileDevPracticesWest/");
        out.add("http://www.sqe.com/AgileDevPracticesWest/");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("http://www.sqe.com/starwest/");
        out.add("http://www.sqe.com/starwest/");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("http://www.sqe.com/TrainingReg/SelectTraining.aspx?course=ESTF");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");
        out.add("https://www.sqe.com/events/ecom/ecom.asp?ObjectType=SMBOOK&ReferenceID=677&Function=PREVIEW");

        for(int i=0;i<in.size();i++)
        {
            driver.get(in.get(i));
            if(driver.getCurrentUrl().equals(out.get(i)))
            {
                System.out.println("Good");
            }
            else
            {
                System.out.println("Bad");
            }
        }
    }
    @Test
    public void linksBadOrGood() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("test.csv", "UTF-8");
        writer.print("Link,Good/Bad,Title");
        writer.println();
        try {


            for (int i = 2; i < 2095; i++) {
                driver.get("file:///Users/robbiebridgewater/Downloads/StickyLInkCheck.htm");
                writer.print(easyWait(By.xpath("html/body/table/tbody/tr["+i+"]/td[1]")).getText() + ",");
                dipole value = checkLink(i);
                if (value.truthvalue) {
                    writer.print("GOOD");
                    System.out.println(i + ": Good");
                } else {
                    writer.print("BAD");
                    System.out.println(i + ": bad");
                }

                if (!value.title.equals(""))
                    writer.print("," + value.title);
                writer.println();
            }
            writer.close();
        }
        catch (Exception e)
        {
            writer.close();
            System.out.println("Error!");
            System.out.println(e);
        }
    }


    private dipole checkLink(int i)
    {
        String path=easyWait(By.xpath("html/body/table/tbody/tr["+i+"]/td[1]")).getText();
        driver.get(path);
        if(!doesElementExist(By.xpath("html/body/table[3]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td[2]/span"))&&!doesElementExist(By.xpath("html/body/table[2]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]")))
            return new dipole(true);
        if(doesElementExist(By.xpath("html/body/table[3]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td[2]/span")) && driver.findElement(By.xpath("html/body/table[3]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td[2]/span")).getText().toLowerCase().contains("sorry, but a problem was found looking for the page you requested. Our staff will investigate. In the meantime, click here to get back to the Home Page".toLowerCase()))
            return new dipole(false);

        if(doesElementExist(By.xpath("html/body/table[2]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]"))&&driver.findElement(By.xpath("html/body/table[2]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]")).getText().toLowerCase().contains("Sorry, your document could not be found".toLowerCase()))
            return new dipole(false);

        return new dipole(true);
    }

    @Test
    public void test2()
    {
        GregorianCalendar start=new GregorianCalendar();
        driver.get("http://gamefaqs.com");
        GregorianCalendar end=new GregorianCalendar();
        long milliDif=end.getTimeInMillis()-start.getTimeInMillis();
        long seconds=milliDif/1000;
        long minutes=seconds/60;
        long hours=minutes/60;
        milliDif=milliDif%1000;
        seconds=milliDif%60;
        minutes=minutes%60;
        System.out.println(hours+" Hours, "+minutes+" Minutes,"+seconds+" Seconds and "+milliDif+" milliseconds");
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




    @Test
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


}


class dipole
{
    public String title;
    public boolean truthvalue;

    public dipole(boolean t, String s)
    {
        truthvalue=t;
        title=s;
    }

    public dipole(boolean t)
    {
        truthvalue=t;
        title="";
    }


}

