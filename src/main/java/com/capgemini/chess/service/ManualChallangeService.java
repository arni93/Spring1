package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

public interface ManualChallangeService {
	UserProfileTO findUserById(long userId);

	List<UserProfileTO> findUserByName(String userName);

	List<UserProfileTO> findUserBySurname(String userSurname);

	boolean createChallenge(long senderPlayerId, long receiverPlayerId);
}
