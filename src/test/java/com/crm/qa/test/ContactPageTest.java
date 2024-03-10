package com.crm.qa.test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    String sheetName = "contacts";
    public ContactPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
        contactsPage = homePage.clickContactsLink();
     }
     @Test(priority = 1)
     public void verifyContactLabel(){
        Assert.assertTrue(contactsPage.verifyContactsLabel(),
                "Contacts label is missing on the page");
         driver.quit();

     }
     @Test(priority = 2)
     public void selectContactsTest(){

        contactsPage.selectContactsByName("test2 test2");
    }
    @DataProvider
    public Object[][] getContactTestData(){
    Object[][] data = TestUtil.getTestData(sheetName);
    return data;
    }

    @Test(priority = 3, dataProvider ="getContactTestData" )
    public void validateCreateNewContact(String firstname, String lastname, String category){
        homePage.clickOnNewContact();
        //contactsPage.createNe
        // wContact("test fName",
          //      "test lName", "Lead");
         contactsPage.createNewContact(firstname, lastname, category);
        String expectedText = firstname + " " + lastname;
        String[] texts = contactsPage.getDisplayText();
        // Get labelText
        String labelText = texts[0]; // Assuming label text is at index 0

        // Assertion for labelText containing "Access"
        Assert.assertTrue(labelText.contains("Access"), "Label text does not contain 'Access'");


        // Get displayed text

        String displayedText = texts[1]; // Assuming displayed text is at index 1

        // Assertion for expectedText and displayedText
        Assert.assertEquals(displayedText, expectedText, "Displayed text doesn't match expected text");
        System.out.println("Displayed text: " + displayedText);


    }





    @AfterMethod
    public void tearDown(){

        driver.quit();
    }
}

