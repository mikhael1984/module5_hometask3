package com.epam.bytya.module5_hometask3.utils;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Mikhail_Mereminskiy on 9/28/2016.
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/Resources/features/", glue = "com.epam.bytya.module5_hometask3.tests")

public class Runner {


}
