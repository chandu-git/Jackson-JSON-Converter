package com.jackson;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Jackson2Example {
	public static void main(String[] args) {
		Jackson2Example obj = new Jackson2Example();
		obj.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		Staff staff = createDummyObject();

		try {
			// Convert object to JSON string
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String jsonInString = mapper.writeValueAsString(staff);
			System.out.println(jsonInString);

			// Convert JSON string to Object
			Staff staff1 = mapper.readValue(jsonInString, Staff.class);
			System.out.println(staff1);

			//Pretty print
			String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff1);
			System.out.println(prettyStaff1);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Staff createDummyObject() {

		Staff staff = new Staff();

		staff.setName("Jason");
		staff.setAge(33);
		staff.setPosition("Developer");
		staff.setSalary(new BigDecimal("7500.00"));

		List<String> skills = new ArrayList<>();
		skills.add("java");
		skills.add("python");

		staff.setSkills(skills);
		
		List<Address> addresses = new ArrayList<>();
		
		Address add1 = new Address();
		add1.setCity("Edison");
		add1.setState("NJ");
		
		Address add2 = new Address();
		add2.setCity("Piscataway");
		add2.setState("NJ");
		
		addresses.add(add1);
		addresses.add(add2);
		
		staff.setAddresses(addresses);

		return staff;

	}
}
