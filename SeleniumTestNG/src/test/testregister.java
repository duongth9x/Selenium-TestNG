package test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.common;
import common.ExcelCommon_POI;
import common.OpenBrowser;
import pageobject.loginobject;
import pageobject.registerobject;

public class testregister {
	
	public class UserModel
	{
		public String username;
		public String Email;
		public String First;
		public String Last;
		public String Web;
		public String Role;
	}
	
	
	
	@BeforeTest
	@Parameters("browser")
	public void setUp(String browser) throws Exception {

		OpenBrowser.multi_browser(browser);	

		common.driver.manage().timeouts().implicitlyWait(common.TIMEOUTS,TimeUnit.SECONDS);
	}


	@Test(priority = 6, enabled = true)
	public void addNewUser() throws Exception {

		//login
		XSSFSheet ExcelWSheetLogin = ExcelCommon_POI.setExcelFile("Login_Selenium_Digitest.xlsx", "Login");

		String Username = ExcelCommon_POI.getCellData(1, 1, ExcelWSheetLogin);
		String Password = ExcelCommon_POI.getCellData(1, 2, ExcelWSheetLogin);
		common.driver.get(common.URL);	
		common.driver.findElement(loginobject.linkLogin).click();
		common.driver.findElement(loginobject.txtUsername).sendKeys(Username);
		common.driver.findElement(loginobject.txtPassword).sendKeys(Password);
		common.driver.findElement(loginobject.btnLogin).click();

		//ad new user
		// Get data from Excel
		XSSFSheet ExcelWSheet = ExcelCommon_POI.setExcelFile("Login_Selenium_Digitest.xlsx", "Register");
		
		int firstRow =  ExcelWSheet.getFirstRowNum();
		int lastRow =  ExcelWSheet.getLastRowNum();
				
		UserModel user;
		List<UserModel> listA = new ArrayList<UserModel>();
		for (int indexR = firstRow; indexR <= lastRow; indexR++) {
			user = new UserModel();
			
			user.username = ExcelCommon_POI.getCellData(indexR, 1, ExcelWSheet);
			user.Email = ExcelCommon_POI.getCellData(indexR, 2, ExcelWSheet);
			user.First = ExcelCommon_POI.getCellData(indexR, 3, ExcelWSheet);
			user.Last = ExcelCommon_POI.getCellData(indexR, 4, ExcelWSheet);
			user.Web = ExcelCommon_POI.getCellData(indexR, 5, ExcelWSheet);
			user.Role = ExcelCommon_POI.getCellData(indexR, 6, ExcelWSheet);
			
			listA.add(user);
		}
		
		ExcelWSheet = null;
		
		
		for (int index = 1; index < listA.size(); index++) {
			user = listA.get(index);
	
		common.driver.findElement(registerobject.linkMenuadduser).click();
		common.driver.findElement(registerobject.linkAddnewuser).click();

		common.driver.findElement(registerobject.txtUserlogin).sendKeys(user.username);
		
		common.driver.findElement(registerobject.txtEmail).sendKeys(user.Email);
		common.driver.findElement(registerobject.txtFirstname).sendKeys(user.First);
		common.driver.findElement(registerobject.txtLastname).sendKeys(user.Last);
		common.driver.findElement(registerobject.txtWebsite).sendKeys(user.Web);
		common.driver.findElement(registerobject.btnShowpasswd).click();
		common.driver.findElement(registerobject.btnRole).sendKeys(user.Role);
		common.driver.findElement(registerobject.btnCreateNewUser).click();

		Thread.sleep(2000);
		/*//Verify
		try {
			String ActualMessage = common.driver.findElement(registerobject.actualMess).getText();
			String ExpectMessage = "Log out";
			assertEquals(ActualMessage, ExpectMessage);
			ExcelCommon_POI.writeDataToExcel(1, 7,"Login_Selenium_Digitest.xlsx" , "Register", "Passed");
			System.out.println("Pass");
		}
		catch (Exception e) {
			ExcelCommon_POI.writeDataToExcel(1, 7, "Login_Selenium_Digitest.xlsx", "Register", "Failed");
			System.out.println("Fail");
		} */
	}
	}

/*	private void assertEquals(String actualMessage, String expectMessage) {
		// TODO Auto-generated method stub

	}*/
	
	
	@AfterTest
	public void tearDown() throws Exception {
		//common.driver.quit();
	}
}
