package com.capgemini.chess.dataaccess.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.GameDao;
import com.capgemini.chess.dataaccess.entities.GameEntity;
import com.capgemini.chess.service.mapper.GameMapper;
import com.capgemini.chess.service.to.GameTO;

/**
 * Data access object implementing GameDao interface. It is stub for testing
 * 
 * @author AWOZNICA
 *
 */
@Repository
public class GameDaoImpl implements GameDao {

	private long nextGameId = 0;
	private List<GameEntity> gamesList = new ArrayList<>();

	public GameDaoImpl() {
		this.addStubs();
	}

	@Override
	public List<GameTO> getAllGames() {
		List<GameTO> gameTOs = GameMapper.map2TOs(this.gamesList);
		return gameTOs;
	}

	@Override
	public boolean addGame(GameTO gameTO) {
		long id = this.nextGameId++;
		gameTO.setGameId(id);
		GameEntity gameEntity = GameMapper.map(gameTO);
		boolean result = this.gamesList.add(gameEntity);
		return result;
	}

	@Override
	public boolean removeGame(long gameId) {
		int positionById = this.findPositionById(gameId);
		if (positionById < 0) {
			return false;
		}
		this.gamesList.remove(positionById);
		return true;
	}

	@Override
	public GameTO getGameById(long gameId) {
		GameTO gameTO = null;
		int position = this.findPositionById(gameId);
		if (position >= 0) {
			GameEntity gameEntity = this.gamesList.get(position);
			gameTO = GameMapper.map(gameEntity);
		}
		return gameTO;
	}

	@Override
	public List<GameTO> getGamesByPlayerId(long id) {
		List<GameTO> gameTOs = this.gamesList.stream()
				.filter(p -> (p.getBlackPlayerId() == id || p.getWhitePlayerId() == id)).map(GameMapper::map)
				.collect(Collectors.toList());
		return gameTOs;
	}

	@Override
	public List<GameTO> getGamesWithLastMoveBefore(LocalDateTime date) {
		List<GameTO> gameTOs = this.gamesList.stream().filter(p -> (date.compareTo(p.getLastMoveDate()) > 0))
				.map(GameMapper::map).collect(Collectors.toList());
		return gameTOs;
	}

	private void addStubs() {
		this.gamesList.add(this.createEntity(1, 2, LocalDateTime.of(2000, 2, 1, 10, 0)));
		this.gamesList.add(this.createEntity(2, 1, LocalDateTime.of(2000, 3, 2, 10, 0)));
		this.gamesList.add(this.createEntity(3, 4, LocalDateTime.of(2000, 4, 3, 10, 0)));
		this.gamesList.add(this.createEntity(3, 4, LocalDateTime.of(2000, 5, 3, 10, 0)));
		this.gamesList.add(this.createEntity(4, 5, LocalDateTime.of(2000, 6, 4, 10, 0)));
		this.gamesList.add(this.createEntity(5, 6, LocalDateTime.of(2000, 7, 1, 10, 0)));
		this.gamesList.add(this.createEntity(6, 7, LocalDateTime.of(2000, 8, 1, 10, 0)));

	}

	private GameEntity createEntity(long whitePlayerId, long blackPlayerId, LocalDateTime dateOfLastMove) {
		long gameId = this.nextGameId++;
		GameEntity gameEntity = new GameEntity();
		gameEntity.setGameId(gameId);
		gameEntity.setWhitePlayerId(whitePlayerId);
		gameEntity.setBlackPlayerId(blackPlayerId);
		gameEntity.setLastMoveDate(dateOfLastMove);
		return gameEntity;
	}

	private int findPositionById(long givenId) {
		for (int iter = 0; iter < this.gamesList.size(); iter++) {
			GameEntity gameEntity = this.gamesList.get(iter);
			if (gameEntity.getGameId() == givenId) {
				return iter;
			}
		}
		return -1;
	}

}
