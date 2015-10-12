package com.techwell.test;

import junit.framework.ComparisonFailure;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robbiebridgewater on 8/26/14.
 */
public class HelperMethods {
    static WebDriver driver;
    static final String defaultPassword = "Seleniumtester";
    static String baseUrl;
    static int easyWaitTimer=10;

//    public static String homeString="http://stickyminds.com/";;

    public HelperMethods(WebDriver d) {
        driver = d;
        baseUrl = CentralRunner.homeString;
    }

    public HelperMethods(WebDriver d, String url) {
        driver = d;
        baseUrl = url;
    }

    public static void clickInvisElement(String s)
    {
        ((JavascriptExecutor) driver).executeScript(""+s+".click();");
    }
    public static void login(String username, String password) {
        //   driver.get(baseUrl + "/");
    //    System.out.println(CentralRunner.loginPanicButton);
        if (amILoggedIn()) {
            return;
        }

        if(CentralRunner.loginPanicButton<5) {
            easyWait(By.xpath("/html/body/div[1]/header/div/div[1]/ul/li[1]/a"));
            driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/ul/li[1]/a")).click();
            new WebDriverWait(driver, 100).until(presenceOfElementLocated(By.id("edit-pass")));
            driver.findElement(By.id("edit-name")).clear();
            driver.findElement(By.id("edit-name")).sendKeys(username);
            driver.findElement(By.id("edit-pass")).clear();
            driver.findElement(By.id("edit-pass")).sendKeys(password);
            driver.findElement(By.id("edit-submit")).click();
            easyWait(By.xpath("/html/body/div[1]/header/a/img"));
            if(doesElementExist(By.cssSelector(".messages--error.messages.error"))) //&& easyWait(By.xpath(".//*[@id='content']/div")).getText().toLowerCase().contains("sorry, unrecognized username or password"))
                CentralRunner.loginPanicButton++;
            else
                CentralRunner.loginPanicButton=0;
        }

        else
            assertTrue("Seleniumtester password is messed up. Please fix",0==1);
      //  System.out.println(CentralRunner.loginPanicButton);

    }



    public static void login() {
        login("seleniumtester", defaultPassword);
    }

    public static void logout() {
        if (!amILoggedIn()) {
            return;
        }
        driver.get(baseUrl + "/user/logout");

        //driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/ul/li[2]/ul/li/ol/li[2]/a")).click();
    }

    public static void clickInvisibleTopNavItem(int menuNumber, int childNumber) {
        ((JavascriptExecutor) driver).executeScript("window.location=jQuery('.block-tb-megamenu li.tb-megamenu-item.level-1:nth-child(" + menuNumber + ") li.tb-megamenu-item.level-2:nth-child(" + childNumber + ") a').attr('href')");
    }

    public static void assertElementExists(By location, String item) {
        assertTrue(item + " Element at " + location + " not found", driver.findElements(location).size() > 0);//summary existance check
    }

    public static void assertElementExists(By location) {
        assertElementExists(location, "");
    }

    public static boolean amILoggedIn() {
        WebElement loginItem = easyWait(By.xpath("/html/body/div[1]/header/div/div[1]/h2"));
        return loginItem.isDisplayed();
    }

    public static boolean amIonCMC() {
        return driver.getCurrentUrl().contains("cmcrossroads.com");
    }

    public static boolean amIonAgile() {
        return driver.getCurrentUrl().contains("agileconnection.com");
    }

    public static boolean amIonSM() {
        return driver.getCurrentUrl().contains("stickyminds.com");
    }

    public static void runner(Class<?>[] a) {
        Result aTest;
        aTest = org.junit.runner.JUnitCore.runClasses(a);

        for (Failure i : aTest.getFailures()) {
            System.out.println(i.getException() + "\nat: " + i.getDescription());
            System.out.println();
        }

    }

    public static boolean doesElementExist(By b) {
        return driver.findElements(b).size() >= 1;
    }

    public static WebElement easyWait(By location, int seconds) {
        new WebDriverWait(driver, seconds).until(presenceOfElementLocated(location));
        return driver.findElement(location);
    }

    public static WebElement easyWait(By location) {
        return easyWait(location, easyWaitTimer);
    }

    public static void assertContains(String container, String contains) {
        assertContains("", container, contains);
    }

    public static void assertContains(String message, String container, String contains)

    {
        if (container.contains(contains) || contains == null) {
            return;
        }
        throw new ComparisonFailure(message, container, contains);

    }


    public static ExpectedCondition<Boolean> textToBePresentInElementLocatedNoCase(
            final By locator, final String text) {

        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = driver.findElement(locator).getText().toLowerCase();
                    return elementText.contains(text.toLowerCase());
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element found by %s",
                        text, locator);
            }
        };
    }
}