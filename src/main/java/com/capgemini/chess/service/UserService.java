package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

/**
 * interface contains business logic to work with userProfiles
 * 
 * @author AWOZNICA
 * 
 */
public interface UserService {
	/**
	 * gets user with given user id
	 * 
	 * @param userId
	 *            id of user
	 * @return userProfileTO when there was user with given id, null otherwise
	 */
	public UserProfileTO findUserById(long userId);

	/**
	 * gets users with given login
	 * 
	 * @param login
	 *            login of user
	 * @return list of users with given login, when no users with given login
	 *         found returns empty list
	 */
	public List<UserProfileTO> findUsersByLogin(String login);

	/**
	 * gets users with given name
	 * 
	 * @param userName
	 *            name of user
	 * @return list of users with given name, when no users with given name
	 *         found returns empty list
	 */
	public List<UserProfileTO> findUsersByName(String userName);

	/**
	 * gets users with given surname
	 * 
	 * @param userSurname
	 *            surname of user
	 * @return list of users with given surname, when no users with given
	 *         surname found returns empty list
	 */
	public List<UserProfileTO> findUsersBySurname(String userSurname);

	/**
	 * gets users with given login, name and surname
	 * 
	 * @param login
	 *            user login
	 * @param userName
	 *            user name
	 * @param surname
	 *            user surname
	 * @return list of users that match criteria, when no users with given
	 *         criteria found returns empty list
	 */
	public List<UserProfileTO> findUsers(String login, String userName, String surname);

	/**
	 * gets user with given user id
	 * 
	 * @param userId
	 *            id of user
	 * @return userProfileTO when there was user with given id, null otherwise
	 */
	public UserProfileTO readUser(Long id);
}
