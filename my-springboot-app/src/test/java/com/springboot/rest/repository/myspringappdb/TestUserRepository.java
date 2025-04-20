package com.springboot.rest.repository.myspringappdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@MockBean(ClientRegistrationRepository.class)
@MockBean(OAuth2AuthorizedClientService.class)
@MockBean(ObjectMapper.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TestUserRepository {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testSaveUser() {

        Assertions.assertTrue(userRepository.findByUsername("john_doe").getUsername().equals("john_doe"));
        Assertions.assertTrue(userRepository.findByUsername("jane_smith").getUsername().equals("jane_smith"));
        Assertions.assertTrue(userRepository.findByUsername("alice_wonder").getUsername().equals("alice_wonder"));


    }

    @Test
    public void testDeleteUser() {

        Assertions.assertTrue(userRepository.findByUsername("john_doe") != null);
        userRepository.deleteById("john_doe");
        Assertions.assertTrue(userRepository.findByUsername("john_doe") == null);
    }


}
