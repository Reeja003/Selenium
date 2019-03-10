//*****Verify whether application allows admin to grant loan to member, member can view loan & repay the loan***
package com.training.sanity.tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Admin_MemberGrantLoanPOM;
import com.training.pom.Cyclos_Member_FilterTransactionPOM;
import com.training.pom.Cyclos_Member_ViewandRepayLoanPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_078_E2E_Admin_GrantLoan_Member_Repay_Test {

	private WebDriver driver;
	private String baseUrl;
	private CyclosLoginPOM cyclosLoginPOM;
	private Cyclos_Admin_MemberGrantLoanPOM cyclosmembergrantloanPOM;
	private Cyclos_Member_ViewandRepayLoanPOM cyclosloanrepayPOM;
	private Cyclos_Member_FilterTransactionPOM cyclosmenu;
	private static Properties properties;
	private ScreenShot screenShot;
	private String member;
	private String repayAmount;
	private String loanAmount;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosLoginPOM = new CyclosLoginPOM(driver);
		cyclosmembergrantloanPOM = new Cyclos_Admin_MemberGrantLoanPOM(driver);
		cyclosloanrepayPOM = new Cyclos_Member_ViewandRepayLoanPOM(driver);
		cyclosmenu = new Cyclos_Member_FilterTransactionPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser and login with admin credentials
		driver.get(baseUrl);

	}

	@AfterClass
	public void tearDown()  {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
	}

//***Test the Admin should grant loan to a registered member	
@Test(priority = 1)
	public void memberGrantLoanTest() throws Exception {

		loanAmount = "10000";
		// ****login as admin
		cyclosLoginPOM.sendUserName("admin");
		cyclosLoginPOM.sendPassword("123456");
		cyclosLoginPOM.clickLoginBtn();
		// **** Enter member login

		cyclosLoginPOM.sendMemberLogin("manzoor");
		// ****Grand Loan for member
		cyclosmembergrantloanPOM.clickGrantLoansubmitBtn();
		cyclosmembergrantloanPOM.sendLoanAmount(loanAmount);
		cyclosmembergrantloanPOM.sendLoanDescription("car loan");
		cyclosmembergrantloanPOM.clickLoanSubmit();
		screenShot.captureScreenShot("CYTC_078_1.Loandetails");
		cyclosmembergrantloanPOM.clickLoanConfirmSubmit();
		
		// Assert->loan successful alert message
		Alert loanAlert = driver.switchTo().alert();
		String expected = "The loan was successfully granted";
		String actual = loanAlert.getText();
		Assert.assertEquals(actual, expected);
		loanAlert.accept();
		screenShot.captureScreenShot("CYTC_078_2.Memberdetailspage");
		cyclosmembergrantloanPOM.clickLogout();
		Alert logout = driver.switchTo().alert();
		logout.accept();

	}

// ****Test Member should be able to view the loan

	@Test(priority = 2)

	public void memberViewLoansTest() {

		repayAmount = "1000";
		cyclosLoginPOM.sendUserName("manzoor");
		cyclosLoginPOM.sendPassword("manzoor");
		cyclosLoginPOM.clickLoginBtn();

		// ****Enter member Login and view loan for the member

		cyclosmenu.clickAccountTab();
		cyclosloanrepayPOM.clickLoans();
		cyclosloanrepayPOM.clickLastpage();
		cyclosloanrepayPOM.clickView();
		
	     //***send rapay amount for the granted loan 
		cyclosloanrepayPOM.sendamount(repayAmount);
		
		//Assert--> Loan should be repayed successfully
		cyclosloanrepayPOM.clickRepayBtn();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Alert successAlert = driver.switchTo().alert();
		String actual1=successAlert.getText();
		Assert.assertTrue(actual1.contains("Are you sure to repay 10,00 units?\n" + 
				"\n" + 
				"Please, click OK to proceed"));
		successAlert.accept();
		String actual2 = successAlert.getText();
		Assert.assertTrue(actual2.contains("The repayment was succesfully processed"));
		successAlert.accept();

		//Assert-->repayment amount should be deducted from the loan amount
		Integer value = Integer.parseInt(loanAmount) - Integer.parseInt(repayAmount);
		 DecimalFormat formatter = new DecimalFormat("##,##");
		String expectedBalance =formatter.format(value);
		String actualBalance=cyclosloanrepayPOM.resultAmount();
		Assert.assertEquals(actualBalance, expectedBalance);
      


	}

}
