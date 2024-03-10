package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage  extends TestBase {

    //object repo (Page factory)
    @FindBy(xpath = "//a[@href='https://ui.freecrm.com' and text()='Log In']")
    WebElement loginBtnLandingPage;
    @FindBy(xpath = "//a[@href='https://register.freecrm.com/register/' and text()='Sign Up']")
    WebElement signUp;

    public LandingPage(){
        PageFactory.initElements(driver, this);
    }

    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public LandingPage clckLogin(){
        loginBtnLandingPage.click();
        return new LandingPage();
    }
}
