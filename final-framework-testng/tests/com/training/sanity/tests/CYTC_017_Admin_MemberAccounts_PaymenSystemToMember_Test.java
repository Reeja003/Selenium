//*************Verify whether application allows admin to make the payment for member******
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Admin_MemberPaymentSystemPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_017_Admin_MemberAccounts_PaymenSystemToMember_Test {

	private WebDriver driver;
	private String baseUrl;
	private CyclosLoginPOM cyclosLoginPOM;
	private Cyclos_Admin_MemberPaymentSystemPOM cyclosmemberpaymentPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosLoginPOM = new CyclosLoginPOM(driver);
		cyclosmemberpaymentPOM = new Cyclos_Admin_MemberPaymentSystemPOM(driver);
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

	@Test
	public void memberPaymentSystemTest() {

		// ****Enter member name and submit payment for memebr
		String member = "manzoor";
		cyclosLoginPOM.sendMemberLogin(member);
		cyclosmemberpaymentPOM.clickPaymentSystemSubmitBtn();
		cyclosmemberpaymentPOM.sendPaymentAmount("500");
		cyclosmemberpaymentPOM.selectTransactionType("Debit to member");
		cyclosmemberpaymentPOM.sendDescription("bonus");
		cyclosmemberpaymentPOM.clickPaymentSubmit();
		screenShot.captureScreenShot("CYTC_017_1.Paymentdetails");
		cyclosmemberpaymentPOM.clickTransactionSubmit();
		screenShot.captureScreenShot("CYTC_017_2.Successfulpayment");
		// Assert->payment performed message
		String expected = "The payment has been performed";
		String actual = cyclosmemberpaymentPOM.successpaymentMessaage();
		Assert.assertEquals(actual, expected);

	}

}
