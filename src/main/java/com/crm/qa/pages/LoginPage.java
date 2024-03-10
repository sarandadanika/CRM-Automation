package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import jdk.jfr.Name;
//import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  extends TestBase {

    //Object repo (Page factory)
    @FindBy(name="email")
    WebElement email;
    @FindBy(name="password")
    WebElement password;

   @FindBy(xpath = "//div[@class ='ui fluid large blue submit button' and text()='Login']")
    WebElement loginBtnLoginPage;


   public LoginPage(){

       PageFactory.initElements(driver, this);
   }
   public String validateLoginPageTitle(){
       return driver.getTitle();
   }
   //public homepage means it returns to Home page
   public HomePage login(String em, String pw){
      email.sendKeys(em);
      password.sendKeys(pw);
       loginBtnLoginPage.click();
      return new HomePage();
   }
}
