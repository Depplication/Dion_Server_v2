package com.dion.v2.domain.point.repository;

import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.point.entity.OwnerPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerPointRepository extends JpaRepository<OwnerPoint, Long> {

    Optional<OwnerPoint> findByOwner(Owner owner);

}
