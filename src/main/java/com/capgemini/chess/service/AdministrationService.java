package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

/**
 * interface contains business logic for user accounts administration
 * 
 * @author AWOZNICA
 *
 */

public interface AdministrationService {
	/**
	 * finds user profile by given userId
	 * 
	 * @param userId
	 *            id of user
	 * @return UserProfileTO of userId when user exists, null otherwise
	 */
	public UserProfileTO findUserById(long userId);

	/**
	 * finds users with specified user login
	 * 
	 * @param login
	 *            login of user
	 * @return list of users with given login, when no such users then empty
	 *         list is returned
	 */
	public List<UserProfileTO> findUsersByLogin(String login);

	/**
	 * finds users with specified user first name
	 * 
	 * @param firstName
	 *            name of user
	 * @return list of users with given firstName, when no such users then empty
	 *         list is returned
	 */
	public List<UserProfileTO> findUsersByFirstName(String firstName);

	/**
	 * finds users with specified user last name
	 * 
	 * @param firstName
	 *            last name of user
	 * @return list of users with given lastName, when no such users then empty
	 *         list is returned
	 */
	public List<UserProfileTO> findUsersByLastName(String lastName);

	/**
	 * finds users with given credentials
	 * 
	 * @param login
	 *            login of user
	 * @param firstName
	 *            user first name
	 * @param lastName
	 *            user last name
	 * @return list of users with given credentials, when no such users then
	 *         empty list is returned
	 */
	public List<UserProfileTO> findUsers(String login, String firstName, String lastName);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @param newFirstName
	 *            users new first name which is set
	 * @return true when user is found and change is made, false otherwise
	 */
	public boolean setUserFirstName(long userId, String newFirstName);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @param newLastName
	 *            users new last name which is set
	 * @return true when user is found and change is made, false otherwise
	 */
	boolean setUserLastName(long userId, String newLastName);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @param newEmailAddress
	 *            users new email which is set
	 * @return true when user is found and change is made, false otherwise
	 */
	boolean setUserEmailAddress(long userId, String newEmailAddress);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @param newAboutMeNote
	 *            users new aboutMeNote
	 * @return true when user is found and change is made, false otherwise
	 */
	boolean setUserAboutMeNote(long userId, String newAboutMeNote);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @param newLifeMotto
	 *            users new LifeMotto
	 * @return true when user is found and change is made, false otherwise
	 */
	boolean setUserLifeMotto(long userId, String newLifeMotto);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @param newFirstName
	 * @param newLastName
	 * @param newEmailAddress
	 * @param newAboutMeNote
	 * @param newLifeMotto
	 * @return true when user is found and change is made, false otherwise
	 */
	boolean setUserProfile(long userId, String newFirstName, String newLastName, String newEmailAddress,
			String newAboutMeNote, String newLifeMotto);

	/**
	 * @param userId
	 *            user id for which setting is made
	 * @return true when user is found and deleted, false when user did not
	 *         exist or exist but there were problems to remove it
	 */
	boolean deleteUser(long userId);
}
