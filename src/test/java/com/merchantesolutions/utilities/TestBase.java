package com.merchantesolutions.utilities;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    protected WebDriver driver;

    @Before
    public void setupMethod() {
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
        Dimension d = new Dimension(1480,720);
        driver.manage().window().setSize(d);
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @After
    public void tearDownMethod()  {
        Driver.closeDriver();
    }

}
