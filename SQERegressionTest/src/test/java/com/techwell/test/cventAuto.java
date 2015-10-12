package com.techwell.test;

        import org.openqa.selenium.*;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.Select;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Scanner;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import static com.techwell.test.HelperMethods.doesElementExist;
        import static com.techwell.test.HelperMethods.easyWait;
        import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


/**
 * Created by erlebridgewater on 3/4/15.
 */
public class cventAuto {
    static WebDriver driver;
    static Scanner breaker = new Scanner(System.in);
    static String homeString;
    static HelperMethods h;

    public static void main(String[] args) {

        setup();
        login();


        driver.get("https://app.cvent.com/Subscribers/Events2/EventSelection");
        driver.findElement(By.cssSelector(".cv-button.cv-button-secondary.cv-button-icon-right.cvf-grid-filter-display")).click();
        driver.findElement(By.xpath(".//*[@id='cv-main-body']/div/div[2]/div/div[3]/div/ul/li[1]/div[2]/ul/li[9]/a")).click();
        turnOffSalesforce3(getConferenceURLList(turnOffSalesforce2(turnOffSalesforce1())));


        driver.get("https://app.cvent.com/Subscribers/Events2/EventSelection");
        driver.findElement(By.cssSelector(".cv-button.cv-button-secondary.cv-button-icon-right.cvf-grid-filter-display")).click();
        driver.findElement(By.xpath(".//*[@id='cv-main-body']/div/div[2]/div/div[3]/div/ul/li[1]/div[2]/ul/li[10]/a")).click();
        turnOffSalesforce3(turnOffSalesforce2(turnOffSalesforce1()));


        driver.get("https://app.cvent.com/Subscribers/Events2/EventSelection");
        driver.findElement(By.cssSelector(".cv-button.cv-button-secondary.cv-button-icon-right.cvf-grid-filter-display")).click();
        driver.findElement(By.xpath(".//*[@id='cv-main-body']/div/div[2]/div/div[3]/div/ul/li[1]/div[2]/ul/li[11]/a")).click();
        turnOffSalesforce3(turnOffSalesforce2(turnOffSalesforce1()));

        driver.get("https://app.cvent.com/Subscribers/Events2/EventSelection");
        driver.findElement(By.cssSelector(".cv-button.cv-button-secondary.cv-button-icon-right.cvf-grid-filter-display")).click();
        driver.findElement(By.xpath(".//*[@id='cv-main-body']/div/div[2]/div/div[3]/div/ul/li[1]/div[2]/ul/li[12]/a")).click();
        ArrayList<String> a =turnOffSalesforce2(turnOffSalesforce1());
       // System.out.println(a);
        turnOffSalesforce3(a);


        driver.get("https://app.cvent.com/Subscribers/Events2/EventSelection");
        driver.findElement(By.cssSelector(".cv-button.cv-button-secondary.cv-button-icon-right.cvf-grid-filter-display")).click();
        driver.findElement(By.xpath(".//*[@id='cv-main-body']/div/div[2]/div/div[3]/div/ul/li[1]/div[2]/ul/li[13]/a")).click();
        turnOffSalesforce3(turnOffSalesforce2(turnOffSalesforce1()));

        //System.out.println(getConferenceURLList(urls));
        shutdown();
    }

    private static ArrayList<String> turnOffSalesforce1() {
        ArrayList<String> urls=new ArrayList<String>();
     //   driver.get(url);

        boolean stop=false;
        boolean stop2=false;
        int i=1;
        while(!stop2) {
            while (!stop) {
                if (doesElementExist(By.xpath(".//*[@id='CustomViewInputModel-container']/table/tbody/tr[" + i + "]/td[1]/div/a")))
                {
                    urls.add(driver.findElement(By.xpath(".//*[@id='CustomViewInputModel-container']/table/tbody/tr[" + i + "]/td[1]/div/a")).getAttribute("href"));
                    i++;
                }
                else
                    stop = true;
            }
            String pageText=driver.findElement(By.cssSelector(".cv-page-status.cv-page-status")).getText();
            if(pageText.split("- ")[1].split(" ")[0].equals(pageText.split("of ")[1]))
                stop2=true;
            else {
                stop=false;
                i=1;
                easyWait(By.cssSelector(".cv-icon.cv-icon-pagination-next")).click();
            }
        }
        return urls;
    }

