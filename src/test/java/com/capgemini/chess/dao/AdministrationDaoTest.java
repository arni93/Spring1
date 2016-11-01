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

import com.capgemini.chess.dataaccess.dao.AdministrationDao;
import com.capgemini.chess.dataaccess.dao.impl.AdministrationDaoImpl;
import com.capgemini.chess.service.to.UserProfileTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AdministrationDaoTest {

	@Autowired
	AdministrationDao administrationDao;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		@Scope("prototype")
		public AdministrationDao administrationDao() {
			return new AdministrationDaoImpl();
		}
	}

	@Test
	public void shouldFindOnePersonWhenGivenLoginawoznica() throws Exception {
		// given
		String login = "awoznica";
		// when
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByLogin(login);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldFindNoPeopleWhenGivenLoginAAABBB() throws Exception {
		// given
		String login = "AAABBB";
		// when
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByLogin(login);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindTwoPeopleWhenGivenNamekrzysztof() throws Exception {
		// given
		String name = "krzysztof";
		// when
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByFirstName(name);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(2, foundUsers.size());
	}

	@Test
	public void shouldFindNoPeopleWhenGivenSurnameTylda() throws Exception {
		// Given
		String surname = "Tylda";
		// when
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByLastName(surname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindOnePersonWhenGivenSurnametylda() throws Exception {
		// given
		String surname = "tylda";
		// when
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByLastName(surname);
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
		List<UserProfileTO> foundUsers = this.administrationDao.findUsers(login, firstName, lastName);
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
		List<UserProfileTO> foundUsers = this.administrationDao.findUsers(login, firstName, lastName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldChangeFirstName() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.administrationDao.findUserById(givenId);
		final String userNameOnStart = userOnStart.getName();
		// when
		this.administrationDao.setUserFirstName(givenId, "aaabbb");
		UserProfileTO userAfterChange = this.administrationDao.findUserById(givenId);
		Assert.assertNotEquals(userNameOnStart, userAfterChange.getName());
	}

	@Test
	public void shouldChangeLastName() throws Exception {
		// given
		long givenId = 1;
		UserProfileTO userOnStart = this.administrationDao.findUserById(givenId);
		final String userLastNameOnStart = userOnStart.getSurname();
		// when
		this.administrationDao.setUserLastName(givenId, "surname");
		UserProfileTO userAfterChange = this.administrationDao.findUserById(givenId);
		Assert.assertNotEquals(userLastNameOnStart, userAfterChange.getName());
	}

	@Test
	public void shouldChangeEmailAddress() throws Exception {
		// given
		long givenId = 3;
		UserProfileTO userOnStart = this.administrationDao.findUserById(givenId);
		final String userEmailOnStart = userOnStart.getEmail();
		// when
		this.administrationDao.setUserEmailAddress(givenId, "email@email.com");
		UserProfileTO userAfterChange = this.administrationDao.findUserById(givenId);
		Assert.assertNotEquals(userEmailOnStart, userAfterChange.getEmail());
	}

	@Test
	public void shouldChangeAboutMeNote() throws Exception {
		// given
		long givenId = 4;
		UserProfileTO userOnStart = this.administrationDao.findUserById(givenId);
		final String userAboutMeOnStart = userOnStart.getAboutMe();
		// when
		this.administrationDao.setUserAboutMeNote(givenId, "i am no one");
		UserProfileTO userAfterChange = this.administrationDao.findUserById(givenId);
		Assert.assertNotEquals(userAboutMeOnStart, userAfterChange.getAboutMe());
	}

	@Test
	public void shouldChangeLifeMotto() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.administrationDao.findUserById(givenId);
		final String userLifeMottoOnStart = userOnStart.getLifeMotto();
		// when
		this.administrationDao.setUserLifeMotto(givenId, "carpe diem");
		UserProfileTO userAfterChange = this.administrationDao.findUserById(givenId);
		Assert.assertNotEquals(userLifeMottoOnStart, userAfterChange.getLifeMotto());
	}

	@Test
	public void shouldRemoveUserWithId2() throws Exception {
		// given
		long userId = 2;
		UserProfileTO userBeforeDeleting = this.administrationDao.findUserById(userId);
		this.administrationDao.deleteUser(userId);
		UserProfileTO userAfterDeleting = this.administrationDao.findUserById(userId);
		Assert.assertNotNull(userBeforeDeleting);
		Assert.assertNull(userAfterDeleting);
	}
}