package com.capgemini.chess.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Groups all services tests and performs them
 * 
 * @author AWOZNICA
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ UserServiceTest.class, AdministrationServiceTest.class, ManualChallengeServiceTest.class,
		FilterChallengeServiceTest.class })
public class ServiceTestSuite {
}
