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

public class Cyclos_Member_AddContactandPaymentPOM {
	private WebDriver driver;

	public Cyclos_Member_AddContactandPaymentPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *** Personal section_Contacts from left menu***
	@FindBy(xpath = "//*[@class='subMenuText'][contains(text(),'Contacts')]")
	private WebElement contactsLink;

	// ***Add New Contact***
	@FindBy(id = "memberUsername")
	private WebElement memberlogin;
	@FindBy(xpath = "//input[@class='button'][@type='submit']")
	private WebElement submitBtn;

	// ***Payment to added member ***
	@FindBy(xpath = "//a[@class='profileLink']")
	private WebElement addedContact;
	@FindBy(xpath = "//td[@class='label'][contains(text(),'Make payment')]//following-sibling::td[1]")
	private WebElement makepaymentsubmitBtn;
	@FindBy(id = "amount")
	private WebElement paymentAmount;
	@FindBy(id = "description")
	private WebElement description;
	@FindBy(id = "submitButton")
	private WebElement paymentSubmitBtn;

	// ****Click Contacts Link from the left menu under personal section
	public void clickContactsLink() {

		contactsLink.click();
	}

	// *****Enter Member login which need to be added as contact
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

//****Submit to add member as contact
	public void clickSubmitBtn() {

		submitBtn.submit();
	}

//****	Click on added contact from contact List
	public void clickAddedContact() {

		addedContact.click();
	}
	// ****Click make Payment option

	public void clickMakepaymentsubmitBtn() {

		makepaymentsubmitBtn.click();
	}

	// ****Enter Payment details
	public void sendPaymentAmount(String paymentamount) {
		this.paymentAmount.sendKeys(paymentamount);
	}

	public void sendPaymentDescription(String description) {
		this.description.sendKeys(description);
	}
	
	// ****Click Submit to make payment
	public void clickSubmit() {
		paymentSubmitBtn.submit();
	}
	
	// ****Successful payment message 
	public String successMessage()
	{
		String result=driver.findElement(By.xpath("//table[@class='defaultTable']//td")).getText();
		return result;
		
	}
	


}
