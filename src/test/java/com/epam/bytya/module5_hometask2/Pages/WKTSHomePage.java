package com.epam.bytya.module5_hometask2.Pages;

import com.epam.bytya.module5_hometask2.utils.Highlighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mikhail_Mereminskiy on 8/6/2016.
 */
public class WKTSHomePage extends Page {

    @FindBy(xpath = "//*[@id=\"gwt-debug-blacklisted_company\"]")
    private WebElement blacklistedCompanyMenuItem;

    public WKTSHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public boolean isLoginSuccess(){
        return blacklistedCompanyMenuItem.isDisplayed();
    }

    public void openBlacklist(){
        Highlighter.highlightWithJS(blacklistedCompanyMenuItem);
        blacklistedCompanyMenuItem.click();
    }

}
