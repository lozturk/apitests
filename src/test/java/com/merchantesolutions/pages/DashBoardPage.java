package com.merchantesolutions.pages;

import com.merchantesolutions.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {

    public DashBoardPage (){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//body/div/div/ul/li[4]/a")
    public WebElement usersButton;

    @FindBy(xpath = "(//*[.='New User'])[1]")
    public WebElement newUserButton;





}
