package com.capgemini.chess.batch;

import java.time.LocalDateTime;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveGamesAndShowTimeTasklet implements Tasklet {
	@Autowired
	RemoveAbandonedGamesBatch batch;

	public RemoveGamesAndShowTimeTasklet() {
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		batch.removeGamesOlderThan3MonthsFrom(LocalDateTime.now());
		System.out.println("current time=" + LocalDateTime.now());
		return RepeatStatus.FINISHED;
	}

}
