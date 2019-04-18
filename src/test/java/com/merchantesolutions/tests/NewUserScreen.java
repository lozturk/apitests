package com.merchantesolutions.tests;

import com.github.javafaker.Faker;
import com.merchantesolutions.Pages.DashBoardPage;
import com.merchantesolutions.Pages.UsersPage;
import com.merchantesolutions.Utilities.Driver;
import com.merchantesolutions.Utilities.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewUserScreen extends TestBase {

    public String userName, password;
    @Test
    public void acreateNewUserTest(){
        DashBoardPage dashBoardPage = new DashBoardPage();
        dashBoardPage.usersButton.click();
        dashBoardPage.newUserButton.click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        userName = firstName + lastName;
        password = firstName.toLowerCase()+lastName.toLowerCase();
        String email = firstName.toLowerCase()+lastName.toLowerCase()+"@"+faker.internet().domainName();
        UsersPage usersPage = new UsersPage();
        usersPage.enterUserName.sendKeys(userName);
        usersPage.enterUserPassword.sendKeys(password);
        usersPage.enterUserEmail.sendKeys(email);
        usersPage.createUserButton.click();
        dashBoardPage.usersButton.click();
        String userNameInUsersTable = usersPage.lastCreatedUserName.getText();
        String emailInUsersTable = usersPage.lastCreatedEmail.getText();
        // verify that new created user info matches with entered info
        Assert.assertEquals(userName,userNameInUsersTable);
        Assert.assertEquals(email,emailInUsersTable);
    }

    @Test
    public void bdeleteLastUserTest(){
        DashBoardPage dashBoardPage = new DashBoardPage();
        dashBoardPage.usersButton.click();
        UsersPage usersPage = new UsersPage();
        usersPage.lastDeleteLink.click();
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
        Assert.assertNotEquals(userName,usersPage.lastCreatedUserName.getText());
        Assert.assertNotEquals(password,usersPage.lastCreatedEmail.getText());
    }



}
