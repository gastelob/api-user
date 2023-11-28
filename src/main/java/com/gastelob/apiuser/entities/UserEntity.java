package com.gastelob.apiuser.entities;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@ToString
@RequiredArgsConstructor
@Validated
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<PhoneEntity> phones;

    public void addPhone(PhoneEntity phoneEntity){
        phoneEntity.setUser(this);
    }


    @PrePersist
    public void prePersist() {
        this.phones.forEach(this::addPhone);
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.modified = now;
        this.lastLogin = now;
    }

    @PreUpdate
    public void preUpdate() {
        phones.forEach(this::addPhone);
        this.modified = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity user = (UserEntity) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
