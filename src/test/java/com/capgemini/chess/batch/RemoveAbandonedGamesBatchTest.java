package com.capgemini.chess.batch;

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
 * @author AWOZNICA
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RemoveAbandonedGamesBatchTest {

	@Autowired
	private RemoveAbandonedGamesBatch batch;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		@Scope("prototype")
		public RemoveAbandonedGamesBatch removeAbandonedGamesBatch() {
			return new RemoveAbandonedGamesBatch();
		}

		@Bean
		@Scope("prototype")
		public GameDao gameDao() {
			return new GameDaoImpl();
		}
	}

	@Test
	public void shouldRemove3DatesForGivenDate() throws Exception {
		// given
		LocalDateTime date = LocalDateTime.of(2000, 7, 4, 10, 0);
		int expectedSize = 3;
		// when
		List<GameTO> removedGames = this.batch.removeGamesOlderThan3MonthsFrom(date);
		int actualSize = removedGames.size();
		// then
		Assert.assertNotNull(removedGames);
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void shouldRemove7DatesForCurrentDate() throws Exception {
		// given
		LocalDateTime date = LocalDateTime.now();
		int expectedSize = 7;
		// when
		List<GameTO> removedGames = this.batch.removeGamesOlderThan3MonthsFrom(date);
		int actualSize = removedGames.size();
		// then
		Assert.assertNotNull(removedGames);
		Assert.assertEquals(expectedSize, actualSize);
	}
}
