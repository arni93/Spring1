package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTO;

public class ChallengeMapper {
	public static ChallengeTO map(ChallengeEntity entity) {
		if (entity != null) {
			ChallengeTO challengeTO = new ChallengeTO();
			challengeTO.setChallengeId(entity.getChallengeId());
			challengeTO.setChallengeStatus(entity.getChallengeStatus());
			challengeTO.setCreationDate(entity.getCreationDate());
			challengeTO.setExpireDate(entity.getExpireDate());
			challengeTO.setReceiverPlayerId(entity.getReceiverPlayerId());
			challengeTO.setSenderPlayerId(entity.getSenderPlayerId());
			return challengeTO;
		}
		return null;
	}

	public static ChallengeEntity map(ChallengeTO challengeTO) {
		if (challengeTO != null) {
			ChallengeEntity challengeEntity = new ChallengeEntity();
			challengeEntity.setChallengeId(challengeTO.getChallengeId());
			challengeEntity.setChallengeStatus(challengeTO.getChallengeStatus());
			challengeEntity.setCreationDate(challengeTO.getCreationDate());
			challengeEntity.setExpireDate(challengeTO.getExpireDate());
			challengeEntity.setReceiverPlayerId(challengeTO.getReceiverPlayerId());
			challengeEntity.setSenderPlayerId(challengeTO.getSenderPlayerId());
			return challengeEntity;
		}
		return null;
	}

	public static ChallengeEntity update(ChallengeEntity challengeEntity, ChallengeTO challengeTO) {
		if (challengeEntity != null && challengeTO != null) {
			challengeEntity.setChallengeId(challengeTO.getChallengeId());
			challengeEntity.setChallengeStatus(challengeTO.getChallengeStatus());
			challengeEntity.setCreationDate(challengeTO.getCreationDate());
			challengeEntity.setExpireDate(challengeTO.getExpireDate());
			challengeEntity.setReceiverPlayerId(challengeTO.getReceiverPlayerId());
			challengeEntity.setSenderPlayerId(challengeTO.getSenderPlayerId());
		}
		return challengeEntity;
	}

	public static List<ChallengeTO> map2TOs(List<ChallengeEntity> challengeEntities) {
		return challengeEntities.stream().map(ChallengeMapper::map).collect(Collectors.toList());
	}

	public static List<ChallengeEntity> map2Entities(List<ChallengeTO> challengeTOs) {
		return challengeTOs.stream().map(ChallengeMapper::map).collect(Collectors.toList());
	}
}
