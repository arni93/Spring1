package com.capgemini.chess.enums;

/**
 * Enums for collecting status of challenges
 * 
 * @author AWOZNICA
 *
 */
public enum ChallengeStatus {
	/**
	 * Send means challenge was send to another player and waits for his
	 * decision
	 */
	SEND,
	/**
	 * Accepted means challenge was accepted by receiver player
	 */
	ACCEPTED,
	/**
	 * Declined means challenge was not accepted by received player
	 */
	DECLINED,
	/**
	 * Canceled means sender of challenge canceled it before receiver decided to
	 * accept or not
	 */
	CANCELED;
}
