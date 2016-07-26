package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
/*import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;*/
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.*;
import pageobject.*;

public class testlogin {

	public class UserModel
	{
		public String Username;
		public String Password;
	}

	@BeforeTest
	@Parameters("browser")

	public void setUp(String browser) throws Exception {

		OpenBrowser.multi_browser(browser);	

		common.driver.manage().timeouts().implicitlyWait(common.TIMEOUTS,TimeUnit.SECONDS);
	}

	@Test(priority = 6, enabled = true)
	public void Login_Admin_Successful() throws Exception {
		

			XSSFSheet ExcelWAddNew = ExcelCommon_POI.setExcelFile("Login_Selenium_Digitest.xlsx", "Login");
			int firstRow =  ExcelWAddNew.getFirstRowNum();
			int lastRow =  ExcelWAddNew.getLastRowNum();

			UserModel user;
			List<UserModel> listA = new ArrayList<UserModel>();
			for (int indexR = firstRow; indexR <= lastRow; indexR++) {
				user = new UserModel();

				user.Username = ExcelCommon_POI.getCellData(indexR, 1, ExcelWAddNew);
				user.Password = ExcelCommon_POI.getCellData(indexR, 2, ExcelWAddNew);
				listA.add(user);
			}

			ExcelWAddNew = null;

			for (int index = 1; index < listA.size(); index++) {
				user = listA.get(index);
				common.driver.get(common.URL);	
				common.driver.findElement(loginobject.linkLogin).click();
				common.driver.findElement(loginobject.txtUsername).sendKeys(user.Username);
				common.driver.findElement(loginobject.txtPassword).sendKeys(user.Password);
				common.driver.findElement(loginobject.btnLogin).click();

				try {	
				//logout
				WebElement element = common.driver.findElement(By.xpath(".//*[@id='wp-admin-bar-my-account']/a/img"));
				Actions action = new Actions(common.driver);
				action.moveToElement(element).perform();
				Thread.sleep(3000);
				WebElement subElement = common.driver.findElement(By.linkText("Log Out"));
				action.moveToElement(subElement);
				action.click();
				action.perform();
				//verify

				
				String ActualMessage = common.driver.findElement(loginobject.actualMess).getText();
				String ExpectMessage = "You are now logged out.";
				Assert.assertEquals(ActualMessage, ExpectMessage);
				ExcelCommon_POI.writeDataToExcel(index, 3,"Login_Selenium_Digitest.xlsx" , "Login", "Passed");
				System.out.println("Pass");
		}
		catch (Exception e) {
			ExcelCommon_POI.writeDataToExcel(index, 3, "Login_Selenium_Digitest.xlsx", "Login", "Failed");
			System.out.println("Fail");

		}
}


		/*// Get data from Excel
		XSSFSheet ExcelWSheet = ExcelCommon_POI.setExcelFile("Login_Selenium_Digitest.xlsx", "Login");

		int firstRow =  ExcelWSheet.getFirstRowNum();
		int lastRow =  ExcelWSheet.getLastRowNum();

		UserModel user;
		List<UserModel> listA = new ArrayList<UserModel>();
		for (int indexR = firstRow; indexR <= lastRow; indexR++) {
			user = new UserModel();

			String Username = ExcelCommon_POI.getCellData(1, 1, ExcelWSheet);
			String Password = ExcelCommon_POI.getCellData(1, 2, ExcelWSheet);
			common.driver.get(common.URL);	
			common.driver.findElement(loginobject.linkLogin).click();
			common.driver.findElement(loginobject.txtUsername).sendKeys(Username);
			common.driver.findElement(loginobject.txtPassword).sendKeys(Password);
			common.driver.findElement(loginobject.btnLogin).click();

			WebElement element = common.driver.findElement(By.xpath(".//*[@id='wp-admin-bar-my-account']/a/img"));
			Actions action = new Actions(common.driver);
			action.moveToElement(element).perform();
			Thread.sleep(3000);
			WebElement subElement = common.driver.findElement(By.linkText("Log Out"));
			action.moveToElement(subElement);
			action.click();
			action.perform();*/
		//verify
		/*try {
			String ActualMessage = common.driver.findElement(registerobject.actualMess).getText();
			String ExpectMessage = "You are now logged out.";
			assertEquals(ActualMessage, ExpectMessage);
			ExcelCommon_POI.writeDataToExcel(1, 3,"Login_Selenium_Digitest.xlsx" , "Login", "Passed");
			System.out.println("Pass");
		}
		catch (Exception e) {
			ExcelCommon_POI.writeDataToExcel(1, 3, "Login_Selenium_Digitest.xlsx", "Login", "Failed");
			System.out.println("Fail");
		 */
		//		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		common.driver.quit();
		//Duongiu dang test nhe
	}

}
