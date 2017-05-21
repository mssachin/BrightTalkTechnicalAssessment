package com.qaexercise.brighttalk.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//import cucumber.api.junit.Cucumber;


/**
 * Created by Sachin on 20/05/2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/features/",
        glue = "com.qaexercise.brighttalk.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-html-report"}

)
public class CucumberTestunner {


}
