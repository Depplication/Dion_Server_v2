package com.dion.v2.domain.upload.entity;

import com.dion.v2.domain.advertising.entity.Advertising;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdvertisingAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advertisingAttachmentId;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Advertising advertising;
    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }

    private String clientName;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
