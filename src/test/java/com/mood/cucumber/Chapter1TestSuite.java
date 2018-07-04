package com.mood.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "FitNesseRoot/IntegrationTests/chapter1",
        glue = "com.mood.cucumber",
        plugin = {"json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml"}        
)
public class Chapter1TestSuite {
}
