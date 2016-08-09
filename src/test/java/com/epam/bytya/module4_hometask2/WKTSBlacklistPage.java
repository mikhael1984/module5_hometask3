package com.epam.bytya.module4_hometask2;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mikhail_Mereminskiy on 8/6/2016.
 */
public class WKTSBlacklistPage extends Page {

    @FindBy(xpath = "//*[@id=\"gwt-debug-cell-table\"]")
    private WebElement blacklistTable;

    @FindBy(xpath = "//*[@id=\"gwt-debug-cell-table\"]//tbody/tr[1]/td[3]")
    private WebElement companyIdFirstRow;

    @FindBy(xpath = "//*[@id=\"gwt-debug-cell-table\"]//tbody/tr[1]//input[@type='checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = "//*[@id=\"gwt-debug-cell-table\"]//tbody/tr[1]//input[@type='checkbox']")
    private WebElement unblockButton;

    private String companyToRemove;

    public WKTSBlacklistPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void unblockCompany() throws InterruptedException {

        // Remember the company to be unblocked
        Thread.sleep(1000);
        companyToRemove = companyIdFirstRow.getText();

        /*JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", checkbox);
        executor.executeScript("arguments[0].click();", unblockButton);*/

        // Select and unblock the company
        checkBox.click();
        unblockButton.click();
    }


    public boolean isPageShown(){
        return blacklistTable.isDisplayed();
    }

    public boolean isCompanyBlocked(){
        return !companyIdFirstRow.getText().equals(companyToRemove);
    }

}
