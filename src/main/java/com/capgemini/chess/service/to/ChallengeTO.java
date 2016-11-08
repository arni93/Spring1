package com.capgemini.chess.service.to;

import java.time.LocalDateTime;

import com.capgemini.chess.enums.ChallengeStatus;

public class ChallengeTO {
	private long challengeId;
	private long senderPlayerId;
	private long receiverPlayerId;
	private LocalDateTime creationDate;
	private LocalDateTime expireDate;
	private ChallengeStatus challengeStatus;

	public long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public long getSenderPlayerId() {
		return senderPlayerId;
	}

	public void setSenderPlayerId(long senderPlayerId) {
		this.senderPlayerId = senderPlayerId;
	}

	public long getReceiverPlayerId() {
		return receiverPlayerId;
	}

	public void setReceiverPlayerId(long receiverPlayerId) {
		this.receiverPlayerId = receiverPlayerId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public ChallengeStatus getChallengeStatus() {
		return challengeStatus;
	}

	public void setChallengeStatus(ChallengeStatus challengeStatus) {
		this.challengeStatus = challengeStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (challengeId ^ (challengeId >>> 32));
		result = prime * result + ((challengeStatus == null) ? 0 : challengeStatus.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((expireDate == null) ? 0 : expireDate.hashCode());
		result = prime * result + (int) (receiverPlayerId ^ (receiverPlayerId >>> 32));
		result = prime * result + (int) (senderPlayerId ^ (senderPlayerId >>> 32));
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
		ChallengeTO other = (ChallengeTO) obj;
		if (challengeId != other.challengeId)
			return false;
		if (challengeStatus != other.challengeStatus)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (expireDate == null) {
			if (other.expireDate != null)
				return false;
		} else if (!expireDate.equals(other.expireDate))
			return false;
		if (receiverPlayerId != other.receiverPlayerId)
			return false;
		if (senderPlayerId != other.senderPlayerId)
			return false;
		return true;
	}

}
