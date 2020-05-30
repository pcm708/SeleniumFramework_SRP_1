package com.qa.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.automation.SignInTestSessionInitiator;

public class GoogleSignInTest {

	SignInTestSessionInitiator signIn;
	
	private void _initVars() {
		signIn = new SignInTestSessionInitiator();
	}
	
	@BeforeClass	
	public void Start_Test_Session() {
		_initVars();
	}
	
	@BeforeMethod
	public void handleTestMethodName(Method method) {
		signIn.stepStartMessage(method.getName());
	}
	
	@Test(priority = 0)
	public void Step1_launchApplication() {
		signIn.launchApplication();
		Assert.assertTrue(
		signIn.loginPage.verifyPageTitle("HubSpot Login"));
		System.out.println("Hubspot application launched");
	}
	
	@Test(priority = 1)
	public void Step2_signInUsingGoogle() {
		signIn.googlePage = signIn.loginPage.click_signInWithGoogle();
		Assert.assertTrue(
		signIn.loginPage.verifyPageTitle("Sign in – Google accounts"));
		System.out.println("User is on Google Sign In Page");
	}
	
	@Test(priority = 2)
	public void Step3_verifyHubspotDashboardPage() {
		System.out.println("Entering the user credentials...");
		signIn.dbPage = signIn.googlePage.signIn();
		signIn.dbPage.clickOnHubspotIcon();
		System.out.println("User logged into Hubspot Application");
		Assert.assertTrue(signIn.dbPage.verifyPageTitle("Dashboard Library"));
		System.out.println("Successfull SignIn");
	}
	
	@AfterClass(alwaysRun = true)
	public void Stop_Test_Session() {
		signIn.closebrowserSession();
	}
}