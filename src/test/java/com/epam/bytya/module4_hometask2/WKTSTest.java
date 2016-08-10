package com.epam.bytya.module4_hometask2;

import org.openqa.selenium.WebDriver;
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

    // Initialization
    public static final String START_URL = "https://wlst-sprint-wktsadmin-web-02.tw.intra:7810/MainModule.html#login/";
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
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    // Perform login
    @Test(description = "Login to WKTS")
    public void loginToWKTS(){
        new WKTSLoginPage(driver).loginToWKTS(LOGIN, PASSWORD);
        //PageFactory.initElements(driver, WKTSLoginPage.class).loginToWKTS(LOGIN, PASSWORD);
        Assert.assertTrue(new WKTSHomePage(driver).isLoginSuccess(), "Login failed: ");
    }

    // Open the list of blacklisted companies
    @Test(dependsOnMethods = "loginToWKTS", description = "Open blacklisted companies list")
    public void openBlacklisted(){
        new WKTSHomePage(driver).openBlacklist();
        //PageFactory.initElements(driver, WKTSHomePage.class).openBlacklist();
        Assert.assertTrue(new WKTSBlacklistPage(driver).isPageShown(), "Blacklisted menu item is unavailable");
        System.out.println("");
    }

    // Remove first company from blacklist
    @Test(dependsOnMethods = "openBlacklisted", description = "Remove a company from blacklist")
    public void removeCompanyFromBlacklist() throws InterruptedException {
        new WKTSBlacklistPage(driver).unblockCompany();
        //PageFactory.initElements(driver, WKTSBlacklistPage.class).unblockCompany();
        Assert.assertTrue(new WKTSBlacklistPage(driver).isCompanyBlocked(), "Company not removed: ");
    }

    @AfterTest
    private void finishTest(){
        driver.close();
    }

}
