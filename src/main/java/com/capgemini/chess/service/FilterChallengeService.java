package com.capgemini.chess.service;

import java.util.List;
import java.util.Map;

import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Interface to define methods to filter challenges in service layer
 * 
 * @author AWOZNICA
 *
 */
public interface FilterChallengeService {
	/**
	 * gets all challenges in which sender has senderId and receiver has
	 * receiverId
	 * 
	 * @param senderId
	 *            id of sender player
	 * @param receiverId
	 *            id of receiver player
	 * @return list with all challenges in which sender has senderId and
	 *         receiver has receiver id, when no such challenges then empty list
	 *         is returned
	 */
	public List<ChallengeTO> getChallengesFilteredBy(long senderId, long receiverId);

	/**
	 * gets all challenges in which sender has sender id, receiver has
	 * receiverId and challengeStatus is set to status
	 * 
	 * @param senderId
	 *            id of sender player
	 * @param receiverId
	 *            id of receiver player
	 * @param status
	 *            status of challenge
	 * @return list with all challenges in which sender has senderId and
	 *         receiver has receiver id ,and status of challenge is set to
	 *         status Enum, when no such challenges then empty list is returned
	 */
	public List<ChallengeTO> getChallengesFilteredBy(long senderId, long receiverId, ChallengeStatus status);

	/**
	 * gets all challenges in which sender or receiver has playerId and
	 * challengeStatus is set to status
	 * 
	 * @param playerId
	 *            id of player(sender or receiver)
	 * @param status
	 *            status of challenge
	 * @return list with all challenges in which sender or receiver has player
	 *         id, and ChallengeStatus is set to status, when no such challenges
	 *         then empty list is returned
	 * 
	 */
	public List<ChallengeTO> getChallengesFilteredBy(long playerId, ChallengeStatus status);

	/**
	 * gets all challenges in which challengeStatus is set to status
	 * 
	 * @param status
	 *            status of challenge
	 * @return list of all challenges in which challengeStatus is set to status,
	 *         when no such challenges then empty list is returned
	 */
	public List<ChallengeTO> getChallengesFilteredBy(ChallengeStatus status);

	/**
	 * @param filtersMap
	 *            map of filters used to filter challenges. Allowed filters are:
	 *            status(ChallengeStatus), senderId(long), receiverId(long),
	 *            playerId(long), createdBefore(LocalDateTime),
	 *            createdAfter(LocalDateTime), expiredBefore(LocalDateTime),
	 *            expiredAfter(LocalDateTime)
	 * @return List of all challenges that fulfill filters from filtersMap, if
	 *         no such challenges then empty list is returned
	 */
	public List<ChallengeTO> getChallengeFilteredBy(Map<String, List<String>> filtersMap);

}
