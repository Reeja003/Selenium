//*********Verify whether application displays Account information of member*****
package com.training.sanity.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
//import static org.testng.Assert.assertEquals;

//import static org.testng.Assert.assertEquals;

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
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Member_AccountInformationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_020_Member_AccountInformation_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Member_AccountInformationPOM cyclosmemberaccountPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM = new CyclosLoginPOM(driver);
		cyclosmemberaccountPOM = new Cyclos_Member_AccountInformationPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser and login with memebr credential
		driver.get(baseUrl);
		cyclosloginPOM.sendUserName("Reeja");
		cyclosloginPOM.sendPassword("reeja123");
		cyclosloginPOM.clickLoginBtn();

	}

	@AfterClass
	public void tearDown()  {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
	}

	@Test
	public void memberAccountInformationTest() {

		/*
		 * Click account information for a member and view transaction details then
		 * click back button
		 */
		cyclosmemberaccountPOM.clickAccountLink();
		cyclosmemberaccountPOM.clickAccountInformationLink();
		screenShot.captureScreenShot("CYTC_020_1.Memberaccountinfo");
		cyclosmemberaccountPOM.clickViewIcon();
		screenShot.captureScreenShot("CYTC_020_2.Transactiondetails");
		cyclosmemberaccountPOM.clickBackbtn();
		screenShot.captureScreenShot("CYTC_020_3.BackbuttontosearchTranpage");
		/*
		 * Assert->Search transaction on a member page should display after clicking
		 * back button
		 */
		String expected = "Search transactions on Member account";
		String actual = cyclosmemberaccountPOM.transactionDetails();
		Assert.assertEquals(actual, expected);

	}
}
