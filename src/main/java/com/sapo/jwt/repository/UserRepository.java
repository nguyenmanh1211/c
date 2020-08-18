package com.sapo.jwt.repository;

import com.sapo.jwt.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String name, int status);
}
