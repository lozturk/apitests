package com.merchantesolutions.tests;

import com.merchantesolutions.pages.DashBoardPage;
import com.merchantesolutions.pages.UsersPage;
import com.merchantesolutions.utilities.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UserFilterTest extends TestBase {

    @Before
    public void landingUsersPage(){
        DashBoardPage dashBoardPage = new DashBoardPage();
        dashBoardPage.usersButton.click();
    }

    @Test
    public void filtersUserNameTest(){
        // verify that filters username function filtering the entered values
        UsersPage usersPage = new UsersPage();
        usersPage.userNameDropDownMenu.click();
        String username = usersPage.lastCreatedUserName.getText();
        Select containsList = new Select(usersPage.userNameDropDownMenu);
        containsList.selectByVisibleText("Equals");
        usersPage.userNameInputBox.sendKeys(username);
        usersPage.filterButtonInUsersPage.click();
        Assert.assertEquals(username,usersPage.lastCreatedUserName.getText());
    }

    @Test
    public void filtersEmailTest(){
        // verify that filters email function filtering the entered values
        UsersPage usersPage = new UsersPage();
        usersPage.emailDropDownMenu.click();
        String email = usersPage.lastCreatedEmail.getText();
        Select emailList = new Select(usersPage.emailDropDownMenu);
        emailList.selectByIndex(0);
        usersPage.emailInputBox.sendKeys(email);
        usersPage.filterButtonInUsersPage.click();
        Assert.assertEquals(email,usersPage.lastCreatedEmail.getText());
    }

    @Test
    public void filtersBetweenTwoTime(){
        // verify that calendar function filtering the users
        UsersPage usersPage = new UsersPage();
        String username = usersPage.lastCreatedUserName.getText();
        usersPage.calendarFromBox.click();
        usersPage.setADayOfCurrentMonth(1).click();
        usersPage.calendarToBox.click();
        usersPage.setADayOfCurrentMonth(18).click();
        usersPage.filterButtonInUsersPage.click();
        List<String> userNamesCreatedBetweenDates = usersPage.getAllNames();
        System.out.println(userNamesCreatedBetweenDates);
        Assert.assertTrue(userNamesCreatedBetweenDates.contains(username));

    }


}
