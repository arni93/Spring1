package com.capgemini.chess.controller.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Groups all Rest controllers tests
 * 
 * @author AWOZNICA
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ FilterChallengesRestControllerTest.class, ManualChallengeRestControllerTest.class })
public class RestControllerSuite {
}
