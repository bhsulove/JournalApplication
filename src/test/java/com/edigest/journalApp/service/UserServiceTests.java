package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeAll
    static void beforeAll() {
        //Before all tests, this method is implemented.
    }
    @AfterAll
    static void afterAll() {
        //After all tests, this method is implemented.
    }

    @BeforeEach
    void beforeEach() {
        //Before every test method this method will be executed.
    }
    @AfterEach
    void afterEach() {
        //After every test method this method will be executed.
    }

    @Disabled
    @Test
    public void testFindUserByUsername() {
        User user = userRepository.findByUsername("ram");
        assertTrue(!user.getJournalEntries().isEmpty());

        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUsername("ram"));
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,3,5",
            "3,3,8"
    })
    public void test(int a , int b , int expected){
        assertEquals(expected,a+b);
    }

    /*@ParameterizedTest
    @ValueSource(strings={
            "ram","shyam","hari"
    })
    public void testfindByUsername(String username){
       assertNotNull(userRepository.findByUsername(username),"failed to find user "+username);
    }*/

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }



}
