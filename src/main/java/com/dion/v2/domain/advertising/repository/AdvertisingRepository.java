package com.dion.v2.domain.advertising.repository;

import com.dion.v2.domain.advertising.entity.Advertising;
import com.dion.v2.domain.advertising.type.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {

    Page<Advertising> findAllByAdTitleContaining(Pageable pageable, String title);

    Page<Advertising> findAllByCategory(Pageable pageable, Category category);

}
