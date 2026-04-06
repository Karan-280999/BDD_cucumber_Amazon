package StepDefination;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/Features" , glue = "StepDefination" ,
//tags = "@Smoke",
monochrome = true ,
plugin = {"pretty",
        "html:target/HtmlReports/cucumber-report.html",
        "json:target/HtmlReports/cucumber-report.json"}
       )
public class Runner {

}
