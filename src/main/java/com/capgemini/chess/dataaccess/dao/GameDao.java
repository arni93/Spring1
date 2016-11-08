package com.capgemini.chess.dataaccess.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.chess.service.to.GameTO;

/**
 * Data Access Object interface with methods to operate on Game Entities
 * 
 * @author AWOZNICA
 *
 */
public interface GameDao {

	/**
	 * return all games in repository
	 * 
	 * @return list of all games in repository
	 */
	public List<GameTO> getAllGames();

	/**
	 * adds new Game to repository
	 * 
	 * @param gameTO
	 *            game that is added
	 * @return true when game successfully added to repository
	 */

	public boolean addGame(GameTO gameTO);

	/**
	 * gets game by game id
	 * 
	 * @param gameId
	 *            game id
	 * @return GameTO object with id as specified in param, when no such game
	 *         found then null
	 */

	GameTO getGameById(long gameId);

	/**
	 * removes game with given game id
	 * 
	 * @param gameId
	 *            id of game
	 * @return true when game exists and was removed successfully, false when no
	 *         matching game id or couldn't remove
	 */
	boolean removeGame(long gameId);

	/**
	 * gets all games for one player
	 * 
	 * @param id
	 *            player id
	 * @return return list of all games for both whitePlayer and blackPlayer
	 *         with given id
	 * 
	 */
	public List<GameTO> getGamesByPlayerId(long id);

	/**
	 * returns all games with last move before specifiedDate
	 * 
	 * @param date
	 *            date
	 * @return list of games with last move date before specified in parameter
	 */
	public List<GameTO> getGamesWithLastMoveBefore(LocalDateTime date);

}
