package com.capgemini.chess.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.impl.ChallengeDaoImpl;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.impl.FilterChallengeServiceImpl;
import com.capgemini.chess.service.to.ChallengeTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FilterChallengeServiceTest {
	@Autowired
	private FilterChallengeService filterChallengeService;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		public FilterChallengeService filterChallengeService() {
			return new FilterChallengeServiceImpl();
		}

		@Bean
		public ChallengeDao challengeDao() {
			return new ChallengeDaoImpl();
		}
	}

	@Test
	public void shouldReturnOneChallengeWhenGivenSenderId1AndReceiverId10() throws Exception {
		// given
		final int expectedChallengeQuantity = 1;
		final int givenSenderId = 1;
		final int givenReceiverId = 10;
		// when
		List<ChallengeTO> resultChallenges = this.filterChallengeService.getChallengesFilteredBy(givenSenderId,
				givenReceiverId);
		final int actualChallengeQuantity = resultChallenges.size();
		// then
		Assert.assertNotNull(resultChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnZeroChallengesWhenGivenSenderId1AndReceiverId100000() throws Exception {
		// given
		final int expectedChallengeQuantity = 0;
		final int givenSenderId = 1;
		final int givenReceiverId = 100000;
		// when
		List<ChallengeTO> resultChallenges = this.filterChallengeService.getChallengesFilteredBy(givenSenderId,
				givenReceiverId);
		final int actualChallengeQuantity = resultChallenges.size();
		// then
		Assert.assertNotNull(resultChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnOneChallengeWhenGivenSenderId1AndReceiverId10AndStatusACCEPTED() throws Exception {
		// given
		final int expectedChallengeQuantity = 1;
		final int givenSenderId = 1;
		final int givenReceiverId = 10;
		final ChallengeStatus givenStatus = ChallengeStatus.ACCEPTED;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenSenderId,
				givenReceiverId, givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnZeroChallengesWhenGivenSenderId1AndReceiverId10AndStatusCANCELED() throws Exception {
		// given
		final int expectedChallengeQuantity = 0;
		final int givenSenderId = 1;
		final int givenReceiverId = 10;
		final ChallengeStatus givenStatus = ChallengeStatus.CANCELED;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenSenderId,
				givenReceiverId, givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnFourChallengesWhenGivenPlayerId1AndStatusSEND() throws Exception {
		// given
		final int expectedChallengeQuantity = 4;
		final int givenPlayerId = 1;
		final ChallengeStatus givenStatus = ChallengeStatus.SEND;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenPlayerId,
				givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnOneChallengeWhenGivenPlayerId1AndStatusACCEPTED() throws Exception {
		// given
		final int expectedChallengeQuantity = 1;
		final int givenPlayerId = 1;
		final ChallengeStatus givenStatus = ChallengeStatus.ACCEPTED;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenPlayerId,
				givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnZeroChallengesWhenGivenPlayerId1AndStatusCANCELED() throws Exception {
		// given
		final int expectedChallengeQuantity = 0;
		final int givenPlayerId = 1;
		final ChallengeStatus givenStatus = ChallengeStatus.CANCELED;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenPlayerId,
				givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnSixChallengesWhenGivenStatusSEND() throws Exception {
		// given
		final int expectedChallengeQuantity = 6;
		final ChallengeStatus givenStatus = ChallengeStatus.SEND;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnZeroChallengesWhenGivenStatusCanceled() throws Exception {
		// given
		final int expectedChallengeQuantity = 0;
		final ChallengeStatus givenStatus = ChallengeStatus.CANCELED;
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengesFilteredBy(givenStatus);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnChallengesForGivenParams() throws Exception {
		// given
		final int expectedChallengeQuantity = 3;
		final LocalDateTime givenCreatedAfter = LocalDateTime.of(2001, 9, 1, 0, 0);
		final LocalDateTime givenCreatedBefore = LocalDateTime.of(2001, 12, 1, 0, 0);
		Map<String, List<String>> filtersMap = new HashMap<>();
		ArrayList<String> args = new ArrayList<>();
		args.add(givenCreatedAfter.toString());
		filtersMap.put("createdAfter", args);
		args = new ArrayList<>();
		args.add(givenCreatedBefore.toString());
		filtersMap.put("createdBefore", args);
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengeFilteredBy(filtersMap);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

	@Test
	public void shouldReturnChallengesForGivenParams2() throws Exception {
		// given
		final int expectedChallengeQuantity = 6;
		LocalDateTime givenExpiredAfter = LocalDateTime.of(2000, 10, 1, 0, 0);
		LocalDateTime givenExpiredBefore = LocalDateTime.of(2000, 12, 1, 0, 0);
		Map<String, List<String>> filtersMap = new HashMap<>();
		ArrayList<String> args = new ArrayList<>();
		args.add(givenExpiredAfter.toString());
		filtersMap.put("expiredAfter", args);
		args = new ArrayList<>();
		args.add(givenExpiredBefore.toString());
		filtersMap.put("expiredBefore", args);
		// when
		List<ChallengeTO> filteredChallenges = this.filterChallengeService.getChallengeFilteredBy(filtersMap);
		final int actualChallengeQuantity = filteredChallenges.size();
		// then
		Assert.assertNotNull(filteredChallenges);
		Assert.assertEquals(expectedChallengeQuantity, actualChallengeQuantity);
	}

}
