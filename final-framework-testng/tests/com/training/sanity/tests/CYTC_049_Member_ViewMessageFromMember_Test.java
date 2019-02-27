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
import com.training.pom.Cyclos_Member_FilterMessagePOM;
import com.training.pom.Cyclos_Member_FilterTransactionPOM;
import com.training.pom.Cyclos_Member_ScheduledPaymentsPOM;
import com.training.pom.Cyclos_Member_ViewMemberMessagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_049_Member_ViewMessageFromMember_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
	private Cyclos_Member_FilterMessagePOM cyclosfiltermessagePOM;
	private Cyclos_Member_ViewMemberMessagePOM cyclosmemberMessage;
	private static Properties properties;
	private ScreenShot screenShot;
	private String fromMember;
	private String subject;
	private String body;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosloginPOM = new CyclosLoginPOM(driver);
		cyclosfiltermessagePOM = new Cyclos_Member_FilterMessagePOM(driver);
		cyclosmemberMessage = new Cyclos_Member_ViewMemberMessagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);

	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test(priority = 1)
	public void sendMessageToMember() throws AWTException {

		fromMember = "Reeja";
		subject = "hello";
		body = "Welcome";
		// ***Login with member Reeja ***
		cyclosloginPOM.sendUserName(fromMember);
		cyclosloginPOM.sendPassword("reeja123");
		cyclosloginPOM.clickLoginBtn();

		// ***Send message to the member name NewReeja3***
		cyclosfiltermessagePOM.clickPersonalTab();
		cyclosfiltermessagePOM.clickMessagesLink();
		cyclosmemberMessage.clickNewSubmitBtn();
		cyclosmemberMessage.selectSendTo("Member");
		cyclosmemberMessage.sendMemberLogin("NewReeja3");
		cyclosmemberMessage.sendSubjectTXT(subject);
		cyclosmemberMessage.sendBodyTXT(body);
		screenShot.captureScreenShot("CYTC_049_1.Send message");
		cyclosmemberMessage.clickSubmitBtn();

		// Assert->Alert message for sent message
		Alert alertMsg = driver.switchTo().alert();
		String expected = "The message was successfully sent";
		String actual = alertMsg.getText();
		Assert.assertEquals(actual, expected);
		alertMsg.accept();

		// ***Logout from the current member***
		cyclosmemberMessage.clickLogoutBtn();
		// Assert->Alert message for logging out
		Alert logoutMsg = driver.switchTo().alert();
		String expectedMsg = "Please confirm to logout";
		String actualMsg = logoutMsg.getText();
		Assert.assertEquals(actualMsg, expectedMsg);
		alertMsg.accept();

	}

	@Test(priority = 2)
	public void MessageDetails()

	{
		ArrayList<String> list = new ArrayList<String>();
		// ***Login with message receiver Member and view details
		cyclosloginPOM.sendUserName("NewReeja3");
		cyclosloginPOM.sendPassword("newreeja");
		cyclosloginPOM.clickLoginBtn();
		cyclosfiltermessagePOM.clickPersonalTab();
		cyclosfiltermessagePOM.clickMessagesLink();
		cyclosmemberMessage.clickMessage();
		screenShot.captureScreenShot("CYTC_049_2.Logout Message");

		/*
		 * Assert->Compare Array list created by from member send message details and to
		 * member message details
		 */
		list = cyclosmemberMessage.messageDetails();
		boolean result = list.get(0).equals(fromMember) && list.get(1).equals(subject) && list.get(2).contains(body);

		Assert.assertTrue(result);

	}

}
