package com.capgemini.chess.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.ChallengeDaoImpl;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.service.impl.ManualChallengeServiceImpl;
import com.capgemini.chess.service.impl.UserServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ManualChallengeServiceTest {
	@Autowired
	private ManualChallangeService service;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}

		@Bean
		public ManualChallangeService manualChallengeService() {
			return new ManualChallengeServiceImpl();
		}

		@Bean
		public UserDao userDao() {
			return new UserDaoImpl();
		}

		@Bean
		public ChallengeDao challengeDao() {
			return new ChallengeDaoImpl();
		}
	}

	@Test
	public void shouldFindUserWhenGivenUserId2() throws Exception {
		// given
		long id = 2;
		// when
		UserProfileTO foundUser = this.service.findUserById(id);
		// then
		Assert.assertNotNull(foundUser);
		Assert.assertEquals(id, foundUser.getId());
	}

	@Test
	public void shouldNotFindUserWhenGivenUserId10000() throws Exception {
		// given
		long givenId = 10000;
		// when
		UserProfileTO foundUser = this.service.findUserById(givenId);
		// then
		Assert.assertNull(foundUser);
	}

	@Test
	public void shouldFindTwoUsersWithGivenNamekrzysztof() throws Exception {
		// given
		String givenName = "krzysztof";
		// when
		List<UserProfileTO> foundUsers = this.service.findUserByName(givenName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(2, foundUsers.size());
		Assert.assertEquals(givenName, foundUsers.get(0).getName());
		Assert.assertEquals(givenName, foundUsers.get(1).getName());
	}

	@Test
	public void shouldFindNoUsersWithGivenNameAAA() throws Exception {
		// given
		String givenName = "AAA";
		// when
		List<UserProfileTO> foundUsers = this.service.findUserByName(givenName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindOneUserWithSurnametylda() throws Exception {
		// given
		String givenSurname = "tylda";
		// when
		List<UserProfileTO> foundUsers = this.service.findUserBySurname(givenSurname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
		Assert.assertEquals(givenSurname, foundUsers.get(0).getSurname());
	}

	@Test
	public void shouldFindNoUsersWithSurnameBBB() throws Exception {
		// given
		String givenSurname = "BBB";
		// when
		List<UserProfileTO> foundUsers = this.service.findUserBySurname(givenSurname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldReturnTrueAndAddNewChallengeWhenGivenSenderAndReceiverIdExists() throws Exception {
		// given
		long senderId = 1;
		long receiverId = 2;
		// when
		boolean result = this.service.createChallenge(senderId, receiverId);
		// then
		Assert.assertTrue(result);
	}

	@Test
	public void shouldReturnFalseAndAddNoChallengesWhenGivenSenderIdNotExists() throws Exception {
		// given
		long senderId = 1000;
		long receiverId = 2;
		// when
		boolean result = this.service.createChallenge(senderId, receiverId);
		// then
		Assert.assertFalse(result);
	}

	@Test
	public void shouldReturnFalseAndAddNoChallengeWhenGivenReceiverIdNotExists() throws Exception {
		// given
		long senderId = 1;
		long receiverId = 2000;
		// when
		boolean result = this.service.createChallenge(senderId, receiverId);
		// then
		Assert.assertFalse(result);
	}
}
