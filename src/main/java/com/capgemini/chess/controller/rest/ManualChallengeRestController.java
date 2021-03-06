package com.capgemini.chess.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.service.ManualChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.UserProfileTO;

@RestController
@ResponseBody
@RequestMapping("/rest/manualChallenge")
public class ManualChallengeRestController {
	@Autowired
	private ManualChallengeService manualChallengeService;

	/**
	 * finds user by id
	 * 
	 * @param userId
	 *            id of user
	 * @return User profile if it is found, null otherwise
	 */
	@RequestMapping(value = "/findUserById", method = RequestMethod.GET)
	public UserProfileTO findUserById(@RequestParam("id") long userId) {
		return this.manualChallengeService.findUserById(userId);
	}

	/**
	 * finds User profiles by name
	 * 
	 * @param userName
	 *            name of user
	 * @return List of users with specified name in parameter, when no such
	 *         users then empty list is returned
	 */
	@RequestMapping(value = "/findUsersByName", method = RequestMethod.GET)
	public List<UserProfileTO> findUserByName(@RequestParam("name") String userName) {
		return this.manualChallengeService.findUserByName(userName);
	}

	/**
	 * finds user profiles by surname
	 * 
	 * @param userSurname
	 *            surname of user
	 * @return List of users with specified surname in parameter, when no such
	 *         users then empty list is returned
	 */
	@RequestMapping(value = "/findUsersBySurname", method = RequestMethod.GET)
	public List<UserProfileTO> findUserBySurname(@RequestParam("surname") String userSurname) {
		return this.manualChallengeService.findUserBySurname(userSurname);
	}

	/**
	 * creates challenge (between senderId player and receiverId player
	 * contained in structure of challenge) specified as param
	 * 
	 * @param challenge
	 *            challange info
	 * @return true if challenge is created, false when it is not created
	 */
	@RequestMapping(value = "/createChallenge", method = RequestMethod.POST)
	public boolean createChallenge(@RequestBody ChallengeTO challenge) {
		final long senderPlayerId = challenge.getSenderPlayerId();
		final long receiverPlayerId = challenge.getReceiverPlayerId();
		return this.manualChallengeService.createChallenge(senderPlayerId, receiverPlayerId);
	}
}
