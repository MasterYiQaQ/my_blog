package com.wang.dao;

import com.wang.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserRepository extends JpaRepository<User,Long> {
    User findAllByUsernameAndPassword(String username,String password);
}
