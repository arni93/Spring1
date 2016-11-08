package com.capgemini.chess.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.ManualChallangeService;
import com.capgemini.chess.service.UserService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.UserProfileTO;

/**
 * Implementation of ManualChallangeService
 * 
 * @author AWOZNICA
 *
 */
@Service
public class ManualChallengeServiceImpl implements ManualChallangeService {
	@Autowired
	private UserService userService;

	@Autowired
	private ChallengeDao challengeDao;

	@Override
	public UserProfileTO findUserById(long userId) {
		UserProfileTO foundUser = this.userService.findUserById(userId);
		return foundUser;
	}

	@Override
	public List<UserProfileTO> findUserByName(String userName) {
		List<UserProfileTO> foundUsers = this.userService.findUsersByName(userName);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUserBySurname(String userSurname) {
		List<UserProfileTO> foundUsers = this.userService.findUsersBySurname(userSurname);
		return foundUsers;
	}

	@Override
	public boolean createChallenge(long senderPlayerId, long receiverPlayerId) {
		UserProfileTO sender = userService.findUserById(senderPlayerId);
		UserProfileTO receiver = userService.findUserById(receiverPlayerId);
		if (sender != null && receiver != null) {
			ChallengeTO challengeTO = new ChallengeTO();
			challengeTO.setSenderPlayerId(senderPlayerId);
			challengeTO.setReceiverPlayerId(receiverPlayerId);
			challengeTO.setCreationDate(LocalDateTime.now());
			challengeTO.setExpireDate(LocalDateTime.now().plusMonths(1));
			challengeTO.setChallengeStatus(ChallengeStatus.SEND);
			boolean addChallengeResult = this.challengeDao.addChallenge(challengeTO);
			return addChallengeResult;
		}
		return false;
	}

}
