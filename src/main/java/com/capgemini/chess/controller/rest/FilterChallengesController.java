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

@Controller
@ResponseBody
@RequestMapping("/rest/filterChallenges")
public class FilterChallengesController {
	@Autowired
	private FilterChallengeService filterChallengeService;

	@RequestMapping(value = "/byPlayerIds", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam long senderId,
			@RequestParam long receiverId) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId,
				receiverId);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byPlayerIds/{senderId}/{receiverId}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("senderId") long senderId,
			@PathVariable("receiverId") long receiverId) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId,
				receiverId);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byPlayerIdsAndStatus", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam long senderId,
			@RequestParam long receiverId, @RequestParam ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId, receiverId,
				status);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byPlayerIdsAndStatus/{senderId}/{receiverId}/{status}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("senderId") long senderId,
			@PathVariable("receiverId") long receiverId, @PathVariable("status") ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(senderId, receiverId,
				status);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byStatusAndPlayerId", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam long playerId,
			@RequestParam ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(playerId, status);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byStatusAndPlayerId/{playerId}/{status}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("playerId") long playerId,
			@PathVariable("status") ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(playerId, status);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byStatus", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(status);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byStatus/{status}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByPathVariables(@PathVariable("status") ChallengeStatus status) {
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(status);
		return filteredChallenges;
	}

	@RequestMapping(value = "/byParams/{params}", method = RequestMethod.GET)
	public List<ChallengeTO> getChallengesFilteredByCriteria(
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filters) {
		List<ChallengeTO> filteredChallenge = this.filterChallengeService.getChallengeFilteredBy(filters);
		return filteredChallenge;
	}
}
