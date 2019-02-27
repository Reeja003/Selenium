package com.training.sanity.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
//import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

//import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.training.pom.Cyclos_Member_AddContactandPaymentPOM;
import com.training.pom.Cyclos_Member_FilterMessagePOM;
import com.training.pom.Cyclos_Member_ScheduledPaymentsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_050_Member_AddContactandMakePayment_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Member_FilterMessagePOM cyclosfiltermessagePOM;
	Cyclos_Member_ScheduledPaymentsPOM cyclosscheduledpaymentPOM;
	private Cyclos_Member_AddContactandPaymentPOM cyclosmemberaddContact;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM = new CyclosLoginPOM(driver);
		cyclosfiltermessagePOM = new Cyclos_Member_FilterMessagePOM(driver);
		cyclosmemberaddContact = new Cyclos_Member_AddContactandPaymentPOM(driver);
		cyclosscheduledpaymentPOM = new Cyclos_Member_ScheduledPaymentsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser and login with member credential
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
	public void addAccount() throws AWTException {
		// ****Add another member as Contact
		cyclosfiltermessagePOM.clickPersonalTab();
		cyclosmemberaddContact.clickContactsLink();
		cyclosmemberaddContact.sendMemberLogin("NewReeja3");
		cyclosmemberaddContact.clickSubmitBtn();

		// Assert->contact inserted alert message
		Alert alertMsg = driver.switchTo().alert();
		String expected = "The contact was inserted";
		String actual = alertMsg.getText();
		Assert.assertEquals(actual, expected);
		alertMsg.accept();
		screenShot.captureScreenShot("CYTC_050_1.Added Contact");

	}

	@Test(priority = 2)
	public void makePayment() {

		// ****Make payment to added contact member
		cyclosmemberaddContact.clickAddedContact();
		cyclosmemberaddContact.clickMakepaymentsubmitBtn();
		cyclosmemberaddContact.sendPaymentAmount("5000");
		cyclosmemberaddContact.sendPaymentDescription("Welcome");
		cyclosmemberaddContact.clickSubmit();
		screenShot.captureScreenShot("CYTC_050_2.Payment details");
		// Assert->message from the page
		String expected = "You are about to perform the following payment:";
		String actual = cyclosscheduledpaymentPOM.actualMessage();
		Assert.assertEquals(actual, expected);

		// ****Submit payment
		cyclosscheduledpaymentPOM.clicktranSubmitBtn();
		screenShot.captureScreenShot("CYTC_050_3.Successful Payment");
		//Assert->Message from successful payment page
		String expectedMsg="The payment has been performed";
		String actualMsg=cyclosmemberaddContact.successMessage();
		Assert.assertEquals(actualMsg, expectedMsg);
		

	}

}
