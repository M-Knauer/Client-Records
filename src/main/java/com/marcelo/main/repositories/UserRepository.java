package com.marcelo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcelo.main.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByLogin(String login);

}
