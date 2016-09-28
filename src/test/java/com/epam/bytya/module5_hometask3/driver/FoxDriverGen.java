package com.epam.bytya.module5_hometask3.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by Mikhail_Mereminskiy on 8/29/2016.
 */
public class FoxDriverGen extends DriverGen {

    private static final String FOX_PROFILE = "D:\\profile";

    @Override
    public WebDriver generateDriver() {

        DesiredCapabilities dcFirefox = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile(new File(FOX_PROFILE));
        dcFirefox.setPlatform(Platform.WINDOWS);
        dcFirefox.setCapability(FirefoxDriver.PROFILE, profile);

        return new FirefoxDriver(dcFirefox);
    }

}
