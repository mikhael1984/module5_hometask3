package com.epam.bytya.module4_hometask3.Pages;

import com.epam.bytya.module4_hometask3.utils.Highlighter;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//*[@id=\"gwt-debug-unblock_company\"]")
    private WebElement unblockButton;

    @FindBy(xpath = "//*[@id=\"gwt-debug-search\"]")
    private WebElement searchString;

    private String companyToRemove;

    public WKTSBlacklistPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getCompanyToRemove(){
        return companyToRemove;
    }

    public void setCompanyToRemove(String companyToRemove){
        this.companyToRemove = companyToRemove;
    }

    public void unblockCompany() throws InterruptedException {

        // Remember the company to be unblocked
        setCompanyToRemove(companyIdFirstRow.getText());
        Highlighter.highlightWithJS(checkBox);
        executor.executeScript("arguments[0].click();", checkBox);
        Highlighter.highlightWithJS(unblockButton);
        executor.executeScript("arguments[0].click();", unblockButton);

    }


    // Check that Blacklist page opened
    public boolean isPageShown(){
        return blacklistTable.isDisplayed();
    }

    // Check if company is still blocked
    public boolean isCompanyBlocked(){
        return companyIdFirstRow.getText().equals(getCompanyToRemove());
    }

    // Perform blacklisted company search (should not be found)
    public void unblockedCompanySearch(){
        Highlighter.highlightWithJS(searchString);
        searchString.sendKeys(getCompanyToRemove() + Keys.ENTER);
    }

}
