package com.dion.v2.domain.point.entity;

import com.dion.v2.domain.auth.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    private Long point;

    @LastModifiedDate
    private LocalDateTime lastSaveDate;

    @Builder
    public UserPoint(Long point) {
        this.point = point;
    }

    public void addPoint(Long point) {
        this.point += point;
    }

}
