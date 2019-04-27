package com.merchantesolutions.tests;

import com.github.javafaker.Faker;
import com.merchantesolutions.pages.DashBoardPage;
import com.merchantesolutions.pages.UsersPage;
import com.merchantesolutions.utilities.ConfigurationReader;
import com.merchantesolutions.utilities.Driver;
import com.merchantesolutions.utilities.ObjectRepoReader;
import com.merchantesolutions.utilities.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class NewUserScreen extends TestBase {

    public String userName, password;

    @Test
    public void createNewUserWithObjectRepoTest(){
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
        // Following 4 lines created as an example of ObjectRepository.
        // The major advantage of using object repository is the segregation of objects from test cases.
        // If the locator value of one Webelement changes, only the object repository needs to be changed rather than making changes
        // in all test cases in which the locator has been used.
        // Maintaining an object repository increases the modularity of framework implementation.
        Driver.getDriver().findElement(By.id(ObjectRepoReader.getObject("NewUserPageUserNameBox"))).sendKeys(userName);
        Driver.getDriver().findElement(By.id(ObjectRepoReader.getObject("NewUserPageUserPasswordBox"))).sendKeys(password);
        Driver.getDriver().findElement(By.id(ObjectRepoReader.getObject("NewUserPageEmailBox"))).sendKeys(email);
        Driver.getDriver().findElement(By.xpath(ObjectRepoReader.getObject("NewUserPageCreateUserButton"))).click();
//        usersPage.enterUserName.sendKeys(userName);
//        usersPage.enterUserPassword.sendKeys(password);
//        usersPage.enterUserEmail.sendKeys(email);
//        usersPage.createUserButton.click();
        dashBoardPage.usersButton.click();
        String userNameInUsersTable = usersPage.lastCreatedUserName.getText();
        String emailInUsersTable = usersPage.lastCreatedEmail.getText();
        // verify that new created user info matches with entered info
        Assert.assertEquals(userName,userNameInUsersTable);
        Assert.assertEquals(email,emailInUsersTable);
    }


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
