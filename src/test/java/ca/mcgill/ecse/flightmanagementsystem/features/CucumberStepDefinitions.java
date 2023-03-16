package ca.mcgill.ecse.flightmanagementsystem.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flightmanagementsystem.application.FMSApplication;
import ca.mcgill.ecse.flightmanagementsystem.controller.FMSController;
import ca.mcgill.ecse.flightmanagementsystem.model.Airport;
import ca.mcgill.ecse.flightmanagementsystem.model.FMS;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {

	/*
	 * In general:
	 * 
	 * @Given will create a bunch of objects e.g. fms.addPerson()
	 * 
	 * @When will call the controller e.g. FMSController.createAirport()
	 * 
	 * @Then will use ASSERTIONS
	 *
	 * String s1 = "hello"; String s2 = "hello";
	 * 
	 * // check if s1 == s2 and return an error if not assertEquals(s1,s2);
	 * assertTrue(s1.equals(s2));
	 */
	
		FMS fms;
		String error = "";

		
		@Given("a FlightManagementSystem exists")
		public void a_FlightManagementSystem_exists() {
			// this method ensures that FMS is not null
			fms = FMSApplication.getFMS();
		}
		
		@Given("the following airports exist in the system")
		public void the_following_airports_exist_in_the_system(
				io.cucumber.datatable.DataTable dataTable) {
			List<Map<String,String>> rows = dataTable.asMaps();
			for(var row: rows) {
				String code = row.get("code");
				String address = row.get("address");
				
				// in the Given statements, we NEVER call the controller
				// we call the model directly using add methods
				fms.addAirport(address, code);
			}	
		}
		
		@When("an airport is added with code {string} and address {string}")
		public void an_airport_is_added_with_code_and_address(String code, String address) {
			error = FMSController.createAirport(code, address);
		}
		
		@Then("an airport shall exist with code {string} and address {string}")
		public void an_airport_shall_exist_with_code_and_address(String code, String address) {
			// return the airport with code {}, or null if no airport exists with that code
			Airport a = Airport.getWithCode(code);
			assertTrue(a != null); // make sure the airport exists with code.
			assertTrue(a.getAddress().equals(address));
		}
		
		@Then("there should be no error")
		public void there_should_be_no_error() {
//			assertTrue(!error.equals(""));
			assertEquals(error, "");
		}
		
		@Then("the number of airports shall be {string}")
		public void the_number_of_airports_shall_be(String number) {
			int numAirports = fms.numberOfAirports();
			assertEquals(numAirports, Integer.parseInt(number));
		}
		
		@Then("there should be an error returned with message {string}")
		public void there_should_be_an_error_returned_with_message(String message) {
			// assertion
			assertEquals(error, message);
		}
	}
