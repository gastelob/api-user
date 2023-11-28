package com.gastelob.apiuser.repository;

import com.gastelob.apiuser.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.phones")
    Optional<List<UserEntity>> findAllWithPhones();
}
