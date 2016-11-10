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

import com.capgemini.chess.dataaccess.dao.GameDao;
import com.capgemini.chess.dataaccess.dao.impl.GameDaoImpl;
import com.capgemini.chess.service.to.GameTO;

/**
 * tests GameDao
 * 
 * @author AWOZNICA
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GameDaoTest {
	@Autowired
	GameDao gameDao;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		@Scope("prototype")
		public GameDao gameDao() {
			return new GameDaoImpl();
		}
	}

	@Test
	public void shouldReturnAllGames() throws Exception {
		// given
		long expectedSize = 7;
		// when
		List<GameTO> allGames = this.gameDao.getAllGames();
		int actualSize = allGames.size();
		// then
		Assert.assertNotNull(gameDao);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldAddNewGame() throws Exception {
		// given
		long expectedSize = 8;
		// when
		GameTO gameTO = new GameTO();
		this.gameDao.addGame(gameTO);
		int actualSize = this.gameDao.getAllGames().size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldReturnGameForGivenGameId() throws Exception {
		// given
		long givenId = 2;
		// when
		GameTO gameTO = this.gameDao.getGameById(givenId);
		// then
		Assert.assertNotNull(gameTO);
		Assert.assertEquals(givenId, gameTO.getGameId());
	}

	@Test
	public void shouldReturnNullForGivenGameId() throws Exception {
		// given
		long givenId = 2000;
		// when
		GameTO gameTO = this.gameDao.getGameById(givenId);
		// then
		Assert.assertNull(gameTO);
	}

	@Test
	public void shouldRemoveGame() throws Exception {
		// given
		long givenId = 3;
		long expectedSize = 6;
		// when
		boolean removeResult = this.gameDao.removeGame(givenId);
		int actualSize = this.gameDao.getAllGames().size();
		// when
		Assert.assertTrue(removeResult);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldGetTwoGamesForCurrentDate() throws Exception {
		// given
		int expectedSize = 7;
		LocalDateTime date = LocalDateTime.now();
		// when
		List<GameTO> gameTOs = this.gameDao.getGamesWithLastMoveBefore(date);
		int actualSize = gameTOs.size();
		// then
		Assert.assertNotNull(gameTOs);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldGetTwoGamesForGivenDate() throws Exception {
		// given
		int expectedSize = 2;
		LocalDateTime date = LocalDateTime.of(2000, 3, 2, 11, 0);
		// when
		List<GameTO> gameTOs = this.gameDao.getGamesWithLastMoveBefore(date);
		int actualSize = gameTOs.size();
		// then
		Assert.assertNotNull(gameTOs);
		Assert.assertEquals(expectedSize, actualSize);
	}
}
