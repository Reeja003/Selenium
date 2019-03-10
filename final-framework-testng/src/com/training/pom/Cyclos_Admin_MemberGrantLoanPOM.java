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

public class Cyclos_Admin_MemberGrantLoanPOM {
	private WebDriver driver;

	public Cyclos_Admin_MemberGrantLoanPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ***Loans Section_GrantLoan***

	@FindBy(xpath = "//tbody//tr[8]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[4]//input[1]")
	private WebElement grantLoanBtn;
	@FindBy(name = "loan(amount)")
	private WebElement loanAmount;
	@FindBy(name = "loan(description)")
	private WebElement loanDescription;
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement loansubmitBtn;
	@FindBy(xpath = "//input[@class=\"button\"][@value=\"Submit\"]")
	private WebElement loanconfitmBtn;
	// ****Home tab from left menu 
	@FindBy(xpath = "//*[@class='menuText'][contains(text(),'Home')]")
	private WebElement homeBtn;
	
	@FindBy(xpath= "//*[@class=\"menuText\"][contains(text(),\"Logout\")]")
	private WebElement logout;
	

	// ****Enter Loan description
	public void sendLoanDescription(String desciption) {

		this.loanDescription.sendKeys(desciption);
	}

	// **** Click on Grand loan
	public void clickGrantLoansubmitBtn() {
		this.grantLoanBtn.click();

	}

	
	//***Logout from admin
	public void clickLogout()
	{
		this.logout.click();
	}

	// **** Enter Loan amount
	public void sendLoanAmount(String amount) {

		this.loanAmount.sendKeys(amount);

	}

	// ****Submit Loan
	public void clickLoanSubmit() {
		this.loansubmitBtn.submit();

	}

	// ****Submit loan confirmation
	public void clickLoanConfirmSubmit() {
		this.loanconfitmBtn.submit();

	}

	// ****Submit loan confirmation
		public void clickHomeBtn() {
			this.homeBtn.click();

		}
		
		public String loanAmount(String amount) {

			this.loanAmount.sendKeys(amount);
			return amount;

		}
		
		public String loanDescription(String desciption) {

			this.loanDescription.sendKeys(desciption);
			return desciption;
		}
		
		
}
