package com.capgemini.chess.dataaccess.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.mapper.ChallengeMapper;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Challenge data access object interface implementation. It is a stub to test
 * services
 * 
 * @author AWOZNICA
 *
 */
public class ChallengeDaoImpl implements ChallengeDao {
	private long nextId = 0;
	private final List<ChallengeEntity> challengeList = new ArrayList<>();

	public ChallengeDaoImpl() {
		this.addStubs();
	}

	@Override
	public boolean addChallenge(ChallengeTO challenge) {
		long id = this.nextId++;
		if (challenge == null) {
			return false;
		}
		challenge.setChallengeId(id);
		ChallengeEntity challengeEntity = ChallengeMapper.map(challenge);
		boolean addResult = this.challengeList.add(challengeEntity);
		return addResult;
	}

	@Override
	public boolean removeChallengeByChallengeId(long challengeId) {
		int entityPosition = this.findChallengePositionById(challengeId);
		boolean result = false;
		if (entityPosition >= 0) {
			this.challengeList.remove(entityPosition);
			result = true;
		}
		return result;
	}

	@Override
	public ChallengeTO getChallengeById(long id) {
		int entityPosition = this.findChallengePositionById(id);
		if (entityPosition < 0) {
			return null;
		}
		ChallengeEntity challengeEntity = this.challengeList.get(entityPosition);
		ChallengeTO challengeTO = ChallengeMapper.map(challengeEntity);
		return challengeTO;
	}

	@Override
	public List<ChallengeTO> getAllChallenges() {
		List<ChallengeTO> mappedEntities = ChallengeMapper.map2TOs(this.challengeList);
		return mappedEntities;
	}

	@Override
	public List<ChallengeTO> getChallengesByStatus(ChallengeStatus status) {
		List<ChallengeEntity> challengeList = this.challengeList.stream().filter(p -> p.getChallengeStatus() == status)
				.collect(Collectors.toList());
		List<ChallengeTO> mappedChallenges = ChallengeMapper.map2TOs(challengeList);
		return mappedChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesBySenderPlayerId(long id) {
		List<ChallengeEntity> challengeList = this.challengeList.stream().filter(p -> p.getSenderPlayerId() == id)
				.collect(Collectors.toList());
		List<ChallengeTO> mappedChallenges = ChallengeMapper.map2TOs(challengeList);
		return mappedChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesByReceiverPlayerId(long id) {
		List<ChallengeEntity> challengeList = this.challengeList.stream().filter(p -> p.getReceiverPlayerId() == id)
				.collect(Collectors.toList());
		List<ChallengeTO> mappedChallenges = ChallengeMapper.map2TOs(challengeList);
		return mappedChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesByPlayerId(long id) {
		List<ChallengeEntity> challengeList = this.challengeList.stream()
				.filter(p -> ((p.getSenderPlayerId() == id) || (p.getReceiverPlayerId() == id)))
				.collect(Collectors.toList());
		List<ChallengeTO> mappedChallenges = ChallengeMapper.map2TOs(challengeList);
		return mappedChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesExpiredBefore(LocalDateTime date) {
		List<ChallengeEntity> challengeList = this.challengeList.stream()
				.filter(p -> (date.compareTo(p.getExpireDate()) > 0)).collect(Collectors.toList());
		List<ChallengeTO> mappedChallenges = ChallengeMapper.map2TOs(challengeList);
		return mappedChallenges;
	}

	@Override
	public boolean setChallengeStatus(long challengeId, ChallengeStatus status) {
		int position = this.findChallengePositionById(challengeId);
		if (position < 0) {
			return false;
		}
		ChallengeEntity challengeEntity = this.challengeList.get(position);
		challengeEntity.setChallengeStatus(status);
		return true;
	}

	@Override
	public boolean setChallengeSenderPlayerId(long challengeId, long senderId) {
		int position = this.findChallengePositionById(challengeId);
		if (position < 0) {
			return false;
		}
		ChallengeEntity challengeEntity = this.challengeList.get(position);
		challengeEntity.setSenderPlayerId(senderId);
		return true;
	}

	@Override
	public boolean setChallengeReceiverPlayerId(long challengeId, long receiverId) {
		int position = this.findChallengePositionById(challengeId);
		if (position < 0) {
			return false;
		}
		ChallengeEntity challengeEntity = this.challengeList.get(position);
		challengeEntity.setReceiverPlayerId(receiverId);
		return true;
	}

	@Override
	public boolean setChallengeCreationDate(long challengeId, LocalDateTime creationDate) {
		int position = this.findChallengePositionById(challengeId);
		if (position < 0) {
			return false;
		}
		ChallengeEntity challengeEntity = this.challengeList.get(position);
		challengeEntity.setCreationDate(creationDate);
		return true;
	}

	@Override
	public boolean setChallengeExpireDate(long challengeId, LocalDateTime expireDate) {
		int position = this.findChallengePositionById(challengeId);
		if (position < 0) {
			return false;
		}
		ChallengeEntity challengeEntity = this.challengeList.get(position);
		challengeEntity.setExpireDate(expireDate);
		return true;
	}

	private ChallengeEntity createChallengeEntity(long senderId, long receiverId, ChallengeStatus status,
			LocalDateTime startDate, LocalDateTime expireDate) {
		ChallengeEntity createdChallenge = new ChallengeEntity();
		createdChallenge.setChallengeId(this.nextId++);
		createdChallenge.setSenderPlayerId(senderId);
		createdChallenge.setReceiverPlayerId(receiverId);
		createdChallenge.setChallengeStatus(status);
		createdChallenge.setCreationDate(startDate);
		createdChallenge.setExpireDate(expireDate);
		return createdChallenge;
	}

	private void addStubs() {
		LocalDateTime startDate1 = LocalDateTime.of(2000, 10, 1, 10, 0);
		LocalDateTime expireDate1 = LocalDateTime.of(2000, 11, 1, 10, 0);
		LocalDateTime startDate2 = LocalDateTime.of(2001, 10, 1, 10, 0);
		LocalDateTime expireDate2 = LocalDateTime.of(2001, 11, 1, 10, 0);
		challengeList.add(createChallengeEntity(1, 10, ChallengeStatus.ACCEPTED, startDate1, expireDate1));
		challengeList.add(createChallengeEntity(2, 20, ChallengeStatus.ACCEPTED, startDate1, expireDate1));
		challengeList.add(createChallengeEntity(1, 11, ChallengeStatus.SEND, startDate1, expireDate1));
		challengeList.add(createChallengeEntity(3, 31, ChallengeStatus.SEND, startDate1, expireDate1));
		challengeList.add(createChallengeEntity(3, 32, ChallengeStatus.SEND, startDate1, expireDate1));
		challengeList.add(createChallengeEntity(3, 30, ChallengeStatus.DECLINED, startDate1, expireDate1));
		challengeList.add(createChallengeEntity(4, 1, ChallengeStatus.SEND, startDate2, expireDate2));
		challengeList.add(createChallengeEntity(5, 1, ChallengeStatus.SEND, startDate2, expireDate2));
		challengeList.add(createChallengeEntity(6, 1, ChallengeStatus.SEND, startDate2, expireDate2));
	}

	private int findChallengePositionById(long challengeId) {
		for (int iter = 0; iter < this.challengeList.size(); iter++) {
			ChallengeEntity challengeEntity = challengeList.get(iter);
			if (challengeEntity.getChallengeId() == challengeId) {
				return iter;
			}
		}
		return -1;
	}

}
