package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.service.AdministrationService;
import com.capgemini.chess.service.to.UserProfileTO;

/**
 * Implementation of Administration service
 * 
 * @author AWOZNICA
 *
 */
@Service
public class AdministrationServiceImpl implements AdministrationService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserProfileTO findUserById(long userId) {
		UserProfileTO foundUser = this.userDao.findUserById(userId);
		return foundUser;
	}

	@Override
	public List<UserProfileTO> findUsersByLogin(String login) {
		List<UserProfileTO> foundUsers = this.userDao.findUsersByLogin(login);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsersByFirstName(String firstName) {
		List<UserProfileTO> foundUsers = this.userDao.findUsersByName(firstName);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsersByLastName(String lastName) {
		List<UserProfileTO> foundUsers = this.userDao.findUsersBySurname(lastName);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsers(String login, String firstName, String lastName) {
		List<UserProfileTO> foundUsers = this.userDao.findUsers(login, firstName, lastName);
		return foundUsers;
	}

	@Override
	public boolean setUserFirstName(long userId, String newFirstName) {
		boolean result = this.userDao.setUserName(userId, newFirstName);
		return result;
	}

	@Override
	public boolean setUserLastName(long userId, String newLastName) {
		boolean result = this.userDao.setUserSurname(userId, newLastName);
		return result;
	}

	@Override
	public boolean setUserEmailAddress(long userId, String newEmailAddress) {
		boolean result = this.userDao.setUserEmail(userId, newEmailAddress);
		return result;
	}

	@Override
	public boolean setUserAboutMeNote(long userId, String newAboutMeNote) {
		boolean result = this.userDao.setUserAboutMe(userId, newAboutMeNote);
		return result;
	}

	@Override
	public boolean setUserLifeMotto(long userId, String newLifeMotto) {
		boolean result = this.userDao.setUserLifeMotto(userId, newLifeMotto);
		return result;
	}

	@Override
	public boolean setUserProfile(long userId, String newFirstName, String newLastName, String newEmailAddress,
			String newAboutMeNote, String newLifeMotto) {
		boolean result = this.userDao.setUserName(userId, newFirstName);
		if (result == true) {
			this.userDao.setUserSurname(userId, newLastName);
			this.userDao.setUserEmail(userId, newEmailAddress);
			this.setUserAboutMeNote(userId, newAboutMeNote);
			this.setUserLifeMotto(userId, newLifeMotto);
		}
		return result;
	}

	@Override
	public boolean deleteUser(long userId) {
		boolean result = this.userDao.deleteUser(userId);
		return result;
	}

}
