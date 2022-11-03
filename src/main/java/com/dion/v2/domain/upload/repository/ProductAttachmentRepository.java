package com.dion.v2.domain.upload.repository;

import com.dion.v2.domain.upload.entity.ProductAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttachmentRepository extends JpaRepository<ProductAttachment, Long> {
}
