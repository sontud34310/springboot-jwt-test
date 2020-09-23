package com.sontud.jwt.test;

import com.sontud.jwt.model.UserDto;
import com.sontud.jwt.service.JwtUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {

    @Mock
    UserDto userDto;

    @Spy
    @InjectMocks
    JwtUserDetailsService jwtUserDetailsService;

    @Test
    public void testHelloWord(){
        String result = jwtUserDetailsService.helloWord("Test");
        assertEquals("Test", result);
        verify(jwtUserDetailsService, atLeastOnce()).helloWord("Test");
        verify(jwtUserDetailsService, times(1)).helloWord("Test");
    }
}
