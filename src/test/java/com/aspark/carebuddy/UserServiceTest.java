package com.aspark.carebuddy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.aspark.carebuddy.repository.UserRepository;
import com.aspark.carebuddy.model.user.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testLoadUserByUsername() {

       UserDetails user = userService.loadUserByUsername("asgu21is@cmrit.ac.in");
       assertNotNull(user);

    }
}
