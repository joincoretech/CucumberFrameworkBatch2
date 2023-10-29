package steps;

import io.cucumber.java.en.When;
import  io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps {


    @When("user validate dashboard tabs")
    public void user_validate_dashboard_tabs(DataTable dataTable) {
        List<String> expectedData=dataTable.asList();

        DashboardPage dashboardPage=new DashboardPage();

        List<String> actualData=new ArrayList<>();
        for (WebElement ele: dashboardPage.dashboardTabs){
          actualData.add(ele.getText());

        }

        System.out.println(expectedData);
        System.out.println(actualData);
       // Assert.assertEquals(actualData, expectedData);
       Assert.assertTrue(actualData.equals(expectedData));

    }
}
