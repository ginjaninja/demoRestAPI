package com.ginjaninja.demoRestAPI;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ginjaninja.demoRestAPI.home.HomeControllerTest;
import com.ginjaninja.demoRestAPI.person.PersonControllerTest;
import com.ginjaninja.demoRestAPI.shift.ShiftControllerTest;
import com.ginjaninja.demoRestAPI.shiftAssignment.ShiftAssignmentControllerTest;

@RunWith(Suite.class)
@SuiteClasses({HomeControllerTest.class,
				PersonControllerTest.class,
				ShiftControllerTest.class,
				ShiftAssignmentControllerTest.class})
public class AllTests {

}
