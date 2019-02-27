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
import com.training.pom.Cyclos_Member_AccountInformationPOM;
import com.training.pom.Cyclos_Member_FilterTransactionPOM;
import com.training.pom.Cyclos_Member_ScheduledPaymentsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_047_Member_ViewScheduledpayment_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Member_ScheduledPaymentsPOM cyclosscheduledpaymentPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String toMember;
	private String amount;
	private String description;
	private String scheduleDate;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM = new CyclosLoginPOM(driver);
		cyclosscheduledpaymentPOM = new Cyclos_Member_ScheduledPaymentsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
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
	public void scheduledpaymentsDetails() throws AWTException {

		// ****Schedule payment for future date
		toMember = "selenium";
		amount = "50,00";
		description = "welcome";
		cyclosscheduledpaymentPOM.clickAccountTab();
		cyclosscheduledpaymentPOM.clickMemberPaymentLink();
		cyclosscheduledpaymentPOM.sendLogin(toMember);
		cyclosscheduledpaymentPOM.sendAmount(amount);
		cyclosscheduledpaymentPOM.selectSchedule(1);
		scheduleDate = cyclosscheduledpaymentPOM.clickScheduleDate();
		cyclosscheduledpaymentPOM.sendDescription(description);
		screenShot.captureScreenShot("CYTC_047_1.Scedule detrails");
		cyclosscheduledpaymentPOM.clickSubmitBtn();
		screenShot.captureScreenShot("CYTC_047_2.Scedule detrails");

		// Assert-> Message in Transaction confirmation page should display
		String expected = "You are about to schedule following payment:";
		String actual = cyclosscheduledpaymentPOM.actualMessage();
		Assert.assertEquals(actual, expected);
		cyclosscheduledpaymentPOM.clicktranSubmitBtn();

		screenShot.captureScreenShot("CYTC_047_3.Transaction Confirmation");
		// Assert-> Scheduled Payment Details page should display
		String pageExpected = "Scheduled payment details";
		String pageActual = cyclosscheduledpaymentPOM.actualPage();
		Assert.assertEquals(pageActual, pageExpected);

	}

	@Test(priority = 2)
	public void scheduledpayments() throws InterruptedException

	{
		ArrayList<String> list = new ArrayList<String>();
		cyclosscheduledpaymentPOM.clickScheduledPaymentsLink();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screenShot.captureScreenShot("CYTC_047_4.Schedule detrails");

		/*
		 * Assert-> Array list which created by the value used to schedule
		 * payment and list from the payment scheduled view should match
		 */
		list = cyclosscheduledpaymentPOM.schedulePaymentDetails();
		boolean result = list.get(0).equals(scheduleDate) && list.get(1).equals(toMember)
				&& list.get(2).contains(amount);
		Assert.assertTrue(result);

	}

}
