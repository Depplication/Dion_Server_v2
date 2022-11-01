package com.dion.v2.domain.advertising.repository;

import com.dion.v2.domain.advertising.entity.Advertising;
import com.dion.v2.domain.advertising.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {

    List<Advertising> findByAdTitleContaining(String title);

    List<Advertising> findByCategory(Category category);

}
