package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
//import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import javax.swing.*;

public class HomePage extends TestBase {

    @FindBy(xpath ="//span[@class= 'user-display'] [text()='Danika Munasinghe']")
    @CacheLookup
    WebElement userNameLabel;

    @FindBy(id = "main-nav")
    @CacheLookup
    WebElement leftMenu;

    @FindBy(xpath = "//span[@class = 'item-text'][text() = 'Contacts']")
    @CacheLookup
    WebElement contactLink;

    @FindBy(xpath = "//a[@href='/contacts/new']")
    @CacheLookup
    WebElement createNewContact;



    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public String validateHomePageTitle(){
        return driver.getTitle();

    }
    public  boolean verifyCorrectUserName(){
       return userNameLabel.isDisplayed();
    }

    public ContactsPage clickContactsLink(){
 /*     Actions actions = new Actions(driver);
      actions.moveToElement(leftMenu).perform();*/
        TestUtil.hoverOverElement(driver, leftMenu);
        contactLink.click();
        //TestUtil.removeHoverEffect(driver, leftMenu);
        return  new ContactsPage();
    }
    public void clickOnNewContact(){
    createNewContact.click();
    }
}
