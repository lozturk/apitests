package com.merchantesolutions.Pages;

import com.merchantesolutions.Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class UsersPage {

    public UsersPage (){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "user_username")
    public WebElement enterUserName;

    @FindBy(id = "user_password")
    public WebElement enterUserPassword;

    @FindBy(id = "user_email")
    public WebElement enterUserEmail;

    @FindBy(xpath = "//*[@value='Create User']")
    public WebElement createUserButton;

    @FindBy(xpath = "//tr[1]/td[3]")
    public WebElement lastCreatedUserName;

    @FindBy(xpath = "//tr[1]/td[4]")
    public WebElement lastCreatedEmail;

    @FindBy(xpath = "//tr[1]/td[6]/div/a[3]")
    public WebElement lastDeleteLink;

    @FindBy (xpath = "//tr/td[3]")
    public List <WebElement> userNames;

    @FindBy(xpath = "//*[@id=\"q_username_input\"]/select")
    public WebElement userNameDropDownMenu;

    @FindBy (id = "q_username")
    public WebElement userNameInputBox;

    @FindBy(name = "commit")
    public WebElement filterButtonInUsersPage;

    @FindBy(xpath = "//*[@id=\"q_email_input\"]/select")
    public WebElement emailDropDownMenu;

    @FindBy(id = "q_email")
    public WebElement emailInputBox;

    @FindBy(id = "q_created_at_gteq_datetime")
    public WebElement calendarFromBox;

    public WebElement setADayOfCurrentMonth(int day) {
        String xpath = "//*[@id=\"ui-datepicker-div\"]//tr//td//a[contains(text(),'" + day + "')]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(id = "q_created_at_lteq_datetime")
    public WebElement calendarToBox;

    public List<String> getAllNames() {
        List<WebElement> allNames = Driver.getDriver().findElements(By.xpath("//*[@id=\"index_table_users\"]//tr//td[3]"));
        List<String> allStringNames = new ArrayList<>();
        for (WebElement names : allNames) {
            allStringNames.add(names.getText());
        }
        return allStringNames;
    }


}
