//*********whether application allows admin to display the loans granted details for member based on the filtered criteria should get displayed****
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
import com.training.generics.ScreenShot;
import com.training.pom.CyclosLoginPOM;
import com.training.pom.Cyclos_Admin_MemberViewLoansPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_019_Admin_MemberLoans_ViewLoans_Test {

	private WebDriver driver;
	private String baseUrl;
	private CyclosLoginPOM cyclosLoginPOM;
	private Cyclos_Admin_MemberViewLoansPOM cyclosmemberloanPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {

		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosLoginPOM = new CyclosLoginPOM(driver);
		cyclosmemberloanPOM = new Cyclos_Admin_MemberViewLoansPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
//open url and login with admin credentials 
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

	// ****Test Member should be able to view the loan
	@Test
	public void memberViewLoansTest() {

		// ****Enter member Login and view loan for the member
		String member = "manzoor";
		cyclosLoginPOM.sendMemberLogin(member);
		cyclosmemberloanPOM.clickViewLoanBtn();

		/*
		 * Assert->Compare the remaining loan amount for closed radio button should be
		 * zero
		 */
		boolean closedExpected = true;
		boolean closedActual = cyclosmemberloanPOM.clickClosedRadioBtn();
		screenShot.captureScreenShot("CYTC_019_1.ClosedLoan");
		Assert.assertEquals(closedActual, closedExpected);

		// Assert->Compare the remaining loan amount for closed radio button should not
		// be zero */
		boolean openExpected = false;
		boolean openActual = cyclosmemberloanPOM.clickOpenRadioBtn();
		screenShot.captureScreenShot("CYTC_019_2.OpenLoan");
		Assert.assertEquals(openActual, openExpected);

	}
}
