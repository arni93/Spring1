package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.AdministrationDao;
import com.capgemini.chess.service.AdministrationService;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class AdministrationServiceImpl implements AdministrationService {
	@Autowired
	private AdministrationDao administrationDao;

	public AdministrationServiceImpl() {
	}

	@Override
	public UserProfileTO findUserById(long userId) {
		UserProfileTO foundUser = this.administrationDao.findUserById(userId);
		return foundUser;
	}

	@Override
	public List<UserProfileTO> findUsersByLogin(String login) {
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByLogin(login);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsersByFirstName(String firstName) {
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByFirstName(firstName);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsersByLastName(String lastName) {
		List<UserProfileTO> foundUsers = this.administrationDao.findUsersByLastName(lastName);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsers(String login, String firstName, String lastName) {
		List<UserProfileTO> foundUsers = this.administrationDao.findUsers(login, firstName, lastName);
		return foundUsers;
	}

	@Override
	public boolean setUserFirstName(long userId, String newFirstName) {
		boolean result = this.administrationDao.setUserFirstName(userId, newFirstName);
		return result;
	}

	@Override
	public boolean setUserLastName(long userId, String newLastName) {
		boolean result = this.administrationDao.setUserLastName(userId, newLastName);
		return result;
	}

	@Override
	public boolean setUserEmailAddress(long userId, String newEmailAddress) {
		boolean result = this.administrationDao.setUserEmailAddress(userId, newEmailAddress);
		return result;
	}

	@Override
	public boolean setUserAboutMeNote(long userId, String newAboutMeNote) {
		boolean result = this.administrationDao.setUserAboutMeNote(userId, newAboutMeNote);
		return result;
	}

	@Override
	public boolean setUserLifeMotto(long userId, String newLifeMotto) {
		boolean result = this.administrationDao.setUserLifeMotto(userId, newLifeMotto);
		return result;
	}

	@Override
	public boolean setUserProfile(long userId, String newFirstName, String newLastName, String newEmailAddress,
			String newAboutMeNote, String newLifeMotto) {
		boolean result = this.administrationDao.setUserProfile(userId, newFirstName, newLastName, newEmailAddress,
				newAboutMeNote, newLifeMotto);
		return result;
	}

	@Override
	public boolean deleteUser(long userId) {
		boolean result = this.administrationDao.deleteUser(userId);
		return result;
	}

}
