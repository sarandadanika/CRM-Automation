package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
//import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class ContactsPage extends TestBase {
    @FindBy(xpath = "//span[@class ='selectable '][text() ='Contacts']")
    WebElement contactLabel;
    @FindBy(id = "main-nav")
    WebElement leftMenu;
    @FindBy(xpath = "//button[@class='ui basic icon button']/i[@class='pin icon']")
    WebElement pinIcon;
    @FindBy(xpath = "//a[text()='\" + name + \"']/ancestor::tr//input[@type='checkbox']")
    WebElement checkBox;
    @FindBy(name = "first_name")
    WebElement firstName;
    @FindBy(name = "last_name")
    WebElement lastName;
    @FindBy(xpath = "//i[@class='save icon']")
    WebElement saveButton;

    public ContactsPage(){

        PageFactory.initElements(driver, this);
    }

    public boolean verifyContactsLabel(){

        return contactLabel.isDisplayed();
    }
    public void selectContactsByName(String name){
        TestUtil.removeHoverEffect(driver, leftMenu);
        pinIcon.click();

        // Constructing dynamic XPath for the checkbox based on the contact name
//        String checkboxXPath = "//a[text()='" + name + "']/ancestor::tr//input[@type='checkbox']";
//        //WebElement checkbox = driver.findElement(By.xpath("//a[text()='test2 test2']/ancestor::tr//input[@type='checkbox']"));
//        // Wait for the checkbox to be clickable
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkboxXPath)));
//        TestUtil.hoverOverElement(driver, checkbox);
//        checkbox.click();


        ////a[text()='"+name+"']" +
        //                "/ancestor::tr//input[@type='checkbox']
    }

    public void createNewContact(String ftName, String ltName, String category){

        firstName.sendKeys(ftName);
        lastName.sendKeys(ltName);


        // Click to open the dropdown
        WebElement dropdownToggle = driver.findElement(By.xpath("//div[@name='category']"));
        dropdownToggle.click();

        // Find the desired option by its text and click on it
        // Wait for the dropdown options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='visible menu transition']//div[@role='option']/span[text()='" + category + "']")));
        option.click();
        saveButton.click();
    }
    public String[] getDisplayText(){
        // Get the text from the specified place using XPath
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
        //Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui label' and text() = 'Access']")));
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dashboard-toolbar']//span[contains(@class, 'selectable')]")));
        String labelText = labelElement.getText();
        String nameText = nameElement.getText();
        return new String[]{labelText, nameText};

    }


}
