package com.capgemini.chess.batch;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveGameBatchReport {
	@Autowired
	private RemoveAbandonedGamesBatch batch;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		batch.removeGamesOlderThan3MonthsFrom(LocalDateTime.now());
		System.out.println("The time is now " + dateFormat.format(new Date()));
	}

}