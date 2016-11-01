package com.capgemini.chess.dataaccess.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * interface contains method definitions to work with challengeEntities
 * 
 * @author AWOZNICA
 *
 */
public interface ChallengeDao {
	/**
	 * adds new challenge to repository
	 * 
	 * @param challenge
	 *            challenge which is added
	 * @return false when challenge is null or there was problems with adding,
	 *         true otherwise
	 */
	boolean addChallenge(ChallengeTO challenge);

	/**
	 * removes challenge with given id
	 * 
	 * @param challengeId
	 *            id of challenge to remove
	 * @return false when there is no challenge with given id or challenge was
	 *         not removed, true otherwise
	 */
	boolean removeChallengeByChallengeId(long challengeId);

	/**
	 * gets challenge with given id
	 * 
	 * @param id
	 *            id of challenge
	 * @return challengeTO object when challenge with given id was found, null
	 *         otherwise
	 */
	ChallengeTO getChallengeById(long id);

	/**
	 * gets all existing challenges
	 * 
	 * @return list of all existing challenges in repository
	 */
	List<ChallengeTO> getAllChallenges();

	/**
	 * gets all challenges with given status
	 * 
	 * @param status
	 *            status of challenges that are looked for
	 * @return list of challenges with given status
	 */
	List<ChallengeTO> getChallengesByStatus(ChallengeStatus status);

	/**
	 * gets all challenges with given sender id
	 * 
	 * @param id
	 *            sender id
	 * @return list of all challenges with given sender id
	 */
	List<ChallengeTO> getChallengesBySenderPlayerId(long id);

	/**
	 * gets all challenges with given receiver id
	 * 
	 * @param id
	 *            receiver id
	 * @return list of all challenges with given receiver id, if not challenges
	 *         found then returns empty list
	 */
	List<ChallengeTO> getChallengesByReceiverPlayerId(long id);

	/**
	 * gets all challenges in with given id is either receiverId or senderId
	 * 
	 * @param id
	 *            id of user
	 * @return returns list of challenges in which given id is receiverid or
	 *         sender id, if no challenges found then empty list
	 */
	List<ChallengeTO> getChallengesByPlayerId(long id);

	/**
	 * gets all challenges that expires before given date
	 * 
	 * @param date
	 *            date
	 * @return list of challenges that have expireDate less than given date
	 */
	List<ChallengeTO> getChallengesExpiredBefore(LocalDateTime date);

	/**
	 * sets new status for challenge with given challenge id
	 * 
	 * @param challengeId
	 *            id of challenge to which change is made
	 * @param status
	 *            new status for challenge
	 * @return false when there is no challenge with given id, true when change
	 *         is made
	 */
	boolean setChallengeStatus(long challengeId, ChallengeStatus status);

	/**
	 * sets new sender id for challenge with given challenge id
	 * 
	 * @param challengeId
	 *            id of challenge to which change is made
	 * @param senderId
	 *            new senderId for challenge
	 * @return false when there is no challenge with given id, true when change
	 *         is made
	 */
	boolean setChallengeSenderPlayerId(long challengeId, long senderId);

	/**
	 * sets new receiver id for challenge with giveb challenge id
	 * 
	 * @param challengeId
	 *            id of challenge to which change is made
	 * @param receiverId
	 *            new receiver id for challenge
	 * @return false when there is no challenge with given id, true when change
	 *         is made
	 */
	boolean setChallengeReceiverPlayerId(long challengeId, long receiverId);

	/**
	 * sets new creation date for challenge with giveb challenge id
	 * 
	 * @param challengeId
	 *            id of challenge to which change is made
	 * @param creationDate
	 *            new creation date for challenge
	 * @return false when there is no challenge with given id, true when change
	 *         is made
	 */
	boolean setChallengeCreationDate(long challengeId, LocalDateTime creationDate);

	/**
	 * sets new expire date for challenge with giveb challenge id
	 * 
	 * @param challengeId
	 *            id of challenge to which change is made
	 * @param expireDate
	 *            new expire date for challenge
	 * @return false when there is no challenge with given id, true when change
	 *         is made
	 */
	boolean setChallengeExpireDate(long challengeId, LocalDateTime expireDate);
}