    private static ArrayList<String>turnOffSalesforce2(ArrayList<String> urls)
    {
        ArrayList<String> result = new ArrayList<String>();
        for(String url: urls) {
            driver.get(url);
            easyWait(By.xpath(".//*[@id='cv-nav']/div/ul/li[4]/a")).click();
            result.add(easyWait(By.xpath(".//*[@id='cv-nav']/div/ul/li[4]/div/div[2]/ul/li[1]/a")).getAttribute("href"));
        }

        return result;
    }

    //https://app.cvent.com/Subscribers/Account/default.aspx?page=70F93A2A-C8BA-411e-AA1B-0B46CBC6F6EB
    public static void setup()
    {
        driver = CentralRunner.driver;
        if(driver==null){driver =new FirefoxDriver();}
        homeString=CentralRunner.homeString;
        if(homeString==null){
            homeString = "https://app.cvent.com";
        }
        h=new HelperMethods(driver,homeString);
        //Logger needs to be turned off if using HTMLUnitDriver, as it gives many verbose statements
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        driver.get("https://app.cvent.com");

    }
    public static void login()
    {
        easyWait(By.cssSelector("#account")).sendKeys("SQEFLUS001");
        easyWait(By.cssSelector("#username")).sendKeys("MSowers");
        //easyWait(By.cssSelector("#tbxPassword")).sendKeys("teIv1fI2uK7h");
        breaker.next();
        easyWait(By.xpath(".//*[@id='cv-login']/div/div[2]/button")).click();
        easyWait(By.cssSelector(".cv-logo"));

    }
    public static void searchForID(String searchTerm)
    {
        driver.get("app.cvent.com");
        easyWait(By.cssSelector(".cv-icon.cv-icon-search-white")).click();
        easyWait(By.cssSelector(".cv-search-input.cv-corner-all.cv-input-focused")).sendKeys(searchTerm);
        easyWait(By.cssSelector(".cv-icon.cv-icon-search-white")).click();
        easyWait(By.xpath(".//*[@id='ctl02_ucAccountSearchEventList_rptrPagedList_ctl00_lnkEvent2']")).click();
    }

