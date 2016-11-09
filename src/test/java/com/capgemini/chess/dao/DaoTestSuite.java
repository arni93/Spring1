package com.capgemini.chess.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Groups all Dao tests and perform them
 * 
 * @author AWOZNICA
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ UserDaoTest.class, ChallengeDaoTest.class, GameDaoTest.class })
public class DaoTestSuite {

}
