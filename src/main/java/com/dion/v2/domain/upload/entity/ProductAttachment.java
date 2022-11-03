package com.dion.v2.domain.upload.entity;

import com.dion.v2.domain.product.entity.Product;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productAttachmentId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public void setProduct(Product product) {
        this.product = product;
    }

    private String clientName;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
