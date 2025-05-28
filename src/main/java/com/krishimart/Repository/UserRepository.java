package com.krishimart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishimart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
