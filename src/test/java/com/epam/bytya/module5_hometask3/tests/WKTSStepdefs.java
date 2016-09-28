package com.epam.bytya.module5_hometask3.tests;

import com.epam.bytya.module5_hometask3.Pages.WKTSLoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Mikhail_Mereminskiy on 9/2/2016.
 */
public class WKTSStepdefs {

    private static final String HOME_URL = "https://wlst-sprint-wktsadmin-web-02.tw.intra:7810/AdminModule.html#manager/companies_search/companies_search";
    private static final String BLACKLIST_URL = "https://wlst-sprint-wktsadmin-web-02.tw.intra:7810/AdminModule.html#manager/blacklisted/blacklisted";

    @Given("^I am at the start page$")
    public void iAmAtTheStartPage() {
        WKTSTest.startBrowser();
    }

    @When("^I perform login$")
    public void iPerformLogin() {
        WKTSTest.loginToWKTS();
    }

    @Then("^I am redirected to home page$")
    public void iAmRedirectedToHomePage() {
        WKTSTest.assertLoginSuccess();
    }


    // Opening Blacklisted page
    @Given("^I am at the WKTS home page$")
    public void iAmAtTheWKTSHomePage() {
        WKTSTest.getDriver().get(HOME_URL);
    }

    @When("^I click Blacklisted menu item$")
    public void iClickBlacklistedMenuItem()  {
        WKTSTest.openBlacklisted();
    }

    @Then("^Blacklist page open$")
    public void blacklistPageOpen()  {
        WKTSTest.assertBlacklistedOpen();
    }

    // Unblock blacklisted company
    @Given("^I am at the Blacklist page$")
    public void iAmAtTheBlacklistPage(){
        WKTSTest.getDriver().get(BLACKLIST_URL);
    }

    @When("^I remove company from Blacklist$")
    public void iRemoveCompanyFromBlacklist() throws InterruptedException {
        WKTSTest.removeCompanyFromBlacklist();
    }

    @Then("^Company is not shown in the list$")
    public void companyIsNotShownInTheList(){
        WKTSTest.assertCompanyRemoved();
    }

    
    // Search for removed company
    @Given("^I am still  at the Blacklist page$")
    public void iAmStillAtTheBlacklistPage(){
        WKTSTest.getDriver().get(BLACKLIST_URL);
    }

    @When("^I type company name in search string$")
    public void iTypeCompanyNameInSearchString(){
        WKTSTest.blacklistSearch();
    }

    @Then("^the company not found$")
    public void theCompanyNotFound(){
        WKTSTest.assertCompanyNotFound();
    }
}
