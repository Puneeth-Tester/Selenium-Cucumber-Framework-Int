package awesomecucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"html:target/cucumber/cucumber.html"},
        glue = {"awesomecucumber"},
        features = "src/test/resources/awesomecucumber"
)
public class MyTestNGRunnerTest extends AbstractTestNGCucumberTests {
}
