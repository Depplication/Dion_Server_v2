package com.dion.v2.domain.advertising.repository;

import com.dion.v2.domain.advertising.entity.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {
}
