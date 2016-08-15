package com.epam.bytya.module4_hometask3.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mikhail_Mereminskiy on 8/6/2016.
 */
public class WKTSLoginPage extends Page {

    @FindBy(xpath = "//*[@id=\"gwt-debug-enterUser\"]")
    private WebElement enterUser;

    @FindBy(xpath = "//*[@id=\"gwt-debug-enterPassword\"]")
    private WebElement enterPassword;

    @FindBy(xpath = "//*[@id=\"gwt-debug-loginButton\"]")
    private WebElement loginButton;

    public WKTSLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void loginToWKTS(String login, String password){
        highlight(enterUser);
        enterUser.clear();
        enterUser.sendKeys(login);

        highlight(enterPassword);
        enterPassword.clear();
        enterPassword.sendKeys(password);

        highlight(loginButton);
        loginButton.click();
    }
}
