package com.capgemini.chess.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.service.impl.UserServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}

		@Bean
		public UserDao userDao() {
			return new UserDaoImpl();
		}
	}

	@Test
	public void testReadUserSuccessful() throws Exception {
		// when
		UserProfileTO userTO = userService.readUser(1L);
		assertNotNull(userTO);
	}

	@Test
	public void shouldFindOneUserWhenLoginawoznica() throws Exception {
		// given
		String login = "awoznica";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsersByLogin(login);
		// then
		assertNotNull(foundUsers);
		assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldFindNoUsersWhenLoginABCD() throws Exception {
		// given
		String login = "ABCD";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsersByLogin(login);
		// then
		assertNotNull(foundUsers);
		assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindOneUserWhenGivenIdEquals4() throws Exception {
		// given
		final long id = 4;
		// when
		UserProfileTO foundUser = this.userService.findUserById(4);
		// then
		Assert.assertNotNull(foundUser);
		Assert.assertEquals(id, foundUser.getId());
	}

	@Test
	public void shouldReturnNoUsersWhenGivenIdEquals100() throws Exception {
		// given
		final long id = 100;
		// when
		UserProfileTO foundUser = this.userService.findUserById(id);
		// then
		Assert.assertNull(foundUser);
	}

	@Test
	public void shouldReturnTwoUsersForGivenName() throws Exception {
		// given
		final String givenName = "krzysztof";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsersByName(givenName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(2, foundUsers.size());
	}

	@Test
	public void shouldReturnNoUsersForGivenName() throws Exception {
		// given
		final String givenName = "no name";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsersByName(givenName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldReturnOneUserForGivenSurname() throws Exception {
		// given
		final String givenSurname = "paluch";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsersBySurname(givenSurname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldReturnNoUsersForGivenSurname() throws Exception {
		// given
		final String givenSurname = "PPaluch";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsersBySurname(givenSurname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldReturnOneUserForGivenData() throws Exception {
		// given
		final String givenLogin = "awoznica";
		final String givenName = "arnold";
		final String givenSurname = "woznica";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsers(givenLogin, givenName, givenSurname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldReturnNoUsersForGivenData() throws Exception {
		// given
		final String givenLogin = "awoznica";
		final String givenName = "";
		final String givenSurname = "woznica";
		// when
		List<UserProfileTO> foundUsers = this.userService.findUsers(givenLogin, givenName, givenSurname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}
}
