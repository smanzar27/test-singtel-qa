package com.singtel.qa.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {  "pretty"
                    ,"html:target/cucumber"
                    ,"json:target/cucumber.json"
                    ,"junit:target/cucumber.xml"
        }
        ,   features = "src/test/resources/features"
        ,   glue = {"com.singtel.qa.test.stepdefinitions"}
        ,   tags = "@QATest"
        ,   monochrome = true
)

public class CucumberRunner extends AbstractTestNGCucumberTests {

}
