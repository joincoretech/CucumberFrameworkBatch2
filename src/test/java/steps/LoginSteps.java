package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {



    @When("user add valid username")
    public void user_add_valid_username() {
        LoginPage loginPage=new LoginPage();
        loginPage.userNameBox.sendKeys(ConfigReader.getPropertiesValue("userName"));
    }

    @When("user add valid password")
    public void user_add_valid_password() {
       LoginPage loginPage=new LoginPage();
       loginPage.passwordBox.sendKeys(ConfigReader.getPropertiesValue("password"));
    }

    @When("user click on the login button")
    public void user_click_on_the_login_button() {
       LoginPage loginPage=new LoginPage();
       loginPage.submitBtn.click();
    }

    @Then("validate user login successfully")
    public void validate_user_login_successfully() {
        DashboardPage dashboardPage=new DashboardPage();
        Assert.assertTrue(dashboardPage.hrmText.isDisplayed());

            }

    @When("user add invalid username")
    public void user_add_invalid_username() {
       LoginPage loginPage=new LoginPage();
       loginPage.userNameBox.sendKeys(ConfigReader.getPropertiesValue("wrongUserName"));
    }

    @When("user add invalid password")
    public void user_add_invalid_password() {
        LoginPage loginPage=new LoginPage();
        loginPage.passwordBox.sendKeys(ConfigReader.getPropertiesValue("wrongPassword"));
    }

    @Then("validate user see error message")
    public void validate_user_see_error_message() {
        String expectedText="Username and Password is Wrong!";
        LoginPage loginPage=new LoginPage();
        String actualText=loginPage.errorMessage.getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @When("user does not add username")
    public void user_does_not_add_username() {
      LoginPage loginPage=new LoginPage();
      loginPage.userNameBox.sendKeys("");
    }

    @Then("user validate error message credentials are required")
    public void user_validate_error_message_credentials_are_required() {
      LoginPage loginPage=new LoginPage();
      String actualText=loginPage.errorMessage.getText();
      String expectedText="UserName and Password is requi";
      Assert.assertEquals(actualText,expectedText);
    }

    @When("user add valid {string} and {string}")
    public void user_add_valid_and(String username, String password) {
        LoginPage loginPage=new LoginPage();
         sendText(loginPage.userNameBox, username);
         sendText(loginPage.passwordBox, password);
    }

    @When("user add username and password and validate error message")
    public void user_add_username_and_password_and_validate_error_message(DataTable errorData) {
        List<Map<String , String>> loginData=errorData.asMaps();

        for (Map<String , String > lDate:loginData){

            String userName= lDate.get("username");
            String password= lDate.get("password");
            String errorMessage= lDate.get("errormessage");
            System.out.println(userName+ " "+ password+" "+ errorMessage);

            LoginPage loginPage=new LoginPage();
            sendText(loginPage.userNameBox, userName);
            sendText(loginPage.passwordBox, password);
            click(loginPage.submitBtn);
            String actualErrorMessage=loginPage.errorMessage.getText();
            Assert.assertEquals("value does not match", errorMessage, actualErrorMessage);
        }

    }


    @When("user add username and password from excel {string} and verify the error message")
    public void user_add_username_and_password_from_excel_and_verify_the_error_message(String testData) {
       List<Map<String, String>>  excelLoginData= ExcelReader.excelIntoListMap(Constants.excelFilePath, testData);

       LoginPage loginPage=new LoginPage();

        Iterator<Map<String ,String>>  it=excelLoginData.iterator();

        while (it.hasNext()){
            Map<String , String> newMap=it.next();
            sendText(loginPage.userNameBox, newMap.get("username"));
            sendText(loginPage.passwordBox, newMap.get("password"));
            click(loginPage.submitBtn);
            System.out.println(newMap);

            String actualErrorMessage=loginPage.errorMessage.getText();

            Assert.assertEquals(actualErrorMessage, newMap.get("errormessage"));
            System.out.println("the test case passed");
        }
    }
}
