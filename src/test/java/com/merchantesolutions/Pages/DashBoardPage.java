package com.merchantesolutions.Pages;

import com.merchantesolutions.Utilities.ConfigurationReader;
import com.merchantesolutions.Utilities.Driver;
import com.merchantesolutions.Utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {

    public DashBoardPage (){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "users")
    public WebElement usersButton;

    @FindBy(xpath = "(//*[.='New User'])[1]")
    public WebElement newUserButton;





}
