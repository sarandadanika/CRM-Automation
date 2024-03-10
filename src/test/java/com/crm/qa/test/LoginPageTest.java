package com.crm.qa.test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends LoginPage {
    LoginPage loginPage;
    HomePage homePage;
    public LoginPageTest(){
       //call TestBase class
        super();
    }

    @BeforeMethod
    public void setUp(){
          initialization();
         loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginPageTitleTest() {
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title, "Cogmento CRM","Login page title not matched");

    }
    @Test(priority = 2)
    public void loginTest(){
       homePage = loginPage.login(prop.getProperty("email"),prop.getProperty("password"));

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
