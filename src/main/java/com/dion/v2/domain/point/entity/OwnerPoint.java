package com.dion.v2.domain.point.entity;

import com.dion.v2.domain.owner.entity.Owner;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Column(nullable = false)
    private Long point;

    @UpdateTimestamp
    private LocalDateTime lastSaveDate;

    @Builder
    public OwnerPoint(Long point) {
        this.point = point;
    }

    public void addPoint(Long point) {
        this.point += point;
    }

}
