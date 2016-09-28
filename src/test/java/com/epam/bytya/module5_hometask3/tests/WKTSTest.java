package com.epam.bytya.module5_hometask3.tests;

import com.epam.bytya.module5_hometask3.Pages.WKTSBlacklistPage;
import com.epam.bytya.module5_hometask3.Pages.WKTSHomePage;
import com.epam.bytya.module5_hometask3.Pages.WKTSLoginPage;
import com.epam.bytya.module5_hometask3.businessObjects.User;
import com.epam.bytya.module5_hometask3.driver.ChromeGen;
import com.epam.bytya.module5_hometask3.driver.FoxDriverGen;
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

    private String companyRemoved;

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

    //@BeforeClass(description = "Start browser")
    public static void startBrowser(){
        driver = getDriver();
        driver.get(START_URL);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

/*    @BeforeClass(description = "Add implicit wait and maximize browser window", dependsOnMethods = "startBrowser")
    public void addImplicitly(){

    }*/

    public static void loginToWKTS() {
        new WKTSLoginPage(driver).loginToWKTS(user);
    }

    public static void assertLoginSuccess(){
        Assert.assertTrue(new WKTSHomePage(driver).isLoginSuccess(), "Login failed: ");
    }

    // Open the list of blacklisted companies
    public static void openBlacklisted() {
        new WKTSHomePage(driver).openBlacklist();
    }

    public static void assertBlacklistedOpen(){
        Assert.assertTrue(new WKTSBlacklistPage(driver).isPageShown(), "Blacklisted menu item is unavailable: ");
    }

    // Remove first company from blacklist
    public static void removeCompanyFromBlacklist() throws InterruptedException {
        new WKTSBlacklistPage(driver).unblockCompany();
    }

    public static void assertCompanyRemoved(){
        Assert.assertFalse(new WKTSBlacklistPage(driver).isCompanyBlocked(), "Company not removed: ");
    }

    //Search in blacklist
    public static void blacklistSearch(){
        new WKTSBlacklistPage(driver).unblockedCompanySearch();

    }

    public static void assertCompanyNotFound(){
        Assert.assertFalse(new WKTSBlacklistPage(driver).isCompanyBlocked(), "Company was found, not unblocked: ");
    }

    @AfterTest (description = "Close browser window")
    private void finishTest(){
        driver.close();
    }

}
