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

}
