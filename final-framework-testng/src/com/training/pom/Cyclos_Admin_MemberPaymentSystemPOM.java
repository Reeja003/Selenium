package com.training.pom;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

public class Cyclos_Admin_MemberPaymentSystemPOM {
	private WebDriver driver;

	public Cyclos_Admin_MemberPaymentSystemPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ***Accounts section_Payment system***
	@FindBy(xpath = "//tbody//tr[5]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[2]//td[2]//input[1]")
	private WebElement paymentsystemBtn;
	@FindBy(id = "amount")
	private WebElement amount;
	@FindBy(id = "type")
	private WebElement wbtransactionType;
	@FindBy(id = "description")
	private WebElement paymentdescription;
	@FindBy(id = "submitButton")
	private WebElement paymentSubmitBtn;
	@FindBy(xpath="//input[@class='button'][@value='Submit']")
	private WebElement transactionSubmitBtn;
	@FindBy(xpath="//*[@id=\"tdContents\"]/table/tbody/tr[2]/td/table/tbody/tr[1]/td")
	private WebElement paymentsuccessMsg;

	public void clickPaymentSystemSubmitBtn() {
		this.paymentsystemBtn.click();

	}

	public void sendPaymentAmount(String amount) {

		this.amount.sendKeys(amount);

	}

	public void selectTransactionType(String visibletext) {

		Select transactionType = new Select(wbtransactionType);
		transactionType.selectByVisibleText(visibletext);

	}

	public void sendDescription(String description) {

		this.paymentdescription.sendKeys(description);

	}

	public void clickPaymentSubmit() {
		this.paymentSubmitBtn.submit();

	}

	public void clickTransactionSubmit() {
		this.transactionSubmitBtn.submit();

	}

	public String successpaymentMessaage() {

		String value = paymentsuccessMsg.getText();
		return value;

	}

}
