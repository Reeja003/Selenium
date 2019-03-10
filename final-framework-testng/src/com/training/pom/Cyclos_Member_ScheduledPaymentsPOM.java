package com.training.pom;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.mysql.jdbc.Driver;

public class Cyclos_Member_ScheduledPaymentsPOM {
	private WebDriver driver;

	public Cyclos_Member_ScheduledPaymentsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *** Account section from left menu***
	@FindBy(xpath = "//*[@class='menuText'][contains(text() ,'Account')]")
	private WebElement accountTab;
	@FindBy(xpath = "//span[@class='subMenuText'][contains(text(),'Member Payment')]")
	private WebElement membepaymentLink;
	@FindBy(xpath = "//span[@class='subMenuText'][contains(text(),'Scheduled payments')]")
	private WebElement scheduledPaymentstLink;

	// ***Search transactions on Member account section ***
	@FindBy(id = "memberUsername")
	private WebElement loginTxt;
	@FindBy(id = "memberName")
	private WebElement name;
	@FindBy(name = "amount")
	private WebElement amountTxt;
	@FindBy(id = "schedulingTypeSelect")
	private WebElement wbscheduleType;
	@FindBy(xpath = "//input[@class='small date']//following-sibling::img")
	private WebElement calendar;
	@FindBy(id = "description")
	private WebElement description;
	@FindBy(id = "submitButton")
	private WebElement submitBtn;
	@FindBy(xpath = "//input[@value=\"Submit\"]")
	private WebElement transsubmitBtn;
	String amountResult;

	// ***Click account tab then member link to get payment to member screen
	public void clickAccountTab() {

		accountTab.click();
	}

	public void clickMemberPaymentLink() {
		
		membepaymentLink.click();
	}

//******Enter login name 
	public void sendLogin(String memberLogin) throws AWTException {

		this.loginTxt.sendKeys(memberLogin);
		WebElement search = loginTxt;
		Actions act = new Actions(driver);
		act.moveToElement(search).build().perform();
		WebElement list = driver.findElement(By.xpath("//table[@class='defaultTableContent']//tbody//ul//li[1]"));
		act.moveToElement(list).click().perform();

	}

//******Enter Amount
	public void sendAmount(String amount) {

		this.amountTxt.sendKeys(amount);
		amountResult = amountTxt.getText();

	}

//******Select scheduleType
	public void selectSchedule(int index) {

		Select scheduleType = new Select(wbscheduleType);
		scheduleType.selectByIndex(index);

	}

//******Select Schedule Date
	public String clickScheduleDate() {
		calendar.click();
		WebElement search = calendar;
		Actions act = new Actions(driver);
		act.moveToElement(search).build().perform();
		WebElement date = driver.findElement(By.xpath("//tr[5][@class='daysrow']//td//following-sibling::td[5]"));
		act.moveToElement(date).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String selectDate = driver.findElement(By.xpath("//input[@class='small date']")).getAttribute("value");
		return selectDate;

	}

//******Enter Description 
	public void sendDescription(String description) {

		this.description.sendKeys(description);

	}

//****Click submit the click on submit in transaction confirmation page
	public void clickSubmitBtn() {

		submitBtn.submit();

	}

	public void clicktranSubmitBtn() {

		transsubmitBtn.submit();

	}

//****Send Transaction confirmation page message to compare 
	public String actualMessage() {
		String actual = driver.findElement(By.xpath("//td[@class='label']")).getText();
		return actual;

	}

//****Send Scheduled payment page to compare 
	public String actualPage() {
		String page = driver.findElement(By.xpath("//*[@class='tdHeaderTable']")).getText();
		return page;

	}

//***click scheduled payments link from left menu under accounts section 
	public void clickScheduledPaymentsLink() {

		clickAccountTab();
		clickAccountTab();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		scheduledPaymentstLink.click();
	}

//****Create Array List with all scheduled payment details to compare 
	public ArrayList<String> schedulePaymentDetails()

	{
		ArrayList<String> list = new ArrayList<String>();

		String scheduleddate = driver
				.findElement(By.xpath("//td[contains(text(),'Scheduled')]/preceding-sibling::td[5]")).getText();
		String scheduledtoMember = driver
				.findElement(By.xpath("//td[contains(text(),'Scheduled')]/preceding-sibling::td[3]")).getText();
		String scheduledAmount = driver
				.findElement(By.xpath("//td[contains(text(),'Scheduled')]/preceding-sibling::td[2]")).getText();
		list.add(scheduleddate);
		list.add(scheduledtoMember);
		list.add(scheduledAmount);
		return list;

	}

}
