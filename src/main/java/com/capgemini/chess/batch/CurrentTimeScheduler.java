package com.capgemini.chess.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentTimeScheduler {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public void run() {
		try {
			String dataParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date", dataParam).toJobParameters();
			JobExecution executionStatus = jobLauncher.run(job, null);
			System.out.println("Execution status: " + executionStatus);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
