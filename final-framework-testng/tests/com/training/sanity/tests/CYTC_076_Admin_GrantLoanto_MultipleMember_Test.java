//***********Verify whether application allows admin to grant loan to multiple member**********

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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

import com.training.dataproviders.CyclosDataProviders;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Admin_MemberGrantLoanPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_076_Admin_GrantLoanto_MultipleMember_Test {

	private WebDriver driver;
	private String baseUrl;
	private CyclosLoginPOM cyclosLoginPOM;
	private Cyclos_Admin_MemberGrantLoanPOM cyclosmembergrantloanPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosLoginPOM = new CyclosLoginPOM(driver);
		cyclosmembergrantloanPOM = new Cyclos_Admin_MemberGrantLoanPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser and login with admin credentials
		driver.get(baseUrl);
		cyclosLoginPOM.sendUserName("admin");
		cyclosLoginPOM.sendPassword("123456");
		cyclosLoginPOM.clickLoginBtn();

	}

	@AfterClass
	public void tearDown()  {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
	}

	
	//****Grand Loan for multiple members
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = CyclosDataProviders.class)
	public void grandLoanMultipleDataexcelTest(String MemberLogin,String Amount,String Description)throws IOException {

		// **** Enter member login
		
		cyclosLoginPOM.sendMemberLogin(MemberLogin);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//****Grand Loan for member
		cyclosmembergrantloanPOM.clickGrantLoansubmitBtn();
		cyclosmembergrantloanPOM.sendLoanAmount(Amount);
		cyclosmembergrantloanPOM.sendLoanDescription(Description);
		cyclosmembergrantloanPOM.clickLoanSubmit();
		cyclosmembergrantloanPOM.clickLoanConfirmSubmit();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Assert->loan successful alert message
		Alert loanAlert = driver.switchTo().alert();
		String expected = "The loan was successfully granted";
		String actual = loanAlert.getText();
		Assert.assertEquals(actual, expected);
		loanAlert.accept();
		screenShot.captureScreenShot("CYTC_076_GrantsLoan_Multiplemember_"+MemberLogin);
		cyclosmembergrantloanPOM.clickHomeBtn();

	}
}
