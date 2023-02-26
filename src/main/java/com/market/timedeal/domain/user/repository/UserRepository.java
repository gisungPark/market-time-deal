package com.market.timedeal.domain.user.repository;

import com.market.timedeal.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u where u.userId = :userId")
    User findByUserId(@Param("userId") String userId);


}
