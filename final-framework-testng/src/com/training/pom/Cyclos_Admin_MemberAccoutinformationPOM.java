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

public class Cyclos_Admin_MemberAccoutinformationPOM {
	private WebDriver driver;

	public Cyclos_Admin_MemberAccoutinformationPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *** Accounts_Account information***

	@FindBy(xpath = "//tbody//tr[5]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
	private WebElement accountinfoBtn;
	@FindBy(name = "query(paymentFilter)")
	private WebElement wbpamentType;
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement searchSubmit;
	@FindBy(xpath = "//table[@class=\"defaultTableContent\"]/tbody/tr[1]/td[1]")
	private WebElement searchDetails;

	// ****Click Account info from left menu under Account section
	public void clickAccountinfoSubmitBtn() {
		this.accountinfoBtn.click();

	}

	// ****Select Payment type
	public void selectPaymentType(String visibletext) {

		Select paymentType = new Select(wbpamentType);
		paymentType.selectByVisibleText(visibletext);

	}

//****Click search to get the details 
	public void clickPaymentSubmitBtn() {
		this.searchSubmit.submit();

	}

//**** Transaction details to compare 	
	public String searchTransactionDetails() {

		String value = searchDetails.getText();
		return value;

	}

}
