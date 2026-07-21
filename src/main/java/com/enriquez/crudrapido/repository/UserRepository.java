package com.enriquez.crudrapido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enriquez.crudrapido.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    
}
