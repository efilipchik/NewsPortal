package com.tms.myproject.repository;

import com.tms.myproject.model.Role;
import com.tms.myproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u where u.login=?1")
    User findByLogin(String login);

}
