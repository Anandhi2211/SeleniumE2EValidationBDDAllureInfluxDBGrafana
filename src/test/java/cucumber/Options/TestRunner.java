package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( // Enable Allure plugin
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/allure-results/cucumber.json",
                "html:target/cucumber-reports.html"},
        features = "src/test/java/features",
        glue = "stepDefinition"
//        monochrome = true
)
public class TestRunner {

}
