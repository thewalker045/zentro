package com.diwakar.SpringBootProject.repository;

import com.diwakar.SpringBootProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
}
