package com.dion.v2.domain.owner.entity;

import com.dion.v2.domain.point.entity.OwnerPoint;
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
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ownerId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String ownerNumber;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String addressLatitude;

    @Column(nullable = false)
    private String addressLongitude;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accountList;
    public void addAccount(Account account) {
        account.setOwner(this);
        this.accountList.add(account);
    }

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private OwnerPoint point;
    public void setPoint(OwnerPoint point) {
        this.point = point;
    }

    @Builder
    public Owner(
            String ownerId, String password,
            String ownerName, String ownerNumber,
            String storeName, String addressLatitude,
            String addressLongitude, List<Account> accountList) {
        this.ownerId = ownerId;
        this.password = password;
        this.ownerName = ownerName;
        this.ownerNumber = ownerNumber;
        this.storeName = storeName;
        this.addressLatitude = addressLatitude;
        this.addressLongitude = addressLongitude;
        this.accountList = accountList;
    }

    public void updateOwner(
            String ownerName, String ownerNumber,
            String storeName, String addressLongitude,
            String addressLatitude) {
        this.ownerName = ownerName;
        this.ownerNumber = ownerNumber;
        this.storeName = storeName;
        this.addressLongitude = addressLongitude;
        this.addressLatitude = addressLatitude;
    }
}
