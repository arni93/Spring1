package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

/**
 * interface contains business logic to execute making manual challenges between
 * two users
 * 
 * @author AWOZNICA
 *
 */
public interface ManualChallangeService {
	/**
	 * gets user with given id
	 * 
	 * @param userId
	 *            id of user that is looked for
	 * @return UserProfile with given id or null when no users with given id
	 *         found
	 */
	UserProfileTO findUserById(long userId);

	/**
	 * gets users with specified name
	 * 
	 * @param userName
	 *            name of user
	 * @return returns list of users with given name, when no users found
	 *         returns empty list
	 */
	List<UserProfileTO> findUserByName(String userName);

	/**
	 * gets users with specified name
	 * 
	 * @param userSurname
	 *            surname of user
	 * @return returns list of users with given surname, when no users found
	 *         returns empty list
	 */
	List<UserProfileTO> findUserBySurname(String userSurname);

	/**
	 * @param senderPlayerId
	 * 
	 * @param receiverPlayerId
	 * @return true when players with given Ids exist and challenge was added
	 *         correctly to repository, false otherwise
	 */
	boolean sendChallenge(long senderPlayerId, long receiverPlayerId);
}
