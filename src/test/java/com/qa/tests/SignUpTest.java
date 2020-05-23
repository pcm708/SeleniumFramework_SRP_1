package com.qa.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.automation.SignUpTestSessionInitiator;

public class SignUpTest {
	
	SignUpTestSessionInitiator signUp;

	private void _initVars() {
		signUp = new SignUpTestSessionInitiator();
	}
	
	@BeforeClass
	public void Start_Test_Session() {
		_initVars();
	}
	
	@BeforeMethod
	public void handleTestMethodName(Method method) {
		signUp.stepStartMessage(method.getName());
	}
	
	@Test(priority = 0)
	public void Step1_launchApplication() {
		signUp.launchApplication();
		Assert.assertTrue(
		signUp.loginPage.verifyPageTitle("HubSpot Login"));
		System.out.println("Hubspot application launched");
	}
	
	@Test(priority = 1)
	public void Step2_clickSignUpLink() {
		signUp.signupPage = signUp.loginPage.click_signUpLink();
		Assert.assertTrue(
		signUp.signupPage.verifyPageTitle("Get started with HubSpot CRM"));
		System.out.println("User is on HubSpot Sign Up Page");
	}
	
	@Test(priority = 2)
	public void Step3_verifySignUpPageLinks() {
		Assert.assertTrue(
		signUp.signupPage.verifySignUpPage());
		System.out.println("User is on HubSpot Sign Up Page");
	}
	
	@AfterClass(alwaysRun = true)
	public void Stop_Test_Session() {
		signUp.closebrowserSession();
	}
}