package com.gastelob.apiuser.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.gastelob.apiuser.entities.PhoneEntity;
import com.gastelob.apiuser.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity user;

    @BeforeEach
    void setup(){
        user = new UserEntity();
        user.setActive(true);
        user.setEmail("gastelo@gmail.com");
        user.setName("Alexander Gastelo");
        user.setPassword("MiPassword01");

        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setUser(user);
        phoneEntity.setNumber("930532414");
        phoneEntity.setCityCode("01");
        phoneEntity.setCountryCode("51");
        List<PhoneEntity> phoneEntities = new ArrayList<>();
        phoneEntities.add(phoneEntity);
        user.setPhones(phoneEntities);
    }
    @DisplayName("Test registrar usuario")
    @Test
    void testRegisterUser(){

        //when

        UserEntity userSave = userRepository.save(user);
        //then
        assertThat(userSave).isNotNull();
        assertThat(userSave.getPhones()).isNotNull();
        assertThat(userSave.getId()).isNotNull();
        assertThat(userSave.getCreated()).isNotNull();
    }

    @DisplayName("Test listar todos los usuarios")
    @Test
    void testListAllUsers(){
        UserEntity user2 = new UserEntity();
        user2.setActive(true);
        user2.setEmail("gastelo02@gmail.com");
        user2.setName("Nuevo Usuario");
        user2.setPassword("MiPassword02");

        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setUser(user2);
        phoneEntity.setNumber("93000000");
        phoneEntity.setCityCode("01");
        phoneEntity.setCountryCode("51");
        List<PhoneEntity> phoneEntities = new ArrayList<>();
        phoneEntities.add(phoneEntity);
        user2.setPhones(phoneEntities);

        userRepository.save(user);
        userRepository.save(user2);

        List<UserEntity> userEntities = userRepository.findAllWithPhones().orElse(new ArrayList<>());

        assertThat(userEntities).isNotNull();
        assertThat(userEntities.size()).isEqualTo(2);
    }

}
