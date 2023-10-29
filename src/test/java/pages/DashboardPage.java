package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "//span[text()='Employee']")
    public WebElement employeeTab;

    @FindBy(xpath = "//a[text()='Employee View']")
    public WebElement employeeViewTab;

    @FindBy(xpath = "//a[text()='HRM']")
    public WebElement hrmText;

    @FindBy(xpath = "//ul[@id='menu']/li")
    public List<WebElement> dashboardTabs;

  public   DashboardPage(){
      PageFactory.initElements(driver, this);
    }
}