    public static void createGlobalDiscountCode(String discountName, String discountCode, int DiscountTypeAmountPercentageFlat,boolean active,boolean stackable, int ammountOrPercentage, String effectiveFrom, String effectiveTo, String note, boolean autoApply, boolean capacityTypeFullOrIndividual, int appliesTooInviteeOrGuestOrBoth, int capacity, boolean applyToAllEvents, ArrayList<advancedFilter> advancedFilters, boolean applyFiltersOnAllAboveOrAny)
    {
        driver.get("https://app.cvent.com/Subscribers/Account/default.aspx?page=70F93A2A-C8BA-411e-AA1B-0B46CBC6F6EB");
        easyWait(By.cssSelector("#ctl02_btnAddDiscount")).click();
        easyWait(By.cssSelector("#ctl02_e_txtName")).sendKeys(discountName);
        easyWait(By.cssSelector("#ctl02_e_txtCode")).sendKeys(discountCode);
        Select DiscountType=new Select(easyWait(By.cssSelector("#ctl02_e_ddlType")));
        DiscountType.selectByIndex(DiscountTypeAmountPercentageFlat);
        if(DiscountTypeAmountPercentageFlat!=2)
        {
            if(stackable)
                easyWait(By.cssSelector("#ctl02_e_rdbStackableYes")).click();
            else
                easyWait(By.cssSelector("#ctl02_e_rdbStackableNo")).click();

        }
        if(active)
            easyWait(By.cssSelector("#ctl02_e_rdbActiveYes")).click();
        else
            easyWait(By.cssSelector("#ctl02_e_rdbActiveNo")).click();


        easyWait(By.cssSelector("#ctl02_e_txtAmt")).sendKeys(Integer.toString(ammountOrPercentage));

        easyWait(By.cssSelector("#ctl02_e_txtStartDate")).sendKeys(effectiveFrom);

        easyWait(By.cssSelector("#ctl02_e_txtEndDate")).sendKeys(effectiveTo);

        easyWait(By.cssSelector("#ctl02_e_txtNote")).sendKeys(note);
        if(autoApply)
            driver.findElement(By.cssSelector("#ctl02_e_rdbAutoApplyYes")).click();
        else
            driver.findElement(By.cssSelector("#ctl02_e_rdbAutoApplyNo")).click();
        Select capacityType= new Select(easyWait(By.cssSelector("#ctl02_e_dcapacitytype")));
        if(capacityTypeFullOrIndividual)
        {
            capacityType.selectByIndex(0);
        }
        else
        {
            capacityType.selectByIndex(1);
        }

        Select appliesToo= new Select(easyWait(By.cssSelector("#ctl02_e_ddlAudienceType")));
        appliesToo.selectByIndex(appliesTooInviteeOrGuestOrBoth);
        easyWait(By.cssSelector("#ctl02_e_txtCapacity")).sendKeys(Integer.toString(capacity));

        if(applyToAllEvents)
            driver.findElement(By.cssSelector("#ctl02_e_rdbToAllEvents")).click();
        else
            driver.findElement(By.cssSelector("#ctl02_e_rdbToSpecEvt")).click();


        for(int i=1; i<advancedFilters.size()+1;i++)
        {
            advancedFilter f=advancedFilters.get(i-1);
            Actions builder=new Actions(driver);
            builder.click(driver.findElement(By.xpath(".//*[@id='ctl02_AdvancedFilterUC_e_cplTxt"+i+"']/div/img"))).sendKeys(f.field).sendKeys(Keys.TAB).perform();

          //  easyWait(By.cssSelector("#ctl02_btnFormButtonPanelSave")).click();

//            Select field = new Select(easyWait(By.cssSelector("#ctl02_AdvancedFilterUC_e_cplTxt"+i+"_FldList")));
  //          field.selectByValue(f.field);

       //g     Select operator=new Select(driver.findElement(By.cssSelector("#opt_ctl02_AdvancedFilterUC_e_ddlOperator"+i)));
//            operator.selectByValue(f.operator);

            builder.click(driver.findElement(By.cssSelector("#opt_ctl02_AdvancedFilterUC_e_ddlOperator"+i))).sendKeys(f.operator).sendKeys(Keys.TAB).perform();


            easyWait(By.cssSelector("#ctl02_AdvancedFilterUC_e_txtValue"+i)).sendKeys(f.value);
        }


        if(applyFiltersOnAllAboveOrAny)
            driver.findElement(By.cssSelector("#ctl02_AdvancedFilterUC_e_rdoAndSearch")).click();
        else
            driver.findElement(By.cssSelector("#ctl02_AdvancedFilterUC_e_rdoOrSearch")).click();


          easyWait(By.cssSelector("#ctl02_btnFormButtonPanelSave")).click();



    }

    public static void updateEmailConfirmation(ArrayList<String> conferences,String htmlMessage, String plainTextMessage)
    {
        for(String conference: conferences)
        {
            getFirstSearchResultFor(conference);
            //easyWait(By.cssSelector(".cv-mega-menu.is-hover>a")).click();
            easyWait(By.cssSelector(".cv-heading-1.cvf-heading-1"));

            //h.clickInvisElement("By.cssSelector(\".cv-mm-category>ul>li>a\")");
            String newURL=driver.getCurrentUrl();

            newURL=newURL.replace("/Overview/Overview/Index/View", "/Emails/EmailTemplatesGrid/Index/");

         //   newURL=newURL.replace("/Overview/Overview/Index/View","/Emails/EmailTemplatesGrid/Index/");

            driver.get(newURL);

            easyWait(By.linkText("Registration Confirmation")).click();
            easyWait(By.xpath(".//*[@id='cv-main-body']/div[2]/div[1]/div[3]/ul/li[2]/a")).click();
            //easyWait(By.cssSelector("#email-container>tbody>tr>td"));
            easyWait(By.cssSelector("#Edit")).click();
            if(htmlMessage.equals("")==false)
            {
                easyWait(By.xpath(".//*[@id='1_radEditor1_ModesWrapper']/ul/li[2]/a/span")).click();
                driver.switchTo().frame(driver.findElement(By.xpath("/html/body/form/div/div[4]/div[2]/div[2]/div/div[3]/div[2]/div[3]/div/table/tbody/tr[3]/td/iframe[2]")));
                easyWait(By.xpath("html/body/textarea")).clear();
                easyWait(By.xpath("html/body/textarea")).sendKeys(htmlMessage);
                driver.switchTo().defaultContent();

            }
            if(plainTextMessage.equals("")==false)
            {
                easyWait(By.cssSelector("#InputModel_ActEmailTxtText")).clear();
                easyWait(By.cssSelector("#InputModel_ActEmailTxtText")).sendKeys(plainTextMessage);
            }
            easyWait(By.cssSelector("#Save")).click();
        }

    }



