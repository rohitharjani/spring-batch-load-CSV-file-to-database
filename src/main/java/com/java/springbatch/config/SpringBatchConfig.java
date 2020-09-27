package com.java.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.java.springbatch.constants.ServiceConstants;
import com.java.springbatch.models.UserDetails;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<UserDetails> itemReader, ItemProcessor<UserDetails, UserDetails> itemProcessor,
			ItemWriter<UserDetails> itemWriter) {
		Step step = stepBuilderFactory.get(ServiceConstants.ETL_FILE_LOAD)
				.<UserDetails, UserDetails>chunk(ServiceConstants.ONE_HUNDRED).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();
		return jobBuilderFactory.get(ServiceConstants.ETL_LOAD).incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	public FlatFileItemReader<UserDetails> itemReader() {
		FlatFileItemReader<UserDetails> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(ServiceConstants.CSV_FILE_PATH));
		flatFileItemReader.setName(ServiceConstants.CSV_READER);
		flatFileItemReader.setLinesToSkip(ServiceConstants.ONE);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<UserDetails> lineMapper() {
		DefaultLineMapper<UserDetails> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(ServiceConstants.DELIMITER);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { ServiceConstants.ID, ServiceConstants.FIRST_NAME,
				ServiceConstants.LAST_NAME, ServiceConstants.ROLE });
		BeanWrapperFieldSetMapper<UserDetails> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(UserDetails.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}
}
