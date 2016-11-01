package com.capgemini.chess.dataaccess.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.capgemini.chess.enums.ChallengeStatus;

public class ChallengeEntity {
	@Id
	@GeneratedValue
	private long challengeId;
	@Column(nullable = false)
	private long senderPlayerId;
	@Column(nullable = false)
	private long receiverPlayerId;
	@Column(nullable = false)
	private LocalDateTime creationDate;
	private LocalDateTime expireDate;
	@Column(nullable = false)
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
