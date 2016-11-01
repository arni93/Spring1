package com.capgemini.chess.dataaccess.dao;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

/**
 * Interface declares methods to work with UserEntities
 * 
 * @author AWOZNICA
 *
 */
public interface UserDao {

	/**
	 * adds new user to repository
	 * 
	 * @param userProfile
	 *            new user Data
	 * @return false when userProfile is null or could not add new user, true
	 *         otherwise
	 */
	boolean addUser(UserProfileTO userProfile);

	/**
	 * set new login for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newLogin
	 *            new login for user
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserLogin(long userId, String newLogin);

	/**
	 * sets new password for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newPassword
	 *            new password for user
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserPassword(long userId, String newPassword);

	/**
	 * sets new user name for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newUserName
	 *            new user name for user
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserName(long userId, String newUserName);

	/**
	 * sets new user name for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newUserSurname
	 *            new surname for user
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserSurname(long userId, String newUserSurname);

	/**
	 * sets new user email for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newUserEmail
	 *            new user email for user
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserEmail(long userId, String newUserEmail);

	/**
	 * sets new info about user for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newUserAboutMe
	 *            new about user info note
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserAboutMe(long userId, String newUserAboutMe);

	/**
	 * sets new life motto for userEntity defined by userId
	 * 
	 * @param userId
	 *            id of userEntity to which the change is made
	 * @param newUserLifeMotto
	 *            new user life motto
	 * @return true when setting ended correctly, false when there is no
	 *         userEntity with given userId
	 */
	boolean setUserLifeMotto(long userId, String newUserLifeMotto);

	/**
	 * finds userProfile by given userId
	 * 
	 * @param userId
	 *            id of user entity
	 * @return returns UserProfile of searched user when found, otherwise null
	 */
	UserProfileTO findUserById(long userId);

	/**
	 * finds users with given login
	 * 
	 * @param login
	 *            user login
	 * @return return list of found users, when no user found returns empty list
	 */
	List<UserProfileTO> findUsersByLogin(String login);

	/**
	 * finds users with given name
	 * 
	 * @param userName
	 *            user name
	 * @return return list of found users, when no user found returns empty list
	 */
	List<UserProfileTO> findUsersByName(String userName);

	/**
	 * finds users with given surname
	 * 
	 * @param userSurname
	 *            user surname
	 * @return return list of found users, when no user found returns empty list
	 */
	List<UserProfileTO> findUsersBySurname(String userSurname);

	/**
	 * finds users with given login, name and surname
	 * 
	 * @param login
	 *            user login
	 * @param userName
	 *            user name
	 * @param surname
	 *            user surname
	 * @return return list of found users, when no user found returns empty list
	 */
	List<UserProfileTO> findUsers(String login, String userName, String surname);

	/**
	 * gets all users
	 * 
	 * @return returns list of all users
	 */
	List<UserProfileTO> getAllUsers();

	/**
	 * deletes user with given id from repository
	 * 
	 * @param deletedUserId
	 * @return true when deletion was made correctly, false when deletion made
	 *         not correctly or user with given id was not found
	 */
	boolean deleteUser(long deletedUserId);
}
