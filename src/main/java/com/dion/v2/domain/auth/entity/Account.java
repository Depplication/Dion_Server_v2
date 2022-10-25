package com.dion.v2.domain.auth.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
