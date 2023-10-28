package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "//span[text()='Employee']")
    public WebElement employeeTab;

    @FindBy(xpath = "//a[text()='Employee View']")
    public WebElement employeeViewTab;

    @FindBy(xpath = "//a[text()='HRM']")
    public WebElement hrmText;

  public   DashboardPage(){
      PageFactory.initElements(driver, this);
    }
}
