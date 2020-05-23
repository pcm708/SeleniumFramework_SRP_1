package com.qa.automation;

import com.qa.hubspot.keywords.DashboardPageAction;
import com.qa.hubspot.keywords.GooglePageAction;
import com.qa.hubspot.keywords.LoginPageAction;

public class GoogleSignInTestSessionInitiator extends TestSessionInitiator {

	public GooglePageAction googlePage;
	public LoginPageAction loginPage;
	public DashboardPageAction dbPage;
	
	private void _initPage() {
		googlePage= new GooglePageAction(driver);
		loginPage = new LoginPageAction(driver);
		dbPage = new DashboardPageAction(driver);
	}

	public GoogleSignInTestSessionInitiator() {
		super();
		configureBrowser();
		_initPage();
	}
}
