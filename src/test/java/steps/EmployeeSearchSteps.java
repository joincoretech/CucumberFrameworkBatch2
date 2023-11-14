package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashboardPage;
import pages.EmployeeViewPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.GlobalVariables;

public class EmployeeSearchSteps extends CommonMethods {

    @Given("user login as admin")
    public void user_login_as_admin() {
        LoginPage loginPage=new LoginPage();
        // loginPage.userNameBox.sendKeys(ConfigReader.getPropertiesValue("userName"));
        sendText(loginPage.userNameBox, ConfigReader.getPropertiesValue("userName"));

       // loginPage.passwordBox.sendKeys(ConfigReader.getPropertiesValue("password"));
        sendText(loginPage.passwordBox, ConfigReader.getPropertiesValue("password"));

       // loginPage.submitBtn.click();
        click(loginPage.submitBtn);
    }

    @Given("user navigate to employee view page")
    public void user_navigate_to_employee_view_page() {
        DashboardPage dashboardPage=new DashboardPage();
        dashboardPage.employeeTab.click();
        dashboardPage.employeeViewTab.click();
    }

    @When("user add employee id in the search box")
    public void user_add_employee_id_in_the_search_box() {
        EmployeeViewPage employeeViewPage=new EmployeeViewPage();
        employeeViewPage.searchBox.sendKeys("105");
    }

    @When("user click the submit button")
    public void user_click_the_submit_button() {
       EmployeeViewPage employeeViewPage=new EmployeeViewPage();
       employeeViewPage.submitBtn.click();
    }

    @Then("user validate the employee is visible in the table")
    public void user_validate_the_employee_is_visible_in_the_table() {
      EmployeeViewPage employeeViewPage=new EmployeeViewPage();
      String actualText=employeeViewPage.name.getText();
      if (actualText.contains("Aziz")){
          Assert.assertTrue(true);
      }else {
          Assert.assertTrue(false);
      }

    }

    @When("user add employee name in the search box")
    public void user_add_employee_name_in_the_search_box() {
        EmployeeViewPage employeeViewPage=new EmployeeViewPage();
        employeeViewPage.searchBox.sendKeys("Aziz");
    }

    @When("user add employee {string}")
    public void user_add_employee(String data) {
        EmployeeViewPage employeeViewPage=new EmployeeViewPage();
        employeeViewPage.searchBox.sendKeys(data);
    }

    @When("user get data from employee view page")
    public void user_get_data_from_employee_view_page() {
        EmployeeViewPage employeeViewPage=new EmployeeViewPage();
       sendText(employeeViewPage.searchBox, "13");
       click(employeeViewPage.submitBtn);
       GlobalVariables.fullName=employeeViewPage.fullName.getText();
    }
    @Then("suer verify frontend data with backend")
    public void suer_verify_frontend_data_with_backend() {
       System.out.println(GlobalVariables.dbFirstName+" "+GlobalVariables.dbMiddleName+" "+GlobalVariables.dbLastName);
       String dbFullName=GlobalVariables.dbFirstName+" "+GlobalVariables.dbMiddleName+" "+GlobalVariables.dbLastName;
       System.out.println(GlobalVariables.fullName);
       Assert.assertEquals(dbFullName, GlobalVariables.fullName);
    }
}
