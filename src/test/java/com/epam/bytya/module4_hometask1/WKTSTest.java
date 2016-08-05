package com.epam.bytya.module4_hometask1;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mikhail_Mereminskiy on 8/3/2016.
 */
public class WKTSTest {

    // Page elements
    public static final String START_URL = "https://wlst-sprint-wktsadmin-web.tw.intra:7810/";
    public static final String LOGIN_FIELD = "//*[@id=\"gwt-debug-enterUser\"]";
    public static final String PASSWORD_FIELD = "//*[@id=\"gwt-debug-enterPassword\"]";
    public static final String SIGN_IN = "//*[@id=\"gwt-debug-loginButton\"]";
    public static final String BLACKLISTED = "//*[@id=\"gwt-debug-blacklisted_company\"]";
    public static final String BLACKLIST_TABLE = "//*[@id=\"gwt-debug-cell-table\"]";
    public static final String COMPANY_ID_FIRST_ROW = "//*[@id=\"gwt-debug-cell-table\"]//tbody/tr[1]/td[3]";
    public static final String CHECKBOX = "//*[@id=\"gwt-debug-cell-table\"]//tbody/tr[1]//input[@type='checkbox']";
    public static final String UNBLOCK_BUTTON = "//*[@id=\"gwt-debug-unblock_company\"]";
    public static final String SEARCH_FIELD = "//*[@id=\"gwt-debug-search\"]";


    public static final String PROFILE = "D:\\profile";
    public static final String BINARY = "c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    public static final String LOGIN= "wktsadmin";
    public static final String PASSWORD = "pwd";

    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser(){
        FirefoxProfile firefoxProfile = new FirefoxProfile(new File(PROFILE));
        driver = new FirefoxDriver (new FirefoxBinary(new File(BINARY)), firefoxProfile);
        driver.get(START_URL);
    }

    @BeforeClass(description = "Add implicit wait and maximize browser window", dependsOnMethods = "startBrowser")
    public void addImplicitly(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    // Perform login

    @Test(description = "Login to WKTS")
    public void loginToWKTS(){
        doLogin(LOGIN, PASSWORD);
        Assert.assertTrue(isElementPresent(By.xpath(BLACKLISTED)), "Login failed");
    }

    private void doLogin(String login, String password){
        driver.findElement(By.xpath(LOGIN_FIELD)).sendKeys(login);
        driver.findElement(By.xpath(PASSWORD_FIELD)).sendKeys(password);
        driver.findElement(By.xpath(SIGN_IN)).click();
    }

    // Open the list of blacklisted companies
    @Test(dependsOnMethods = "loginToWKTS", description = "Open blacklisted companies list")
    public void openBlacklisted(){
        blacklistOpen();
        Assert.assertTrue(isElementPresent(By.xpath(BLACKLIST_TABLE)), "Blacklisted menu item is unavailable");
    }

    private void blacklistOpen(){
        driver.findElement(By.xpath(BLACKLISTED)).click();
    }

    private boolean isElementPresent(By by){
        return driver.findElement(by).isDisplayed();

    }

    // Remove first company from blacklist

    @Test(dependsOnMethods = "openBlacklisted", description = "Remove a company from blacklist")
    public void removeCompanyFromBlacklist() throws InterruptedException {

        String companyToRemove;

        companyToRemove = removeFromBlackList();
        Assert.assertTrue(driver.findElement(By.xpath(COMPANY_ID_FIRST_ROW)).getText() != companyToRemove, "Company not removed: ");
    }



    private String removeFromBlackList() throws InterruptedException {
        // Remember first company name in the list
        String companyToRemove = driver.findElement(By.xpath(COMPANY_ID_FIRST_ROW)).getText();
        // Remove the company from the list

        WebElement checkbox = driver.findElement(By.xpath(CHECKBOX));
        WebElement unblockButton = driver.findElement(By.xpath(UNBLOCK_BUTTON));

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", checkbox);
        executor.executeScript("arguments[0].click();", unblockButton);

        return companyToRemove;
    }

    @AfterTest
    private void finishTest(){
        driver.close();
    }


}
