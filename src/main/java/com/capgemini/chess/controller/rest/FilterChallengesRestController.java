package com.capgemini.chess.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.FilterChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Rest controller to perform operations on filtering challenges
 * 
 * @author AWOZNICA
 *
 */
@Controller
@ResponseBody
@RequestMapping("/rest/filterChallenges")
public class FilterChallengesRestController {
	@Autowired
	private FilterChallengeService filterChallengeService;

	@RequestMapping(value = "/byPlayerIds", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam long senderId,
			@RequestParam long receiverId) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId,
				receiverId);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param senderId
	 *            id of sender player
	 * @param receiverId
	 *            id of receiver player
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byPlayerIds/{senderId}/{receiverId}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("senderId") long senderId,
			@PathVariable("receiverId") long receiverId) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId,
				receiverId);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param senderId
	 *            id of sender player
	 * @param receiverId
	 *            id of receiver player
	 * @param status
	 *            challenge status
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byPlayerIdsAndStatus", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam long senderId,
			@RequestParam long receiverId, @RequestParam ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId, receiverId,
				status);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param senderId
	 *            id of sender player
	 * @param receiverId
	 *            id of receiver player
	 * @param status
	 *            challenge status
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byPlayerIdsAndStatus/{senderId}/{receiverId}/{status}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("senderId") long senderId,
			@PathVariable("receiverId") long receiverId, @PathVariable("status") ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId, receiverId,
				status);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param playerId
	 *            id of player (both sender and receiver)
	 * @param status
	 *            challenge status
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byStatusAndPlayerId", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam long playerId,
			@RequestParam ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(playerId, status);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param playerId
	 *            id of player (both sender and receiver)
	 * @param status
	 *            challenge status
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byStatusAndPlayerId/{playerId}/{status}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("playerId") long playerId,
			@PathVariable("status") ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(playerId, status);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param status
	 *            challenge status
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byStatus", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(status);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param status
	 *            challenge status
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byStatus/{status}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("status") ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(status);
		return filteredChallenges;
	}

	/**
	 * gets all challenges with specified parameters
	 * 
	 * @param filters
	 *            filters that can be used for filter challenges, @see
	 *            FilterChallengeService
	 * 
	 * @return List of challenges that match specified parameters, if no such
	 *         challenges then null is returned
	 */
	@RequestMapping(value = "/byParams/{params}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByCriteria(
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filters) {
		List<ChallengeTO> filteredChallenge = this.filterChallengeService.getChallengeFilteredBy(filters);
		return filteredChallenge;
	}
}
