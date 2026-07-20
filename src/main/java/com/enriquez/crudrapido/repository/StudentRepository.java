package com.enriquez.crudrapido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enriquez.crudrapido.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    
} 