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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (blackPlayerId ^ (blackPlayerId >>> 32));
		result = prime * result + (int) (gameId ^ (gameId >>> 32));
		result = prime * result + ((lastMoveDate == null) ? 0 : lastMoveDate.hashCode());
		result = prime * result + (int) (whitePlayerId ^ (whitePlayerId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameTO other = (GameTO) obj;
		if (blackPlayerId != other.blackPlayerId)
			return false;
		if (gameId != other.gameId)
			return false;
		if (lastMoveDate == null) {
			if (other.lastMoveDate != null)
				return false;
		} else if (!lastMoveDate.equals(other.lastMoveDate))
			return false;
		if (whitePlayerId != other.whitePlayerId)
			return false;
		return true;
	}

}
