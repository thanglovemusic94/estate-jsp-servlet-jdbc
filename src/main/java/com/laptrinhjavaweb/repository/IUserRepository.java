package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;

public interface IUserRepository extends GennericJDBC<UserEntity> {
	Long insert(UserEntity userEntity);
}
