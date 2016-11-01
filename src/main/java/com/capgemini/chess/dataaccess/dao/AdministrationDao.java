package com.capgemini.chess.dataaccess.dao;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

public interface AdministrationDao {
	UserProfileTO findUserById(long userId);

	List<UserProfileTO> findUsersByLogin(String userLogin);

	List<UserProfileTO> findUsersByFirstName(String firstName);

	List<UserProfileTO> findUsersByLastName(String lastName);

	List<UserProfileTO> findUsers(String login, String firstName, String lastName);

	boolean setUserFirstName(long userId, String newFirstName);

	boolean setUserLastName(long userId, String newLastName);

	boolean setUserEmailAddress(long userId, String newEmailAddress);

	boolean setUserAboutMeNote(long userId, String newAboutMeNote);

	boolean setUserLifeMotto(long userId, String newLifeMotto);

	boolean setUserProfile(long userId, String newFirstName, String newLastName, String newEmailAddress,
			String newAboutMeNote, String newLifeMotto);

	boolean deleteUser(long deletedUserId);

}
