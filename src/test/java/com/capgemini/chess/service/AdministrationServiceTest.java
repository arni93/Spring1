package com.capgemini.chess.service;

import static org.junit.Assert.assertNotNull;

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
import com.capgemini.chess.service.impl.AdministrationServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;

/**
 * @author AWOZNICA
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AdministrationServiceTest {

	@Autowired
	AdministrationService administrationService;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		@Scope("prototype")
		public AdministrationService administrationService() {
			AdministrationServiceImpl administrationService = new AdministrationServiceImpl();
			return administrationService;
		}

		@Bean
		@Scope("prototype")
		public UserDao userDao() {
			return new UserDaoImpl();
		}
	}

	@Test
	public void testAdministrationServiceSuccessful() throws Exception {
		// when
		UserProfileTO userTO = administrationService.findUserById(1L);
		assertNotNull(userTO);
	}

	@Test
	public void shouldFindOnePersonWhenGivenLoginawoznica() throws Exception {
		// given
		String login = "awoznica";
		// when
		List<UserProfileTO> foundUsers = this.administrationService.findUsersByLogin(login);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(1, foundUsers.size());
	}

	@Test
	public void shouldFindNoPeopleWhenGivenLoginAAABBB() throws Exception {
		// given
		String login = "AAABBB";
		// when
		List<UserProfileTO> foundUsers = this.administrationService.findUsersByLogin(login);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindTwoPeopleWhenGivenNamekrzysztof() throws Exception {
		// given
		String name = "krzysztof";
		// when
		List<UserProfileTO> foundUsers = this.administrationService.findUsersByFirstName(name);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(2, foundUsers.size());
	}

	@Test
	public void shouldFindNoPeopleWhenGivenSurnameTylda() throws Exception {
		// Given
		String surname = "Tylda";
		// when
		List<UserProfileTO> foundUsers = this.administrationService.findUsersByLastName(surname);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldFindOnePersonWhenGivenSurnametylda() throws Exception {
		// given
		String surname = "tylda";
		// when
		List<UserProfileTO> foundUsers = this.administrationService.findUsersByLastName(surname);
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
		List<UserProfileTO> foundUsers = this.administrationService.findUsers(login, firstName, lastName);
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
		List<UserProfileTO> foundUsers = this.administrationService.findUsers(login, firstName, lastName);
		// then
		Assert.assertNotNull(foundUsers);
		Assert.assertEquals(0, foundUsers.size());
	}

	@Test
	public void shouldChangeFirstName() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.administrationService.findUserById(givenId);
		final String userNameOnStart = userOnStart.getName();
		// when
		this.administrationService.setUserFirstName(givenId, "aaabbb");
		UserProfileTO userAfterChange = this.administrationService.findUserById(givenId);
		Assert.assertNotEquals(userNameOnStart, userAfterChange.getName());
	}

	@Test
	public void shouldChangeLastName() throws Exception {
		// given
		long givenId = 1;
		UserProfileTO userOnStart = this.administrationService.findUserById(givenId);
		final String userLastNameOnStart = userOnStart.getSurname();
		// when
		this.administrationService.setUserLastName(givenId, "surname");
		UserProfileTO userAfterChange = this.administrationService.findUserById(givenId);
		Assert.assertNotEquals(userLastNameOnStart, userAfterChange.getName());
	}

	@Test
	public void shouldChangeEmailAddress() throws Exception {
		// given
		long givenId = 3;
		UserProfileTO userOnStart = this.administrationService.findUserById(givenId);
		final String userEmailOnStart = userOnStart.getEmail();
		// when
		this.administrationService.setUserEmailAddress(givenId, "email@email.com");
		UserProfileTO userAfterChange = this.administrationService.findUserById(givenId);
		Assert.assertNotEquals(userEmailOnStart, userAfterChange.getEmail());
	}

	@Test
	public void shouldChangeAboutMeNote() throws Exception {
		// given
		long givenId = 4;
		UserProfileTO userOnStart = this.administrationService.findUserById(givenId);
		final String userAboutMeOnStart = userOnStart.getAboutMe();
		// when
		this.administrationService.setUserAboutMeNote(givenId, "i am no one");
		UserProfileTO userAfterChange = this.administrationService.findUserById(givenId);
		Assert.assertNotEquals(userAboutMeOnStart, userAfterChange.getAboutMe());
	}

	@Test
	public void shouldChangeLifeMotto() throws Exception {
		// given
		long givenId = 2;
		UserProfileTO userOnStart = this.administrationService.findUserById(givenId);
		final String userLifeMottoOnStart = userOnStart.getLifeMotto();
		// when
		this.administrationService.setUserLifeMotto(givenId, "carpe diem");
		UserProfileTO userAfterChange = this.administrationService.findUserById(givenId);
		Assert.assertNotEquals(userLifeMottoOnStart, userAfterChange.getLifeMotto());
	}

	@Test
	public void shouldRemoveUserWithId2() throws Exception {
		// given
		long userId = 2;
		UserProfileTO userBeforeDeleting = this.administrationService.findUserById(userId);
		this.administrationService.deleteUser(userId);
		UserProfileTO userAfterDeleting = this.administrationService.findUserById(userId);
		Assert.assertNotNull(userBeforeDeleting);
		Assert.assertNull(userAfterDeleting);
	}

}