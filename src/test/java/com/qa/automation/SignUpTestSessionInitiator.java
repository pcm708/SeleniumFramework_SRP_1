package com.qa.automation;

import com.qa.hubspot.keywords.LoginPageAction;
import com.qa.hubspot.keywords.SignUpPageAction;

public class SignUpTestSessionInitiator extends TestSessionInitiator{

	public SignUpPageAction signupPage;
	public LoginPageAction loginPage;

	private void _initPage() {
		signupPage = new SignUpPageAction(driver);
		loginPage = new LoginPageAction(driver);
	}

	public SignUpTestSessionInitiator() {
		super();
		_initPage();
	}
}
