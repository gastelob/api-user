package com.gastelob.apiuser.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @ToString.Exclude
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhoneEntity phone = (PhoneEntity) o;
        return getId() != null && Objects.equals(getId(), phone.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
