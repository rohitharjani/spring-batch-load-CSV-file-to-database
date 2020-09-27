package com.java.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbatch.models.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

}
