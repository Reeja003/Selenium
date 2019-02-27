package com.training.pom;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.util.ArrayList;
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

public class Cyclos_Member_ViewMemberMessagePOM {
	private WebDriver driver;

	public Cyclos_Member_ViewMemberMessagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *** Message List ***
	@FindBy(id = "newButton")
	private WebElement newBtn;

	// ***Send message section***
	@FindBy(id = "sendToSelect")
	private WebElement wbsendTo;
	@FindBy(id = "memberUsername")
	private WebElement memberlogin;
	@FindBy(id = "subjectText")
	private WebElement subject;
	@FindBy(xpath = "//iframe[1]")
	private WebElement iFrame;
	@FindBy(xpath = "//*[@class='CSS1Compat']")
	private WebElement bodymsg;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitBtn;

	// ****logout***
	@FindBy(xpath = "//*[@class='menuText'][contains(text() ,'Logout')]")
	private WebElement logout;

	// ***To member message details***
	@FindBy(xpath = "//a[@class='messageDetails'][1]")
	private WebElement viewMessage;
	@FindBy(xpath = "//a[@class='profileLink']")
	private WebElement fromMember;
	@FindBy(xpath = "//table[@class='defaultTable']//tbody//tr[4]//td[@class='headerField']")
	private WebElement messageSubject;
	@FindBy(xpath = "//table[@class='defaultTable']//tbody//tr[5]//td[@class='headerField']")
	private WebElement messageBody;

	// ****Click send a new message
	public void clickNewSubmitBtn() {

		newBtn.click();
	}

	// ****Select send to option
	public void selectSendTo(String visibleTXT) {

		Select sendTo = new Select(wbsendTo);
		sendTo.selectByVisibleText(visibleTXT);
	}

	// ****Enter Member login
	public void sendMemberLogin(String loginTxt) throws AWTException {

		this.memberlogin.sendKeys(loginTxt);
		WebElement search = memberlogin;
		Actions act = new Actions(driver);
		act.moveToElement(search).build().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement list = driver
				.findElement(By.xpath("//table[@class='defaultTable']//ul//li[contains(text(),'NewReeja3')]"));
		act.moveToElement(list).click().perform();
	}

	// ****Enter subject
	public void sendSubjectTXT(String subject) {
		this.subject.sendKeys(subject);
	}

	// ****Enter message in the body section 
	public void sendBodyTXT(String bodymsg)

	{

		driver.switchTo().frame(iFrame);
		this.bodymsg.sendKeys(bodymsg);
		driver.switchTo().defaultContent();

	}

	// ****Click submit to send the message
	public void clickSubmitBtn() {

		submitBtn.submit();
	}

	// ****Logout from the current member account
	public void clickLogoutBtn() {

		logout.click();
	}

	// ****Click message Link once login with message receiver member
	public void clickMessage() {

		viewMessage.click();
	}

	// ****Create an Array list with sent message details to compare
	public ArrayList<String> messageDetails()

	{
		ArrayList<String> list = new ArrayList<String>();
		String fromMembr = fromMember.getText();
		String subject = messageSubject.getText();
		String body = messageBody.getText();
		list.add(fromMembr);
		list.add(subject);
		list.add(body);
		return list;
	}

}
