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

public class Cyclos_Member_ViewandRepayLoanPOM {
	private WebDriver driver;

	public Cyclos_Member_ViewandRepayLoanPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ****Loans from left menu account section
	@FindBy(xpath = "//*[@class=\"subMenuText\"][contains(text(),\"Loans\")]")
	private WebElement loans;
	// ****View loan symbol
	// @FindBy(xpath = "//img[@class=\"details view\"]")
	// private WebElement viewloan;
	// ****Amount text
	@FindBy(id = "amountText")
	private WebElement amount;
	// ****Repay button
	@FindBy(xpath = "//*[@class=\"button\"][@value=\"Repay\"]")
	private WebElement repayBtn;

	// ****Click Loans
	public void clickLoans() {

		loans.click();
	}

	//**** Move to last page
	public void clickLastpage()
	{
	WebElement lastPage=driver.findElement(By.xpath("//*[@class='paginationLink'][position()=last()-1]"));
	lastPage.click();
	}
	// **** Click viewloan
	public void clickView() {
		WebElement viewloan=driver.findElement(By.xpath("//*[@class=\"tdContentTableLists innerBorder\"]//tbody//tr[position()=last()]//img[@class=\"details view\"]"));
		viewloan.click();

	}

	// **** Enter the amount to repay
	public void sendamount(String amount) {

		this.amount.clear();
		this.amount.sendKeys(amount);
	}

	// ****click Repay loan button

	public void clickRepayBtn() {
		repayBtn.submit();
	}

	public String resultAmount() {
		String resultaAmount = this.amount.getAttribute("value");
		return resultaAmount;

	}

}