    public static void goHome()
    {
        driver.get("https://app.cvent.com/Subscribers/default.aspx");
    }

    //Use this one to search by ID, which should work
    public static void getFirstSearchResultFor(String s)
    {
        goHome();
        easyWait(By.cssSelector(".cv-icon.cv-icon-search-white")).click();
        easyWait(By.cssSelector(".cv-search-input.cv-corner-all.cv-input-focused")).sendKeys(s);
        easyWait(By.cssSelector(".cv-search-input.cv-corner-all.cv-input-focused")).sendKeys(Keys.RETURN);

        easyWait(By.xpath(".//*[@id='ctl02_ucAccountSearchEventList_rptrPagedList_ctl00_lnkEvent2']")).click();
    }

    public static void shutdown()
    {
        goHome();
        easyWait(By.xpath(".//*[@id='cv-header']/div/div[2]/ul/li[4]/a")).click();
        easyWait(By.cssSelector("#account"));

        if(!CentralRunner.runningFromCentral)
        {
            driver.close();
            driver.quit();
            CentralRunner.driver=null;
        }
    }





    public static void turnOffSalesforce3(ArrayList<String> urls)
    {
        for(String url:urls) {
            driver.get(url);
            String url2 = driver.getCurrentUrl().replace("/RegistrationPathSettings/", "/RegistrationPathSecurity/").replace("/View?", "/Edit?");
            driver.get(url2);
            if(doesElementExist(By.cssSelector("#InputModel_RetrieveContactInfoFromExternal__None"))) {
                if (easyWait(By.cssSelector("#InputModel_RetrieveContactInfoFromExternal__None")).isDisplayed()) {
                    //easyWait(By.cssSelector("#InputModel_RetrieveContactInfoFromExternal__None"), 10).click();
                    if(!easyWait(By.cssSelector("#InputModel_RetrieveContactInfoFromExternal__None"), 10).isSelected())
                        System.out.println("The URL: "+driver.getCurrentUrl()+" is not selected");

                    //easyWait(By.cssSelector("#Save")).click();
                    easyWait(By.cssSelector("#Cancel")).click();

                    //easyWait(By.xpath(".//*[@id='cv-main-body']/div[2]/div[2]/div/div[2]/div/p/span[2]"));
                } else
                    System.out.println("Item: " + driver.getCurrentUrl() + " does not display button");
            }
            else
                System.out.println("Item: " + driver.getCurrentUrl() + " does not display button");


            try {
                Thread.sleep(15000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static ArrayList<String> getConferenceURLList(ArrayList<String> urls)
    {
        ArrayList<String> result = new ArrayList<String>();

        for(String url:urls) {
            driver.get(url);
            boolean stop = false;
            int i = 1;
            while (!stop) {
                if (doesElementExist(By.xpath(".//*[@id='RegistrationPaths-container']/table/tbody/tr[" + i + "]/td[1]/div/a"))) {
                    result.add(driver.findElement(By.xpath(".//*[@id='RegistrationPaths-container']/table/tbody/tr[" + i + "]/td[1]/div/a")).getAttribute("href"));
                    i++;
                } else
                    stop = true;
            }
        }

        return result;
    }

}
