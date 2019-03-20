package com.syshlang.injection.repository.dao;

import com.syshlang.injection.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
}
