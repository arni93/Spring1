package com.capgemini.chess.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.FilterChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Implementation of FilterChallengeService Interface
 * 
 * @author AWOZNICA
 *
 */
@Service
public class FilterChallengeServiceImpl implements FilterChallengeService {

	@Autowired
	private ChallengeDao challengeDao;

	@Override
	public List<ChallengeTO> getChallengesFilteredBy(long senderId, long receiverId) {
		List<ChallengeTO> senderIdChallenges = this.challengeDao.getChallengesBySenderPlayerId(senderId);
		List<ChallengeTO> receiverIdChallenges = this.challengeDao.getChallengesByReceiverPlayerId(receiverId);
		List<ChallengeTO> commonChallenges = new ArrayList<>(senderIdChallenges);
		commonChallenges.retainAll(receiverIdChallenges);
		return commonChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesFilteredBy(long senderId, long receiverId, ChallengeStatus status) {
		List<ChallengeTO> senderIdChallenges = this.challengeDao.getChallengesBySenderPlayerId(senderId);
		List<ChallengeTO> receiverIdChallenges = this.challengeDao.getChallengesByReceiverPlayerId(receiverId);
		List<ChallengeTO> statusChallenges = this.challengeDao.getChallengesByStatus(status);
		List<ChallengeTO> commonChallenges = new ArrayList<>(senderIdChallenges);
		commonChallenges.retainAll(receiverIdChallenges);
		commonChallenges.retainAll(statusChallenges);
		return commonChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesFilteredBy(long playerId, ChallengeStatus status) {
		List<ChallengeTO> playerIdChallenges = this.challengeDao.getChallengesByPlayerId(playerId);
		List<ChallengeTO> statusChallenges = this.challengeDao.getChallengesByStatus(status);
		List<ChallengeTO> commonChallenges = new ArrayList<>(playerIdChallenges);
		commonChallenges.retainAll(statusChallenges);
		return commonChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengesFilteredBy(ChallengeStatus status) {
		List<ChallengeTO> statusChallenges = this.challengeDao.getChallengesByStatus(status);
		return statusChallenges;
	}

	@Override
	public List<ChallengeTO> getChallengeFilteredBy(Map<String, List<String>> filtersMap) {
		List<ChallengeTO> commonChallenges = this.challengeDao.getAllChallenges();
		Set<String> keySet = filtersMap.keySet();
		retainIds(filtersMap, commonChallenges, keySet);
		retainStatus(filtersMap, commonChallenges, keySet);
		retainDates(filtersMap, commonChallenges, keySet);
		return commonChallenges;
	}

	private void retainDates(Map<String, List<String>> filtersMap, List<ChallengeTO> commonChallenges,
			Set<String> keySet) {
		if (keySet.contains("createdBefore")) {
			List<String> dates = filtersMap.get("createdBefore");
			LocalDateTime date = LocalDateTime.parse(dates.get(0));
			List<ChallengeTO> challengesCreatedBefore = this.challengeDao.getChallengesCreatedBefore(date);
			commonChallenges.retainAll(challengesCreatedBefore);
		}
		if (keySet.contains("createdAfter")) {
			List<String> dates = filtersMap.get("createdAfter");
			LocalDateTime date = LocalDateTime.parse(dates.get(0));
			List<ChallengeTO> challengesCreatedAfter = this.challengeDao.getChallengesCreatedAfter(date);
			commonChallenges.retainAll(challengesCreatedAfter);
		}
		if (keySet.contains("expiredBefore")) {
			List<String> dates = filtersMap.get("expiredBefore");
			LocalDateTime date = LocalDateTime.parse(dates.get(0));
			List<ChallengeTO> challengesExpiredBefore = this.challengeDao.getChallengesExpiredBefore(date);
			commonChallenges.retainAll(challengesExpiredBefore);
		}
		if (keySet.contains("expiredAfter")) {
			List<String> dates = filtersMap.get("expiredAfter");
			LocalDateTime date = LocalDateTime.parse(dates.get(0));
			List<ChallengeTO> challengesExpiredAfter = this.challengeDao.getChallengesExpiredAfter(date);
			commonChallenges.retainAll(challengesExpiredAfter);
		}
	}

	private void retainStatus(Map<String, List<String>> filtersMap, List<ChallengeTO> commonChallenges,
			Set<String> keySet) {
		if (keySet.contains("status")) {
			List<String> stats = filtersMap.get("status");
			ChallengeStatus status = ChallengeStatus.valueOf(stats.get(0));
			List<ChallengeTO> challengesByStatus = this.challengeDao.getChallengesByStatus(status);
			commonChallenges.retainAll(challengesByStatus);
		}
	}

	private void retainIds(Map<String, List<String>> filtersMap, List<ChallengeTO> commonChallenges,
			Set<String> keySet) {
		if (keySet.contains("playerId")) {
			List<String> ids = filtersMap.get("playerId");
			Long playerId = Long.parseLong(ids.get(0));
			List<ChallengeTO> challengesByPlayerId = this.challengeDao.getChallengesByPlayerId(playerId);
			commonChallenges.retainAll(challengesByPlayerId);
		}
		if (keySet.contains("senderId")) {
			List<String> ids = filtersMap.get("senderId");
			Long senderId = Long.parseLong(ids.get(0));
			List<ChallengeTO> challengesBySenderId = this.challengeDao.getChallengesBySenderPlayerId(senderId);
			commonChallenges.retainAll(challengesBySenderId);
		}
		if (keySet.contains("receiverId")) {
			List<String> ids = filtersMap.get("receiverId");
			Long receiverId = Long.parseLong(ids.get(0));
			List<ChallengeTO> challengesByReceiverId = this.challengeDao.getChallengesByReceiverPlayerId(receiverId);
			commonChallenges.retainAll(challengesByReceiverId);
		}
	}

}
