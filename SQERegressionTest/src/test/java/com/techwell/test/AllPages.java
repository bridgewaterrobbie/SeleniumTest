package com.techwell.test;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.techwell.test.HelperMethods.amILoggedIn;
import static com.techwell.test.HelperMethods.easyWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by robbiebridgewater on 8/18/14.
 */
public class AllPages {


    public static final String tagline = "is one of the growing communities of the TechWell network.";
    public static final bottomNavWrapper homeBot = new bottomNavWrapper(new String[]{"", "The Latest", "", "Ask a Question", "Recently Asked Questions", "Techwell Stories", "Recommended Web Seminars", "Featured Resources", "Communities", tagline});
    public static final bottomNavWrapper homeBotSM = new bottomNavWrapper(new String[]{"*", "", "The Latest", "", "Ask a Question", "Recently Asked Questions", "Techwell Stories", "Recommended Web Seminars", "Featured Resources", "Communities", tagline});
    public static final bottomNavWrapper articleBot = new bottomNavWrapper(new String[]{"[article]", "TechWell Stories", tagline});
    public static final bottomNavWrapper qaBot = new bottomNavWrapper(new String[]{"Ask a Question", "*", "Recent Q&A Activity", tagline});
    public static final bottomNavWrapper latestBot = new bottomNavWrapper(new String[]{"*", "TechWell Stories", tagline});
    public static final bottomNavWrapper topicsBotNav = new bottomNavWrapper(new String[]{"Articles", "TechWell Stories", "Recommended Web Seminars", "Featured Resources", "Visit our Other Communities", tagline});
    public static final bottomNavWrapper keyWordBot = new bottomNavWrapper(new String[]{"*", "Techwell Stories", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities", tagline});
    public static final bottomNavWrapper guideBot = new bottomNavWrapper(new String[]{"*", "*", "TechWell Stories", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities", tagline});
    public static final sideNavWrapper homeSide = new sideNavWrapper(new String[]{"*", "", "", "Follow Us", "Community Sponsors", "Upcoming Events", ""});
    public static final topNavWrapper top = new topNavWrapper(new String[]{"Home", "My Page", "Q&A", "Topics", "Resources", "Events", "Jobs", "Blog"});
    public static final topNavWrapper topCMC = new topNavWrapper(new String[]{"Home", "My Page", "Q&A", "Topics", "Resources", "Events", "Forum", "Jobs", "Blog"});
    public static final topicsNavWrapper stickyTopics = new topicsNavWrapper(new String[]{"Agile", "Analysis", "Automation", "Cloud", "Design & Develop", "Mobile", "People & Teams", "Performance", "Process", "Project Management", "QA", "Requirements", "Security", "Test Design", "Test Planning", "Tools"});
    public static final topicsNavWrapper cmcTopics = new topicsNavWrapper(new String[]{"Agile CM & ALM", "ALM & SCM Tools", "Application Lifecycle Management", "Build Engineering", "Change Management", "Cloud", "Continuous Integration", "DevOps", "Environment Management", "Mobile", "Release Management", "Version Control"});
    public static final topicsNavWrapper agileTopics = new topicsNavWrapper(new String[]{"Design & Code", "DevOps", "Enterprise", "Lean & Kanban", "People & Teams", "Planning", "Process", "Release management", "Requirements", "Scrum", "Testing", "Transition"});
    public static final topNavWrapper topNL = new topNavWrapper(new String[]{"Home", "", "Q&A", "Topics", "Resources", "Events", "Jobs", "Blog"});
    public static final topNavWrapper topCMCNL = new topNavWrapper(new String[]{"Home", "Q&A", "Topics", "Resources", "Events", "Forum", "Jobs", "Blog"});
    public static final sideNavWrapper articleSide = new sideNavWrapper(new String[]{"", "", "Most Popular", "Community Sponsors", "Follow Us","", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", ""});
    public static final sideNavWrapper articleSideSM = new sideNavWrapper(new String[]{"","","Most Popular","Community Sponsors","","Follow Us","Upcoming Events","Recommended Web Seminars","Featured Resources"});
    //public static final sideNavWrapper articleSideSM = new sideNavWrapper(new String[]{"", "", "Most Popular", "Community Sponsors", "Follow Us", "", "Upcoming Events", "Recommended Web Seminars"});
    public static final sideNavWrapper interviewSide = new sideNavWrapper(new String[]{"", "", "Most Popular", "Community Sponsors", "","Follow Us", ""});
    public static final sideNavWrapper qaSide = new sideNavWrapper((new String[]{"", "*", "Most Popular Q&A", "Community Sponsors", "Follow Us", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", ""}));
    public static final sideNavWrapper latestSide = new sideNavWrapper(new String[]{"*", "", "Most Popular", "Community Sponsors", "Follow Us", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", ""});
    public static final sideNavWrapper topicsSideNav = new sideNavWrapper(new String[]{"*", "", "", "Follow Us", "Community Sponsors", "Upcoming Events", ""});
    public static final sideNavWrapper sponsorsOnly = new sideNavWrapper(new String[]{"Community Sponsors"});
    public static final topicsNavWrapper resourceMenu = new topicsNavWrapper(new String[]{"Articles", "Better Software Magazine", "Books Guide", "Conference Presentations", "Interviews", "Tools & Services", "White Papers & Downloads"});
    //public static final topicsNavWrapper resourceMenu = new topicsNavWrapper(new String[]{"Articles", "Better Software Magazine", "Books Guide", "Conference Presentations", "Interviews", "Tools & Services", "White Papers & Downloads"});
    public static final topicsNavWrapper eventMenu = new topicsNavWrapper(new String[]{"Conferences", "Training", "Virtual Conferences", "Web Seminars"});
    public static final sideNavWrapper eventSidebar = new sideNavWrapper(new String[]{"Community Sponsors", "Follow Us"});
    public static final bottomNavWrapper LoggedMagBot = new bottomNavWrapper(new String[]{"[magazine]", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities"});
    public static final sideNavWrapper magPreSide = new sideNavWrapper(new String[]{"", "", "Most Popular", "Community Sponsors","","Follow Us", ""});
    public static final sideNavWrapper magPreSideNoLog = new sideNavWrapper(new String[]{"", "", "Most Popular", "Community Sponsors","Follow Us", ""});

    public static final sideNavWrapper keyWordSide = new sideNavWrapper(new String[]{"*", "", "", "Follow Us", "Community Sponsors", "Upcoming Events", ""});
    public static final bottomNavWrapper interviewBot = new bottomNavWrapper(new String[]{"[interview]", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities"});
    public static final bottomNavWrapper presentBotWrap = new bottomNavWrapper(new String[]{"[presentation]", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities"});
    public static final bottomNavWrapper resourceBotNav = new bottomNavWrapper(new String[]{"*", "*", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit our other communities"});
    public static final bottomNavWrapper resourceMagBotNav = new bottomNavWrapper(new String[]{"*", "*", "*", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit our other communities"});
    public static final sideNavWrapper resourceSideNav = new sideNavWrapper(new String[]{"", "", "Most Popular", "Community Sponsors", "","Follow Us", ""});
    public static final sideNavWrapper guideSide = new sideNavWrapper(new String[]{"*", "", "", "Follow Us", "Community Sponsors", "Upcoming Events", ""});
    public static final bottomNavWrapper toolBookBot = new bottomNavWrapper(new String[]{"*'*,*", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities"});
    public static final sideNavWrapper toolBookSide = new sideNavWrapper(new String[]{"", "", "most popular", "Comunity Sponsors", ""});
    public static final bottomNavWrapper whitePapBot = new bottomNavWrapper(new String[]{"*", "", "*", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities"});
    public static final bottomNavWrapper bookBot = new bottomNavWrapper(new String[]{"*", "Upcoming Events", "Recommended Web Seminars", "Featured Resources", "Visit Our Other Communities"});
    public static WebDriver driver;
    static HelperMethods h;

    //This constructor runs all tests. Looks at the current URL, and decides what to run
    public AllPages(WebDriver theDriver) {

        driver = theDriver;
        h = new HelperMethods(driver);
        String endURL = driver.getCurrentUrl().split("/")[driver.getCurrentUrl().split("/").length - 2];
        String secondURL = "";
        if (driver.getCurrentUrl().split("/").length > 3) {
            secondURL = driver.getCurrentUrl().split("/")[3];
        }
        if (endURL.contains(".com") || endURL.length() < 2) {
            endURL = driver.getCurrentUrl().split("/")[driver.getCurrentUrl().split("/").length - 1];
        }
// Most cases, we can identify the type of page by the second to last item in the URL. If the second to last item is the root with .com, then we want the last item
        assertTopMenu();
        /*The home menu. Its long so I collapsed it*/
        if (endURL.equals("") || endURL.contains(".com")) {//begin case 1, the longest: Home Page. Has most variations.
            if (!(amILoggedIn()) && driver.getCurrentUrl().contains("cmc")) {
                assertTopNav(topCMCNL);
            } else if (driver.getCurrentUrl().contains("cmc")) {
                assertTopNav(topCMC);
            } else if (amILoggedIn()) {
                assertTopNav(top);
            } else {
                assertTopNav(topNL);
            }
            assertSideNav(homeSide);
            if (amIonSM())
                assertBottomNav(homeBotSM);

            else assertBottomNav(homeBot);
        }
        else if (endURL.equals("article")) {

            assertBottomNav(articleBot);
            if (h.amIonSM()) {
                assertSideNav(articleSideSM);
            } else {
                assertSideNav(articleSide);
            }

        } else if (endURL.equals("q-and-a")) {
            assertBottomNav(qaBot);
            assertSideNav(qaSide);
        } else if (endURL.equals("latest")) {
            assertBottomNav(latestBot);
            assertSideNav(latestSide);
        } else if (endURL.equals("interview")) {
            assertBottomNav(interviewBot);
            assertSideNav(interviewSide);
        } else if (endURL.equals("blog") || secondURL.contains("blog")) {
            assertSideNav(sponsorsOnly);
        } else if (endURL.equals("events")) {
            assertSideNav(eventSidebar);
        } else if (endURL.equals("article")) {
            assertBottomNav(articleBot);
            assertSideNav(articleSide);
        } else if (endURL.equals("better-software-magazine-article")) {
            assertBottomNav(LoggedMagBot);
            if(amILoggedIn())
                assertSideNav(magPreSide);
            else
                assertSideNav(magPreSideNoLog);
        } else if (endURL.equals("presentation")) {
            if(amILoggedIn())
                assertSideNav(magPreSide);
            else
                assertSideNav(magPreSideNoLog);

            assertBottomNav(presentBotWrap);

        } else if (endURL.contains("terms-of-use") || endURL.contains("privacy-policy") || driver.getCurrentUrl().contains("/user") || endURL.contains(".com/search/")) {
        } else if (endURL.contains("about-us") || endURL.equals("webform") || endURL.equals("rss") || endURL.contains("sitemap") || endURL.contains("rss") || endURL.contains("member-benefits")) {
            assertSideNav(sponsorsOnly);
        }
        //resources pages need both the second to last and last part of the URL
        else if (endURL.equals("resources")) {
            if (driver.getCurrentUrl().contains("magazine-articles") || driver.getCurrentUrl().contains("presentations")) {
                assertSideNav(resourceSideNav);
                assertBottomNav(resourceMagBotNav);

            } else if (driver.getCurrentUrl().contains("whitepapers-downloads")) {
                assertBottomNav(whitePapBot);
                assertSideNav(resourceSideNav);
            } else {
                assertSideNav(resourceSideNav);
                assertBottomNav(resourceBotNav);
            }
        } else if (endURL.equals("keywords") || endURL.equals("other-keywords")) {
            assertBottomNav(keyWordBot);
            assertSideNav(keyWordSide);
        } else if (endURL.equals("tools-guide") || endURL.equals("books-guide")) {
            assertSideNav(guideSide);
            assertBottomNav(guideBot);
        } else if (driver.getCurrentUrl().contains("/topics/")) {
            assertBottomNav(topicsBotNav);
            assertSideNav(topicsSideNav);
        } else if (endURL.equals("book")) {

            assertSideNav(magPreSide);
            assertBottomNav(bookBot);
        } else if (endURL.equals("book-topic") || endURL.equals("category") || driver.getCurrentUrl().split("/")[driver.getCurrentUrl().split("/").length - 4].equals("tool")) {
            assertSideNav(magPreSide);
            assertBottomNav(resourceBotNav);
        } else {
            assertEquals("Could not find a URL match for all pages", 0, 1);//Intentionally fail the authBioCheck if you cant match against anything.
        }
    }

    public static void assertTopNav(topNavWrapper combo) {
        assertTemplate(combo, "/html/body/div[1]/header/div/div[3]/div/div/ul", "li", "topnav");
    }

    public static void assertSideNav(sideNavWrapper combo) {
        assertTemplate(combo, "/html/body/div[1]/div/aside/section", "div", "sidenav");
    }

    public static void assertBottomNav(bottomNavWrapper combo) {
        assertTemplate(combo, "/html/body/div[1]/div/div[1]", "div", "bottomNav");
    }

    public static void assertTopMenu() {
        String root;
        stringArrayWrapper combo;
        if (!h.amILoggedIn() && amIonCMC()) {
            root = "/html/body/div[1]/header/div/div[3]/div/div/ul/li[3]/div/div/div/div/div/ul";
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[3]")).click();

        } else {
            root = "/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]/div/div/div/div/div/ul";
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]")).click();
            new Actions(driver).moveToElement(driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]"))).moveByOffset(1,-10).moveByOffset(0,-100).moveToElement(driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]"))).build().perform();
        }
        if (driver.getCurrentUrl().contains("stickyminds")) {
            combo = stickyTopics;
        } else if (driver.getCurrentUrl().contains("cmcrossroads")) {
            combo = cmcTopics;
        } else {
            combo = agileTopics;
        }
        assertTemplate(combo, root, "li", "Topic Menu");



        if (!h.amILoggedIn() && amIonCMC()) {
            root = "/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]/div/div/div/div/div/ul";
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[4]")).click();

        } else {
            root = "/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]/div/div/div/div/div/ul";
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]")).click();
        }
        assertTemplate(resourceMenu, root, "li", "Resource Menu");


        if (!h.amILoggedIn() && amIonCMC()) {
            root = "/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]/div/div/div/div/div/ul";
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[5]")).click();

        } else {
            root = "/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]/div/div/div/div/div/ul";
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/ul/li[6]")).click();
        }
        assertTemplate(eventMenu, root, "li", "Event Menu");

    }

    //This template takes a root and type of item to be searched, and compares the list on the page with the provided list
    private static void assertTemplate(stringArrayWrapper combo, String rootPath, String extPath, String errorSource) {

        String[] names = combo.comboString;
        WebElement root = driver.findElement(By.xpath(rootPath));
        int i = 1;
        for (String name : names) {
            //blank string means picture
            if (name.compareTo("") == 0) {
                assertTrue("error at: " + driver.getCurrentUrl() + " " + "" + errorSource + ": " + i + "th item: Pictures should have no text, instead it is " + root.findElement(By.xpath(extPath + "[" + i + "]")).getText(), root.findElement(By.xpath(extPath + "[" + i + "]")).getText().compareTo("") == 0);
            } else if (name.equals("*")) {
                //any is the wildcard, useful for the featured block on the home page.
            } else {
                //Check several times for fast connection checks. Comment out these for slower computer.
                if (i == 1) {

                    new WebDriverWait(driver,10).until(h.textToBePresentInElementLocatedNoCase(By.xpath(rootPath+"/"+extPath+"[1]"),name));
                }

               // assertTrue("error at: " + driver.getCurrentUrl() + " " + errorSource + " [" + name + "]: Should be " + i + "th in the list, instead it is [" + root.findElement(By.xpath(extPath + "[" + i + "]")).getText()+"]", root.findElement(By.xpath(extPath + "[" + i + "]")).getText().toLowerCase().contains(name.toLowerCase()));
                assertTrue("error at: " + driver.getCurrentUrl() + " " + errorSource + " [" + name + "]: Should be " + i + "th in the list, instead it is [" + driver.findElement(By.xpath(rootPath+"/"+extPath + "[" + i + "]")).getText()+"]", driver.findElement(By.xpath(rootPath+"/"+extPath + "[" + i + "]")).getText().toLowerCase().contains(name.toLowerCase()));
            }
            i++;
        }


    }

    private static boolean amIonCMC() {
        return driver.getCurrentUrl().contains("cmcrossroads.com");
    }

    private static boolean amIonAgile() {
        return driver.getCurrentUrl().contains("agileconnection.com");
    }

    private static boolean amIonSM() {
        return driver.getCurrentUrl().contains("stickyminds.com");
    }


}


//These wrapper classes are to ease autocomplete when using the AllPages. They just wrap String arrays, differentiating what they will be used for.
abstract class stringArrayWrapper {
    public String[] comboString;

    public stringArrayWrapper(String[] toBeWrapped) {
        comboString = toBeWrapped;
    }
}

class sideNavWrapper extends stringArrayWrapper {

    public sideNavWrapper(String[] toBeWrapped) {
        super(toBeWrapped);
    }
}


class bottomNavWrapper extends stringArrayWrapper {

    public bottomNavWrapper(String[] toBeWrapped) {
        super(toBeWrapped);
    }
}

class topNavWrapper extends stringArrayWrapper {
    public topNavWrapper(String[] toBeWrapped) {
        super(toBeWrapped);
    }
}

class topicsNavWrapper extends stringArrayWrapper {
    public topicsNavWrapper(String[] toBeWrapped) {
        super(toBeWrapped);
    }
}