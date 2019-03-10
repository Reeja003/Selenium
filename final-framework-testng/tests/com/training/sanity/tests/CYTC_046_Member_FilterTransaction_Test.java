//*****Verify whether application displays Account information of member*****
package com.training.sanity.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
//import static org.testng.Assert.assertEquals;

//import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
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
import com.training.pom.Cyclos_Member_FilterTransactionPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_046_Member_FilterTransaction_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Member_FilterTransactionPOM cyclosfiltertransPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM = new CyclosLoginPOM(driver);
		cyclosfiltertransPOM = new Cyclos_Member_FilterTransactionPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser and login with member credentials
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

	@Test(priority = 1)
	public void filterTransactionDetails() throws ParseException {

		// ****Search transactions for a member
		cyclosfiltertransPOM.clickAccountTab();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		cyclosfiltertransPOM.clickAccountInformationLink();
		cyclosfiltertransPOM.clickAdvancedbtn();
		cyclosfiltertransPOM.sendMemberLogin("Reeja");
		cyclosfiltertransPOM.clickSearchSubmit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screenShot.captureScreenShot("CYTC_046_1.Transaction Details");
		// ****select Dates from and to calendar
		cyclosfiltertransPOM.clickFromdateCalendar();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		cyclosfiltertransPOM.clickTodateCalendar();
		cyclosfiltertransPOM.clickSearchSubmit();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		screenShot.captureScreenShot("CYTC_046_2.Transaction Details");

		/*
		 * Assert-->Compare search transaction details should be between the specified
		 * dates
		 */
		boolean expected = true;
		boolean actual = cyclosfiltertransPOM.resultBasedonDate();
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 2)
	public void filterTypebasedTransaction() {

		// ****Select payment type
		cyclosfiltertransPOM.selectPaymentType("Loan payments");
		cyclosfiltertransPOM.clickSearchSubmit();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		screenShot.captureScreenShot("CYTC_046_3.Transaction Details");

		/*
		 * Assert->Transaction details based on the specified date and payment type
		 * should display
		 */
		boolean result = cyclosfiltertransPOM.clickSearch();
		Assert.assertTrue(result);

	}
}
