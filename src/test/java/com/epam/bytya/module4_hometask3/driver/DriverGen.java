package com.epam.bytya.module4_hometask3.driver;

import org.openqa.selenium.WebDriver;

/**
 * Created by Mikhail_Mereminskiy on 8/29/2016.
 */
public abstract class DriverGen {
    protected WebDriver driver;

    public abstract WebDriver generateDriver();
}
