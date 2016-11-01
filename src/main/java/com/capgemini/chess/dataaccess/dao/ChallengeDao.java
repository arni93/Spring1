package com.capgemini.chess.dataaccess.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.to.ChallengeTO;

public interface ChallengeDao {
	boolean addChallenge(ChallengeTO challenge);

	boolean removeChallengeByChallengeId(long challengeId);

	ChallengeTO getChallengeById(long id);

	List<ChallengeTO> getAllChallenges();

	List<ChallengeTO> getChallengesByStatus(ChallengeStatus status);

	List<ChallengeTO> getChallengesBySenderPlayerId(long id);

	List<ChallengeTO> getChallengesByReceiverPlayerId(long id);

	List<ChallengeTO> getChallengesByPlayerId(long id);

	List<ChallengeTO> getExpiredChallenges();

	boolean setChallengeStatus(long challengeId, ChallengeStatus status);

	boolean setChallengeSenderPlayerId(long challengeId, long senderId);

	boolean setChallengeReceiverPlayerId(long challengeId, long receiverId);

	boolean setChallengeCreationDate(long challengeId, LocalDateTime creationDate);

	boolean setChallengeExpireDate(long challengeId, LocalDateTime expireDate);
}