package com.mood.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "FitNesseRoot/IntegrationTests/chapter2",
        glue = "com.mood.cucumber",
		plugin = {"json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml"}        
)
public class Chapter2TestSuite {
}
