package com.capgemini.chess.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.service.to.UserProfileTO;

/**
 * tests UserDao
 * 
 * @author AWOZNICA
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		@Scope("prototype")
		public UserDao userDao() {
			return new UserDaoImpl();
		}
	}

	@Test
	public void shouldAddUserEntityToExistingEntities() throws Exception {
		// given
		UserProfileTO userTO = new UserProfileTO();
		userTO.setLogin("login123");
		userTO.setName("name321");
		// when
		int sizeBeforeAdding = userDao.getAllUsers().size();
		this.userDao.addUser(userTO);
		int sizeAfterAdding = userDao.getAllUsers().size();
		Assert.assertEquals(sizeBeforeAdding + 1, sizeAfterAdding);
	}

	@Test
	public void shouldFindOnePersonWhenGivenLoginawoznica() throws Exception {
		// given
		String login = "awoznica";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsersByLogin(login);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldFindNoPeopleWhenGivenLoginAAABBB() throws Exception {
		// given
		String login = "AAABBB";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsersByLogin(login);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindTwoPeopleWhenGivenNamekrzysztof() throws Exception {
		// given
		String name = "krzysztof";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsersByName(name);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(2, foundUsers.size());
	}

	@Test
	public void shouldFindNoPeopleWhenGivenSurnameTylda() throws Exception {
		// Given
		String surname = "Tylda";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsersBySurname(surname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindOnePersonWhenGivenSurnametylda() throws Exception {
		// given
		String surname = "tylda";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsersBySurname(surname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldFindOnePersonForGivenData() throws Exception {
		// given
		String login = "myszka123";
		String firstName = "olga";
		String lastName = "nowak";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsers(login, firstName, lastName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shoudFindNoPersonForGivenData2() throws Exception {
		// given
		String login = "myszka123";
		String firstName = "olgaaa";
		String lastName = "nowak";
		// when
		List<UserProfileTO> foundUsers = this.userDao.findUsers(login, firstName, lastName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldChangeFirstName() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.userDao.findUserById(givenId);
		final String userNameOnStart = userOnStart.getName();
		// when
		this.userDao.setUserName(givenId, "aaabbb");
		UserProfileTO userAfterChange = this.userDao.findUserById(givenId);
		Assert.assertNotEquals(userNameOnStart, userAfterChange.getName());
	}

	@Test
	public void shouldChangePassword() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.userDao.findUserById(givenId);
		final String userPasswordOnStart = userOnStart.getPassword();
		// when
		this.userDao.setUserPassword(givenId, "aaabbb");
		UserProfileTO userAfterChange = this.userDao.findUserById(givenId);
		final String userPasswordAfterChange = userAfterChange.getName();
		Assert.assertNotEquals(userPasswordOnStart, userPasswordAfterChange);
	}

	@Test
	public void shouldChangeLastName() throws Exception {
		// given
		long givenId = 1;
		UserProfileTO userOnStart = this.userDao.findUserById(givenId);
		final String userLastNameOnStart = userOnStart.getSurname();
		// when
		this.userDao.setUserSurname(givenId, "surname");
		UserProfileTO userAfterChange = this.userDao.findUserById(givenId);
		Assert.assertNotEquals(userLastNameOnStart, userAfterChange.getName());
	}

	@Test
	public void shouldChangeEmailAddress() throws Exception {
		// given
		long givenId = 3;
		UserProfileTO userOnStart = this.userDao.findUserById(givenId);
		final String userEmailOnStart = userOnStart.getEmail();
		// when
		this.userDao.setUserEmail(givenId, "email@email.com");
		UserProfileTO userAfterChange = this.userDao.findUserById(givenId);
		Assert.assertNotEquals(userEmailOnStart, userAfterChange.getEmail());
	}

	@Test
	public void shouldChangeAboutMeNote() throws Exception {
		// given
		long givenId = 4;
		UserProfileTO userOnStart = this.userDao.findUserById(givenId);
		final String userAboutMeOnStart = userOnStart.getAboutMe();
		// when
		this.userDao.setUserAboutMe(givenId, "i am no one");
		UserProfileTO userAfterChange = this.userDao.findUserById(givenId);
		Assert.assertNotEquals(userAboutMeOnStart, userAfterChange.getAboutMe());
	}

	@Test
	public void shouldChangeLifeMotto() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.userDao.findUserById(givenId);
		final String userLifeMottoOnStart = userOnStart.getLifeMotto();
		// when
		this.userDao.setUserLifeMotto(givenId, "carpe diem");
		UserProfileTO userAfterChange = this.userDao.findUserById(givenId);
		Assert.assertNotEquals(userLifeMottoOnStart, userAfterChange.getLifeMotto());
	}

	@Test
	public void shouldRemoveUserWithId2() throws Exception {
		// given
		long userId = 2;
		UserProfileTO userBeforeDeleting = this.userDao.findUserById(userId);
		this.userDao.deleteUser(userId);
		UserProfileTO userAfterDeleting = this.userDao.findUserById(userId);
		Assert.assertNotNull(userBeforeDeleting);
		Assert.assertNull(userAfterDeleting);
	}

	@Test
	public void shouldReturnSixEntities() throws Exception {
		// when
		List<UserProfileTO> allUsers = this.userDao.getAllUsers();
		int size = allUsers.size();
		Assert.assertEquals(7, size);
	}
}
