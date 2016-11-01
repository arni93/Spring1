package com.capgemini.chess.dataaccess.dao;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

public interface UserDao {
	boolean addUser(UserProfileTO userProfile);

	boolean setUserLogin(long userId, String newLogin);

	boolean setUserPassword(long userId, String newPassword);

	boolean setUserName(long userId, String newUserName);

	boolean setUserSurname(long userId, String newUserSurname);

	boolean setUserEmail(long userId, String newUserEmail);

	boolean setUserAboutMe(long userId, String newUserAboutMe);

	boolean setUserLifeMotto(long userId, String newUserLifeMotto);

	UserProfileTO findUserById(long userId);

	List<UserProfileTO> findUsersByLogin(String login);

	List<UserProfileTO> findUsersByName(String userName);

	List<UserProfileTO> findUsersBySurname(String userSurname);

	List<UserProfileTO> findUsers(String login, String userName, String surname);

	List<UserProfileTO> getAllUsers();

	boolean deleteUser(long deletedUserId);
}
