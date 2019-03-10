//********Verify whether application displays error message while entering invalid details while performing payment to multiple registered member
package com.training.sanity.tests;

import java.awt.AWTException;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.CyclosDataProviders;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Admin_MemberPaymentSystemPOM;
import com.training.pom.Cyclos_Member_AddContactandPaymentPOM;
import com.training.pom.Cyclos_Member_FilterMessagePOM;
import com.training.pom.Cyclos_Member_ScheduledPaymentsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_080_Member_InvalidPaymentTo_MultiMember_Test {

	private WebDriver driver;

	private String baseUrl;
	private CyclosLoginPOM cyclosloginPOM;
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
		cyclosscheduledpaymentPOM = new Cyclos_Member_ScheduledPaymentsPOM(driver);
		cyclosmemberaddContact=new Cyclos_Member_AddContactandPaymentPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser and login with member credential
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

	

	// ****Make payment to multiple member
	@Test(dataProvider = "excel-inputs3", dataProviderClass = CyclosDataProviders.class)
	public void makePayment(String memberLogin,String amount,String description) throws AWTException {

		cyclosscheduledpaymentPOM.clickAccountTab();
		cyclosscheduledpaymentPOM.clickMemberPaymentLink();
		cyclosscheduledpaymentPOM.sendLogin(memberLogin);
		cyclosmemberaddContact.sendPaymentAmount(amount);
		cyclosmemberaddContact.sendPaymentDescription(description);
		screenShot.captureScreenShot("CYTC_080_1.invalid Payment");
		cyclosmemberaddContact.clickSubmit();
		
		//Assert->Alert message  for invalid data
		Alert paymentAlert = driver.switchTo().alert();
		String actualMsg=paymentAlert.getText();
		Assert.assertTrue(actualMsg.contains("Amount is required"));
	    paymentAlert.accept();
		cyclosscheduledpaymentPOM.clickAccountTab();

	}

}
