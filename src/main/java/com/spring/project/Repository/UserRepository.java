package com.spring.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.project.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //이거 맞아요?
}
