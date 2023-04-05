package com.suif.repository;

import com.suif.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Query(value = "select r.name from user_role ur left join role r on ur.role_id = r.id where ur.user_id = :uid",nativeQuery = true)
    List<String> findUserRoles(Long uid);
}
