package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Admin_MemberAccoutinformationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_016_Admin_MemberAccounts_Accountinformation_Test {

	private WebDriver driver;
	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Admin_MemberAccoutinformationPOM cyclosmemberaccountinfoPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM= new CyclosLoginPOM(driver);
		cyclosmemberaccountinfoPOM = new Cyclos_Admin_MemberAccoutinformationPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
		cyclosloginPOM.sendUserName("admin");
		cyclosloginPOM.sendPassword("123456"); 
		cyclosloginPOM.clickLoginBtn();

	}

	@AfterClass 
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}
	

	@Test
	public void memberAccountInfoTest() {
		cyclosloginPOM.sendMemberLogin("manzoor");
		cyclosmemberaccountinfoPOM.clickAccountinfoSubmitBtn();
		screenShot.captureScreenShot("CYTC_016_1_Transaction Details");
		cyclosmemberaccountinfoPOM.selectPaymentType("Commission payments");
		cyclosmemberaccountinfoPOM.clickPaymentSubmitBtn();
		screenShot.captureScreenShot("CYTC_016_2.Transaction Details");
		String expected="Search transactions of manzoor mehadi on Member account";
		String actual=cyclosmemberaccountinfoPOM.searchTransactionDetails();
		
		Assert.assertEquals(actual, expected);
		
	
	}
	
}
