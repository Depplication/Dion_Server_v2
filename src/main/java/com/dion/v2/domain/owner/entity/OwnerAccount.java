package com.dion.v2.domain.owner.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "owner_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Lob
    @Column(nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Builder
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
