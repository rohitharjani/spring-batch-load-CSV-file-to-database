package com.java.springbatch.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/load")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
			
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@GetMapping
	public BatchStatus loadJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Map<String, JobParameter> map = new HashMap<>();
		map.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(map);
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		LOGGER.info("Job execution Status {} ", jobExecution);
		return jobExecution.getStatus();
	}

}
