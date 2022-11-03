package com.dion.v2.domain.upload.repository;

import com.dion.v2.domain.upload.entity.AdvertisingAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingAttachmentRepository extends JpaRepository<AdvertisingAttachment, Long> {
}
