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

public class Cyclos_Member_AccountInformationPOM {
	private WebDriver driver;

	public Cyclos_Member_AccountInformationPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// *** Accounts Section_Accountinformation Link***
	@FindBy(xpath = "//*[@id=\"menu2\"]/span[2]")
	private WebElement account;
	@FindBy(xpath="//*[@id=\"submenu2.0\"]/span[2]")
	private WebElement accountinfo;
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[5]/img")
	private WebElement viewicon;
	@FindBy(id="backButton")
	private WebElement backBtn;
	@FindBy(xpath="//table [@class=\"defaultTableContent\"]/tbody/tr[1]/td[1]")
	private WebElement transpage;

	public void clickAccountLink() {

		account.click();
	}

	public void clickAccountInformationLink() {

		accountinfo.click();
	}

	public void clickViewIcon() {

		viewicon.click();
	}

	public void clickBackbtn() {

		backBtn.click();
	}

	public String transactionDetails() {

		String value = transpage.getText();
		return value;

	}

}
