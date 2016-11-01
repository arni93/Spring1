package com.capgemini.chess.service.to;

import java.time.LocalDateTime;

public class GameTO {
	private long gameId;
	private long whitePlayerId;
	private long blackPlayerId;
	private LocalDateTime lastMoveDate;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getWhitePlayerId() {
		return whitePlayerId;
	}

	public void setWhitePlayerId(long whitePlayerId) {
		this.whitePlayerId = whitePlayerId;
	}

	public long getBlackPlayerId() {
		return blackPlayerId;
	}

	public void setBlackPlayerId(long blackPlayerId) {
		this.blackPlayerId = blackPlayerId;
	}

	public LocalDateTime getLastMoveDate() {
		return lastMoveDate;
	}

	public void setLastMoveDate(LocalDateTime lastMoveDate) {
		this.lastMoveDate = lastMoveDate;
	}

}
