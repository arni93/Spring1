package com.capgemini.chess.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.impl.ChallengeDaoImpl;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Tests ChallengeDao
 * 
 * @author AWOZNICA
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ChallengeDaoTest {
	@Autowired
	ChallengeDao challengeDao;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		@Scope("prototype")
		public ChallengeDao challengeDao() {
			return new ChallengeDaoImpl();
		}
	}

	@Test
	public void shouldAddTwoChallengesToDaoWhenTwoChallengesGiven() throws Exception {
		// given
		ChallengeTO firstChallenge = new ChallengeTO();
		ChallengeTO secondChallenge = new ChallengeTO();
		firstChallenge.setChallengeStatus(ChallengeStatus.ACCEPTED);
		secondChallenge.setChallengeStatus(ChallengeStatus.DECLINED);
		firstChallenge.setReceiverPlayerId(10);
		secondChallenge.setReceiverPlayerId(8);
		firstChallenge.setSenderPlayerId(1);
		secondChallenge.setReceiverPlayerId(2);
		int sizeBeforeAdding = this.challengeDao.getAllChallenges().size();
		// when
		this.challengeDao.addChallenge(firstChallenge);
		this.challengeDao.addChallenge(secondChallenge);
		// then
		int sizeAfterAdding = this.challengeDao.getAllChallenges().size();
		Assert.assertEquals(sizeBeforeAdding + 2, sizeAfterAdding);
	}

	@Test
	public void shouldAddNoChallengesWhenNullChallengeGiven() throws Exception {
		ChallengeTO challenge = null;
		int sizeBeforeAdding = this.challengeDao.getAllChallenges().size();
		// when
		this.challengeDao.addChallenge(challenge);
		// then
		int sizeAfterAdding = this.challengeDao.getAllChallenges().size();
		Assert.assertEquals(sizeBeforeAdding, sizeAfterAdding);
	}

	@Test
	public void shouldReturnTrueAndRemoveExistingChallenge() throws Exception {
		// given
		long givenId = 1;
		// when
		boolean status = this.challengeDao.removeChallengeByChallengeId(givenId);
		// then
		ChallengeTO removedChallenge = this.challengeDao.getChallengeById(givenId);
		Assert.assertTrue(status);
		Assert.assertNull(removedChallenge);
	}

	@Test
	public void shouldReturnFalseAndRemoveNothingForNotExistingChallengeGiven() throws Exception {
		// given
		long givenId = 1000;
		// when
		boolean status = this.challengeDao.removeChallengeByChallengeId(givenId);
		// then
		Assert.assertFalse(status);
	}

	@Test
	public void shouldReturnChallengeWhenGivenChallengeExists() throws Exception {
		// given
		long givenId = 2;
		// when
		ChallengeTO challenge = this.challengeDao.getChallengeById(givenId);
		// then
		Assert.assertNotNull(challenge);
		Assert.assertEquals(givenId, challenge.getChallengeId());
	}

	@Test
	public void shouldReturnNullWhenGivenChallengeNotExist() throws Exception {
		// given
		long givenId = 2000;
		// when
		ChallengeTO challengeTO = this.challengeDao.getChallengeById(givenId);
		// then
		Assert.assertNull(challengeTO);
	}

	@Test
	public void shouldReturnAllChallenges() throws Exception {
		// given
		long expectedChallengesQuantity = 9;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getAllChallenges();
		int listSize = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertNotNull(challengeList.get(1));
		Assert.assertEquals(expectedChallengesQuantity, listSize);
	}

	@Test
	public void shouldReturn6ChallengesWhenGivenStatusSend() throws Exception {
		// given
		final ChallengeStatus givenStatus = ChallengeStatus.SEND;
		final int expectedSize = 6;
		// when
		List<ChallengeTO> challengesByStatus = this.challengeDao.getChallengesByStatus(givenStatus);
		int size = challengesByStatus.size();
		// then
		Assert.assertNotNull(challengesByStatus);
		Assert.assertEquals(expectedSize, size);
		Assert.assertNotNull(challengesByStatus.get(0));
		Assert.assertEquals(givenStatus, challengesByStatus.get(0).getChallengeStatus());
	}

	@Test
	public void shouldReturn2ChallengesWhenGivenStatusAccepted() throws Exception {
		// given
		final ChallengeStatus givenStatus = ChallengeStatus.ACCEPTED;
		final int expectedSize = 2;
		// when
		List<ChallengeTO> challengesByStatus = this.challengeDao.getChallengesByStatus(givenStatus);
		int size = challengesByStatus.size();
		// then
		Assert.assertNotNull(challengesByStatus);
		Assert.assertEquals(expectedSize, size);
		Assert.assertNotNull(challengesByStatus.get(0));
		Assert.assertEquals(givenStatus, challengesByStatus.get(0).getChallengeStatus());
	}

	@Test
	public void shouldReturn1ChallengeWhenGivenStatusDeclined() throws Exception {
		// given
		final ChallengeStatus givenStatus = ChallengeStatus.DECLINED;
		final int expectedSize = 1;
		// when
		List<ChallengeTO> challengesByStatus = this.challengeDao.getChallengesByStatus(givenStatus);
		int size = challengesByStatus.size();
		// then
		Assert.assertNotNull(challengesByStatus);
		Assert.assertEquals(expectedSize, size);
		Assert.assertNotNull(challengesByStatus.get(0));
		Assert.assertEquals(givenStatus, challengesByStatus.get(0).getChallengeStatus());
	}

	@Test
	public void shouldReturnEmptyListWhenGivenStatusCanceled() throws Exception {
		// given
		final ChallengeStatus givenStatus = ChallengeStatus.CANCELED;
		final int expectedSize = 0;
		// when
		List<ChallengeTO> challengesByStatus = this.challengeDao.getChallengesByStatus(givenStatus);
		int size = challengesByStatus.size();
		// then
		Assert.assertNotNull(challengesByStatus);
		Assert.assertEquals(expectedSize, size);
	}

	@Test
	public void shouldReturnEmptyListWhenGivenStatusNull() throws Exception {
		// given
		final ChallengeStatus givenStatus = null;
		final int expectedSize = 0;
		// when
		List<ChallengeTO> challengesByStatus = this.challengeDao.getChallengesByStatus(givenStatus);
		int size = challengesByStatus.size();
		// then
		Assert.assertNotNull(challengesByStatus);
		Assert.assertEquals(expectedSize, size);
	}

	@Test
	public void shouldReturn3ChallengesForSenderId3() throws Exception {
		// given
		long playerId = 3;
		int expectedSize = 3;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesBySenderPlayerId(playerId);
		int size = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, size);
		Assert.assertNotNull(challengeList.get(0));
		Assert.assertEquals(playerId, challengeList.get(0).getSenderPlayerId());
	}

	@Test
	public void shouldReturn3ChallengesForReceiverId1() throws Exception {
		// given
		long playerId = 1;
		int expectedSize = 3;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesByReceiverPlayerId(playerId);
		int size = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, size);
		Assert.assertNotNull(challengeList.get(0));
		Assert.assertEquals(playerId, challengeList.get(0).getReceiverPlayerId());
	}

	@Test
	public void shouldReturnEmptyListForNotExistingSenderId() throws Exception {
		// given
		long playerId = 3000;
		int expectedSize = 0;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesByReceiverPlayerId(playerId);
		int size = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, size);
	}

	@Test
	public void shouldReturnEmptyListForSenderIdWithNoChallenges() throws Exception {
		// given
		long senderId = 100;
		int expectedSize = 0;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesBySenderPlayerId(senderId);
		int actualSize = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldReturnChallengesExpiringBeforeGivenDate() throws Exception {
		// given
		LocalDateTime givenDate = LocalDateTime.of(2001, 1, 10, 10, 0);
		int expectedSize = 6;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesExpiredBefore(givenDate);
		int actualSize = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldReturnChallengesExpiringAfterGivenDate() throws Exception {
		// given
		LocalDateTime givenDate = LocalDateTime.of(2001, 1, 10, 10, 0);
		int expectedSize = 3;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesExpiredAfter(givenDate);
		int actualSize = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldReturnChallengesCreatedBeforeGivenDate() throws Exception {
		// given
		LocalDateTime givenDate = LocalDateTime.of(2001, 1, 10, 10, 0);
		int expectedSize = 6;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesCreatedBefore(givenDate);
		int actualSize = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldReturnChallengesCreatedAfterGivenDate() throws Exception {
		// given
		LocalDateTime givenDate = LocalDateTime.of(2001, 1, 10, 10, 0);
		int expectedSize = 3;
		// when
		List<ChallengeTO> challengeList = this.challengeDao.getChallengesCreatedAfter(givenDate);
		int actualSize = challengeList.size();
		// then
		Assert.assertNotNull(challengeList);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldChangeStatusForGivenExistingChallengeIdAndReturnTrue() throws Exception {
		// given
		long challengeId = 2;
		ChallengeStatus givenStatus = ChallengeStatus.CANCELED;
		// when
		ChallengeStatus challengeStatusBefore = this.challengeDao.getChallengeById(challengeId).getChallengeStatus();
		boolean methodResult = this.challengeDao.setChallengeStatus(challengeId, givenStatus);
		ChallengeStatus challengeStatusAfter = this.challengeDao.getChallengeById(challengeId).getChallengeStatus();
		// then
		Assert.assertTrue(methodResult);
		Assert.assertEquals(givenStatus, challengeStatusAfter);
		Assert.assertNotEquals(givenStatus, challengeStatusBefore);
	}

	@Test
	public void shouldReturnFalseWhenChangingStatusForGivenNotExistingChallengeId() throws Exception {
		// given
		long challengeId = 2000;
		ChallengeStatus givenStatus = ChallengeStatus.CANCELED;
		// when
		ChallengeTO challengeById = this.challengeDao.getChallengeById(challengeId);
		boolean methodResult = this.challengeDao.setChallengeStatus(challengeId, givenStatus);
		// then
		Assert.assertNull(challengeById);
		Assert.assertFalse(methodResult);
	}

	@Test
	public void shouldReturnFalseWhenChangingSenderIdForNotExistingChallengeId() throws Exception {
		// given
		long challengeId = 2000;
		long playerId = 10;
		// when
		ChallengeTO challengeById = this.challengeDao.getChallengeById(challengeId);
		boolean methodResult = this.challengeDao.setChallengeSenderPlayerId(challengeId, playerId);
		// then
		Assert.assertNull(challengeById);
		Assert.assertFalse(methodResult);
	}

	@Test
	public void shouldReturnTrueWhenChangingSenderIdForExistingChallengeId() throws Exception {
		// given
		long challengeId = 2;
		long playerId = 10000;
		// when
		long senderPlayerIdBefore = this.challengeDao.getChallengeById(challengeId).getSenderPlayerId();
		boolean methodResult = this.challengeDao.setChallengeSenderPlayerId(challengeId, playerId);
		long senderPlayerIdAfter = this.challengeDao.getChallengeById(challengeId).getSenderPlayerId();

		// then
		Assert.assertTrue(methodResult);
		Assert.assertEquals(playerId, senderPlayerIdAfter);
		Assert.assertNotEquals(playerId, senderPlayerIdBefore);
	}

	@Test
	public void shouldReturnFalseWhenChangingReceiverIdForNotExistingChallengeId() throws Exception {
		// given
		long challengeId = 2000;
		long playerId = 10;
		// when
		ChallengeTO challengeById = this.challengeDao.getChallengeById(challengeId);
		boolean methodResult = this.challengeDao.setChallengeReceiverPlayerId(challengeId, playerId);
		// then
		Assert.assertNull(challengeById);
		Assert.assertFalse(methodResult);
	}

	@Test
	public void shouldReturnTrueAndChangeReceiverIdForGivenExistingChallengeId() throws Exception {
		// given
		long challengeId = 2;
		long playerId = 10000;
		// when
		long receiverPlayerIdBefore = this.challengeDao.getChallengeById(challengeId).getReceiverPlayerId();
		boolean methodResult = this.challengeDao.setChallengeReceiverPlayerId(challengeId, playerId);
		long receiverPlayerIdAfter = this.challengeDao.getChallengeById(challengeId).getReceiverPlayerId();

		// then
		Assert.assertTrue(methodResult);
		Assert.assertEquals(playerId, receiverPlayerIdAfter);
		Assert.assertNotEquals(playerId, receiverPlayerIdBefore);
	}

	@Test
	public void shouldReturnFalseWhenChangingCreationDateForNotExistingChallengeId() throws Exception {
		// given
		long challengeId = 2000;
		LocalDateTime givenDate = LocalDateTime.now();
		// when
		ChallengeTO challengeById = this.challengeDao.getChallengeById(challengeId);
		boolean methodResult = this.challengeDao.setChallengeCreationDate(challengeId, givenDate);
		// then
		Assert.assertNull(challengeById);
		Assert.assertFalse(methodResult);
	}

	@Test
	public void shouldReturnTrueAndChangeCreationDateForExistingChallengeId() throws Exception {
		// given
		long challengeId = 2;
		LocalDateTime givenDate = LocalDateTime.now();
		// when
		LocalDateTime creationDateBefore = this.challengeDao.getChallengeById(challengeId).getCreationDate();
		boolean methodResult = this.challengeDao.setChallengeCreationDate(challengeId, givenDate);
		LocalDateTime creationDateAfter = this.challengeDao.getChallengeById(challengeId).getCreationDate();
		// then
		Assert.assertTrue(methodResult);
		Assert.assertEquals(givenDate, creationDateAfter);
		Assert.assertNotEquals(givenDate, creationDateBefore);
	}

	@Test
	public void shouldReturnFalseWhenChangingExpireDateForNotExistingChallengeId() throws Exception {
		// given
		long challengeId = 2000;
		LocalDateTime givenDate = LocalDateTime.now();
		// when
		ChallengeTO challengeById = this.challengeDao.getChallengeById(challengeId);
		boolean methodResult = this.challengeDao.setChallengeExpireDate(challengeId, givenDate);
		// then
		Assert.assertNull(challengeById);
		Assert.assertFalse(methodResult);
	}

	@Test
	public void shouldReturnTrueAndChangeExpireDateForGivenExistingChallengeId() throws Exception {
		// given
		long challengeId = 2;
		LocalDateTime givenDate = LocalDateTime.now();
		// when
		LocalDateTime expireDateBefore = this.challengeDao.getChallengeById(challengeId).getExpireDate();
		boolean methodResult = this.challengeDao.setChallengeExpireDate(challengeId, givenDate);
		LocalDateTime expireDateAfter = this.challengeDao.getChallengeById(challengeId).getExpireDate();
		// then
		Assert.assertTrue(methodResult);
		Assert.assertEquals(givenDate, expireDateAfter);
		Assert.assertNotEquals(givenDate, expireDateBefore);
	}

}
