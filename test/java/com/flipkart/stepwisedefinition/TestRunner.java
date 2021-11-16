package com.flipkart.stepwisedefinition;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features  = "src//test//resources//Features//Amazon.feature",
                    glue = "com.flipkart.stepdefinition" ,
                   plugin = "html:target",
                   monochrome = true,
                   dryRun = false,
                     tags = "@Regression" )
public class TestRunner {

}
