package steps;

import io.cucumber.java.en.When;
import utils.DbUtils;
import utils.GlobalVariables;

import java.util.List;
import java.util.Map;

public class DbSteps {


    @When("user get data from back end")
    public void user_get_data_from_back_end() {
        String query="select firstname, middlename, lastname from employee where employeeid=13;";
        List<Map<String, String >> tableData= DbUtils.getTableDataAsListOFMap(query);

        GlobalVariables.dbFirstName=tableData.get(0).get("FirstName");
        GlobalVariables.dbMiddleName=tableData.get(0).get("MiddleName");
        GlobalVariables.dbLastName=tableData.get(0).get("LastName");

    }
}
