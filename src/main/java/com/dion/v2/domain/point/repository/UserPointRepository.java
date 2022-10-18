package com.dion.v2.domain.point.repository;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.point.entity.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPointRepository extends JpaRepository<UserPoint, Long> {

    Optional<UserPoint> findByUser(User user);

}
