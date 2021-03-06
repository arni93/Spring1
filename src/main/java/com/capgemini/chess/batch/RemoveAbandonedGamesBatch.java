package com.capgemini.chess.batch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.chess.dataaccess.dao.GameDao;
import com.capgemini.chess.service.to.GameTO;

/**
 * batch service for removing abandoned games
 * 
 * @author AWOZNICA
 */
@Component
public class RemoveAbandonedGamesBatch {
	@Autowired
	private GameDao gameDao;

	/**
	 * removes games where last move was 3 months before given date
	 * 
	 * @param date
	 *            date for which the condition of removing is counted
	 * @return list of removed games
	 */
	public List<GameTO> removeGamesOlderThan3MonthsFrom(LocalDateTime date) {
		LocalDateTime dateMinusThreeMonths = date.minusMonths(3);
		List<GameTO> removedGames = new ArrayList<>();
		List<GameTO> gamesWithLastMoveBefore = this.gameDao.getGamesWithLastMoveBefore(dateMinusThreeMonths);
		for (GameTO game : gamesWithLastMoveBefore) {
			long gameId = game.getGameId();
			boolean removalResult = this.gameDao.removeGame(gameId);
			if (removalResult == true) {
				removedGames.add(game);
			}
		}
		System.out.println("removed games = " + removedGames.size());
		return removedGames;
	}
}
