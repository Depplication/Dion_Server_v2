package com.dion.v2.domain.auth.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 로그인 ID
    @Column(nullable = false, unique = true)
    private String userId;

    // 로그인 PW
    @Column(nullable = false)
    private String password;

    // 유저 이름
    @Column(nullable = false)
    private String userName;

    // 유저 전화번호
    @Column(nullable = false)
    private String userNumber;

    // 집주소 위도
    @Column(nullable = false)
    private String addressLatitude;

    // 집주소 경도
    @Column(nullable = false)
    private String addressLongitude;

    // 회원가입 날짜
    @CreationTimestamp
    private LocalDateTime createdDate;

    // 계좌번호 리스트
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accountList;
    public void addAccount(Account account) {
        account.setUser(this);
        this.accountList.add(account);
    }

    @Builder
    public User(
            String userId, String password,
            String userName, String userNumber,
            String addressLatitude, String addressLongitude,
            List<Account> accountList) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userNumber = userNumber;
        this.addressLatitude = addressLatitude;
        this.addressLongitude = addressLongitude;
        this.accountList = accountList;
    }

    public void updateUser(
            String userName, String userNumber,
            String addressLongitude, String addressLatitude) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.addressLongitude = addressLongitude;
        this.addressLatitude = addressLatitude;
    }
}
