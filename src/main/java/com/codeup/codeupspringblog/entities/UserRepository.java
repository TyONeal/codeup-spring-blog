package com.codeup.codeupspringblog.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository <User, Long> {
    @Query("FROM User u WHERE u.id LIKE ?1")
    User getUserById(long id);
    User findByUsername(String Username);

}
