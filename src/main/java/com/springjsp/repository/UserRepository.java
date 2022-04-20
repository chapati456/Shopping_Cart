package com.springjsp.repository;

import com.springjsp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM User",nativeQuery = true)
    List<User> findAllByJQl();

    @Query(value = "SELECT * FROM User u WHERE u.user_name = :name",nativeQuery = true)
    User findByUserNameByJQL(@Param("name") String userName);

    @Modifying
    @Query(value = "insert into User (user_name,email,password) values (:name,:mail,:pass)",nativeQuery = true)
    void saveByJQL(@Param("name") String userName,@Param("mail") String email,@Param("pass") String password);
}

