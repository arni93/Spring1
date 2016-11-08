package com.capgemini.chess.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.service.ManualChallangeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.UserProfileTO;

@RestController
@RequestMapping("/manualChallenge")
public class ManualChallengeRestController {
	@Autowired
	private ManualChallangeService manualChallengeService;

	@ResponseBody
	@RequestMapping(value = "/findUserById", method = RequestMethod.GET)
	public UserProfileTO findUserById(@RequestParam("id") long userId) {
		return this.manualChallengeService.findUserById(userId);
	}

	@ResponseBody
	@RequestMapping(value = "/findUsersByName", method = RequestMethod.GET)
	public List<UserProfileTO> findUserByName(@RequestParam("name") String userName) {
		return this.manualChallengeService.findUserByName(userName);
	}

	@ResponseBody
	@RequestMapping(value = "/findUsersBySurname", method = RequestMethod.GET)
	public List<UserProfileTO> findUserBySurname(@RequestParam("surname") String userSurname) {
		return this.manualChallengeService.findUserBySurname(userSurname);
	}

	@ResponseBody
	@RequestMapping(value = "/createChallenge", method = RequestMethod.POST)
	public boolean createChallenge(@RequestBody ChallengeTO challenge) {
		final long senderPlayerId = challenge.getSenderPlayerId();
		final long receiverPlayerId = challenge.getReceiverPlayerId();
		return this.manualChallengeService.createChallenge(senderPlayerId, receiverPlayerId);
	}
}
