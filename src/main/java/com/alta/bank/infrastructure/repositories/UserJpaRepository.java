package com.alta.bank.infrastructure.repositories;

import com.alta.bank.infrastructure.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {

}
