package com.crm.qa.util;

import com.crm.qa.base.TestBase;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase  {
    public static long PAGE_LOAD_TIMEOUT = 60;
    public static long IMPLICIT_WAIT = 60;

    static Workbook book;
    static Sheet sheet;



    public static void hoverOverElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void removeHoverEffect(WebDriver driver, WebElement element) {
        // Move the mouse to a coordinate outside the viewport to remove hover effect
        Actions actions = new Actions(driver);
//        actions.moveToElement(element).perform();
        actions.moveToElement(element,  100,  40);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public void  switchToFrame(){
       driver.switchTo().frame("frame name here");
    }

    public static String TESTDATA_SHEET_PATH = "C:\\Users\\USER\\IdeaProjects\\CRMTestAutomation\\src\\main\\java\\com\\crm\\qa\\testdata\\CRMTestData.xlsx";


    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        int lastCellNum = sheet.getRow(0).getLastCellNum();

        System.out.println("Last Row Number: " + lastRowNum);
        System.out.println("Last Cell Number: " + lastCellNum);

        Object[][] data = new Object[lastRowNum][lastCellNum];

        for (int i = 0; i < lastRowNum; i++) {
            for (int k = 0; k < lastCellNum; k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        return data;
    }
    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }

}
