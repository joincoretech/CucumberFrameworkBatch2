package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
         // it is path to the feature file. if the path is to features folder, all feature files will run
        // if the path is to the class, only that class will run
        features = "@target/failed.txt",

        // glue is a place or folder that we implement or define the gherkins steps
        // we should have path to the folder where we stored all our steps
        glue = "steps",

        // if we set the dryRun to true, it will quickly check that gherkins steps are defined
        // if dryRun is set to true, the browser will open and real test will run
        dryRun = false,
        // it will remove the unreadable text from the console and make the console readable
        monochrome = true,

       // tags="@smoke",

        plugin ={ "pretty", "html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed.txt" }

        
)

public class FailedRunner {

}
