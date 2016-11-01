package com.capgemini.chess.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
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
			JobExecution executionStatus = jobLauncher.run(job, param);
			System.out.println("Execution status: " + executionStatus);
			// System.out.println("dzialam");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
