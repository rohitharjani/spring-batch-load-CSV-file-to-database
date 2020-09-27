package com.java.springbatch.processor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.java.springbatch.constants.ServiceConstants;
import com.java.springbatch.models.UserDetails;

@Component
public class SpringBatchProcessor implements ItemProcessor<UserDetails, UserDetails> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchProcessor.class);
	private static final Map<String, String> userRoleMap = new HashMap<>();

	public SpringBatchProcessor() {
		userRoleMap.put(ServiceConstants.ROLE_ID_001, ServiceConstants.BACKEND_DEVELOPER);
		userRoleMap.put(ServiceConstants.ROLE_ID_002, ServiceConstants.FRONTEND_DEVELOPER);
	}

	@Override
	public UserDetails process(UserDetails userDetails) throws Exception {
		String roleId = userDetails.getRole();
		String roleName = userRoleMap.get(roleId);
		userDetails.setRole(roleName);
		userDetails.setTime(LocalDateTime.now());
		LOGGER.info("User Details after process {} ", userDetails);
		return userDetails;
	}
}
