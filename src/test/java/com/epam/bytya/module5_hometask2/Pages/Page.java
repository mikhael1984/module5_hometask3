package com.epam.bytya.module5_hometask2.Pages;

import com.epam.bytya.module5_hometask2.utils.Highlighter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Mikhail_Mereminskiy on 8/8/2016.
 */
public abstract class Page {

    protected final WebDriver driver;
    protected final JavascriptExecutor executor;

    public Page(WebDriver driver){
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public Boolean isElementPresent (By locator){
        return driver.findElements(locator).size()>0;
    }

    public void highlight(WebElement element){
        Highlighter.highlightWithJS(element);
    }

}
