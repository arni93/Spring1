package com.capgemini.chess.batch;

import java.time.LocalDateTime;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TimeTasklet implements Tasklet {

	public TimeTasklet() {
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		System.out.println("current time=" + LocalDateTime.now());
		return RepeatStatus.FINISHED;
	}

}
