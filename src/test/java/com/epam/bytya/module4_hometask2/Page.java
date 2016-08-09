package com.epam.bytya.module4_hometask2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mikhail_Mereminskiy on 8/8/2016.
 */
public abstract class Page {

    protected final WebDriver driver;

    public Page(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public Boolean isElementPresent (By locator){
        return driver.findElements(locator).size()>0;
    }

}
