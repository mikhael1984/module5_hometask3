package com.epam.bytya.module4_hometask3.tests;

import com.epam.bytya.module4_hometask3.Pages.WKTSBlacklistPage;
import com.epam.bytya.module4_hometask3.Pages.WKTSHomePage;
import com.epam.bytya.module4_hometask3.Pages.WKTSLoginPage;
import com.epam.bytya.module4_hometask3.businessObjects.User;
import com.epam.bytya.module4_hometask3.driver.ChromeGen;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mikhail_Mereminskiy on 8/3/2016.
 */
public class WKTSTest {

    // Initialization
    public static final String START_URL = "https://wlst-sprint-wktsadmin-web-02.tw.intra:7810/MainModule.html#login/";
    public static final String FOX_PROFILE = "D:\\profile";
    public static final String CHROME_PROFILE = "c:\\Users\\mikhail_mereminskiy\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1";
    public static final String BINARY = "c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    public static final String LOGIN= "wktsadmin";
    public static final String PASSWORD = "pwd";

    private static WebDriver driver;
    private static User user;

    public static WebDriver getDriver(){
        if (driver == null){
            setDriver();
        }
        return driver;
    }

    private static void setDriver(){

        String choice = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //driver = new FoxDriverGen().generateDriver();
        driver = new ChromeGen().generateDriver();
    }

    @BeforeClass(description = "Start browser")
    public void startBrowser(){
        driver = getDriver();
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
        new WKTSLoginPage(driver).loginToWKTS(user);
        Assert.assertTrue(new WKTSHomePage(driver).isLoginSuccess(), "Login failed: ");
    }

    // Open the list of blacklisted companies
    @Test(dependsOnMethods = "loginToWKTS", description = "Open blacklisted companies list")
    public void openBlacklisted(){
        new WKTSHomePage(driver).openBlacklist();
        Assert.assertTrue(new WKTSBlacklistPage(driver).isPageShown(), "Blacklisted menu item is unavailable: ");
        System.out.println("");
    }

    // Remove first company from blacklist
    @Test(dependsOnMethods = "openBlacklisted", description = "Remove a company from blacklist")
    public void removeCompanyFromBlacklist() throws InterruptedException {
        new WKTSBlacklistPage(driver).unblockCompany();
        Assert.assertFalse(new WKTSBlacklistPage(driver).isCompanyBlocked(), "Company not removed: ");
    }

    //Search in blacklist
    @Test(dependsOnMethods = "removeCompanyFromBlacklist", description = "Search unblocked")
    public void blacklistSearch(){
        new WKTSBlacklistPage(driver).unblockedCompanySearch();
        Assert.assertFalse(new WKTSBlacklistPage(driver).isCompanyBlocked(), "Company was found, not unblocked: ");
    }

    @AfterTest (description = "Close browser window")
    private void finishTest(){
        driver.close();
    }

}
