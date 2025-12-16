package com.spring.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.project.Entity.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);}
