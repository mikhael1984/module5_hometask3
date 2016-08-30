package com.epam.bytya.module4_hometask3.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by Mikhail_Mereminskiy on 8/29/2016.
 */
public class ChromeGen extends DriverGen{
    @Override
    public WebDriver generateDriver() {
        System.setProperty("webdriver.chrome.driver", "d:\\Mentoring\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir", "c:\\Users\\mikhail_mereminskiy\\AppData\\Local\\Google\\Chrome\\User Data");
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }
}
