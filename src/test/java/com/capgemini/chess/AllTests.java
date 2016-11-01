package com.capgemini.chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.chess.batch.BatchTestSuite;
import com.capgemini.chess.dao.DaoTestSuite;
import com.capgemini.chess.service.ServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ ServiceTestSuite.class, DaoTestSuite.class, BatchTestSuite.class })
public class AllTests {

}
