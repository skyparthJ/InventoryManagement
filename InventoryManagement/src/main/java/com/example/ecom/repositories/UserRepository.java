package com.example.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{ 
}
