package com.capgemini.chess.batch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Groups all Batch tests and perform them
 * 
 * @author AWOZNICA
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ RemoveAbandonedGamesBatchTest.class })
public class BatchTestSuite {

}
