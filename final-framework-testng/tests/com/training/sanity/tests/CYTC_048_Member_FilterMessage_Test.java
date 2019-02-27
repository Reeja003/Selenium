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
import com.training.pom.Cyclos_Member_FilterMessagePOM;
import com.training.pom.Cyclos_Member_FilterTransactionPOM;
import com.training.pom.Cyclos_Member_ScheduledPaymentsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_048_Member_FilterMessage_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Member_FilterMessagePOM cyclosfiltermessagePOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String type;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM = new CyclosLoginPOM(driver);
		cyclosfiltermessagePOM = new Cyclos_Member_FilterMessagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);

		// *open the browser and login with member*//
		driver.get(baseUrl);
		cyclosloginPOM.sendUserName("Reeja");
		cyclosloginPOM.sendPassword("reeja123");
		cyclosloginPOM.clickLoginBtn();

	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test(priority = 1)
	public void filterToAdminMessage() {

		// *Message List of a member*//
		cyclosfiltermessagePOM.clickPersonalTab();
		cyclosfiltermessagePOM.clickMessagesLink();
		cyclosfiltermessagePOM.clickAdvanceBtn();

		// *Filer the message list based on Sent Items to Admin*//
		type = "Administration";
		cyclosfiltermessagePOM.selectMessageBox("Sent items");
		cyclosfiltermessagePOM.selectType(type);
		cyclosfiltermessagePOM.clickSubmitBtn();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screenShot.captureScreenShot("CYTC_048_1.Message details");

		// Assert ->Sent message Header should be "To" and contain Administration

		String expectedHeader = "To";
		String actualHeader = cyclosfiltermessagePOM.searchMessageBoxType();
		Assert.assertEquals(actualHeader, expectedHeader);
		boolean expected = true;
		boolean actual = cyclosfiltermessagePOM.seachMessagesforType(type);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2)
	public void filterFromAdminMessage() {
		// *Filer the message from Admin*//
		type = "Administration";
		cyclosfiltermessagePOM.selectMessageBox("Inbox");
		cyclosfiltermessagePOM.selectType(type);
		cyclosfiltermessagePOM.clickSubmitBtn();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screenShot.captureScreenShot("CYTC_048_2.Message details");

		/*
		 * Assert ->Inbox message Header should be "From" and contain only
		 * Administration message
		 */
		String expectedHeader = "From";
		String actualHeader = cyclosfiltermessagePOM.searchMessageBoxType();
		Assert.assertEquals(actualHeader, expectedHeader);
		boolean expected = true;
		boolean actual = cyclosfiltermessagePOM.seachMessagesforType(type);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 3)
	public void filterAllinboxMessage() {
		// *Filter all the inbox message*//
		type = "All";
		cyclosfiltermessagePOM.selectMessageBox("Inbox");
		cyclosfiltermessagePOM.selectType(type);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		cyclosfiltermessagePOM.clickSubmitBtn();
		screenShot.captureScreenShot("CYTC_048_3.Message details");

		// Assert ->Inbox message Header should be "From" and contain all messages
		String expectedHeader = "From";
		String actualHeader = cyclosfiltermessagePOM.searchMessageBoxType();
		Assert.assertEquals(actualHeader, expectedHeader);
		boolean expected = true;
		boolean actual = cyclosfiltermessagePOM.seachAllTypeMessages(type);
		Assert.assertEquals(actual, expected);

	}
}
