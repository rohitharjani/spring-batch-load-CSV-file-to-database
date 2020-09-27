package com.java.springbatch.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.springbatch.models.UserDetails;
import com.java.springbatch.repository.UserRepository;

@Component
public class SpringBatchWriter implements ItemWriter<UserDetails> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchWriter.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void write(List<? extends UserDetails> userDetails) throws Exception {
		LOGGER.info("User Details Saved {} ", userDetails);
		userRepository.saveAll(userDetails);
		
	}

	
}
