package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.GameEntity;
import com.capgemini.chess.service.to.GameTO;

public class GameMapper {
	public static GameEntity map(GameTO gameTO) {
		GameEntity gameEntity = null;
		if (gameTO != null) {
			gameEntity = new GameEntity();
			gameEntity.setBlackPlayerId(gameTO.getBlackPlayerId());
			gameEntity.setWhitePlayerId(gameTO.getWhitePlayerId());
			gameEntity.setGameId(gameTO.getGameId());
			gameEntity.setLastMoveDate(gameTO.getLastMoveDate());
		}
		return gameEntity;
	}

	public static GameTO map(GameEntity gameEntity) {
		GameTO gameTO = null;
		if (gameEntity != null) {
			gameTO = new GameTO();
			gameTO.setBlackPlayerId(gameEntity.getBlackPlayerId());
			gameTO.setWhitePlayerId(gameEntity.getWhitePlayerId());
			gameTO.setGameId(gameEntity.getGameId());
			gameTO.setLastMoveDate(gameEntity.getLastMoveDate());
		}
		return gameTO;
	}

	public static List<GameEntity> map2Entities(List<GameTO> gameTOs) {
		return gameTOs.stream().map(GameMapper::map).collect(Collectors.toList());
	}

	public static List<GameTO> map2TOs(List<GameEntity> gameEntities) {
		return gameEntities.stream().map(GameMapper::map).collect(Collectors.toList());
	}
}
