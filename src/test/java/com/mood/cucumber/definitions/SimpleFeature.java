package com.mood.cucumber.definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SimpleFeature {

	@Given("^a variable x with value (\\d+)$")
	public void a_variable_x_with_value(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^I multiply x by (\\d+)$")
	public void i_multiply_x_by(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^x should equal (\\d+)$")
	public void x_should_equal(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}
	
}
