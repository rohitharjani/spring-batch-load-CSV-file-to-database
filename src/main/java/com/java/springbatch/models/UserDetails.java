package com.java.springbatch.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetails {

	@Id
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "timestamp")
	private LocalDateTime time;
}
