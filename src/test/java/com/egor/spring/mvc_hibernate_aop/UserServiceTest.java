package com.egor.spring.mvc_hibernate_aop;

import com.egor.spring.mvc_hibernate_aop.dao.UserDaoImpl;
import com.egor.spring.mvc_hibernate_aop.entity.User;
import com.egor.spring.mvc_hibernate_aop.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDaoImpl userDao;


    @Test
    void isUniqueEmail_throwsException(){
        User user1=new User();
        User user2=new User();
        user1.setEmail("123gmail.com");
        user2.setEmail("123gmail.com");
        List<User> allUsers=new ArrayList<>();
        allUsers.add(user1);
        //указываем что при вызове метода getAllUser()-будет возвращаться этот лист
        Mockito.when(userDao.getAllUsers()).thenReturn(allUsers);
        assertThrows(RuntimeException.class, ()-> {
            System.out.println("email first user == email last user");
            userService.isUniqueEmail(user2.getEmail());
        });
    }

    @Test
    void isUniqueEmail_returnTrue(){
        User user1=new User();
        User user2=new User();
        user1.setEmail("123gmail.com");
        user2.setEmail("321gmail.com");
        List<User> allUsers=new ArrayList<>();
        allUsers.add(user1);
        Mockito.when(userDao.getAllUsers()).thenReturn(allUsers);
        System.out.println("email first user != email last user");
        Assertions.assertEquals(true,userService.isUniqueEmail(user2.getEmail()));
    }

    @Test
    void getAuthorizedUser(){
        User user1=new User();
        User user2=new User();
        Mockito.when(userDao.getAuthorizedUser()).thenReturn(user2);
        Assertions.assertEquals(user2,userService.getAuthorizedUser());
    }

    @Test
    void isRegisteredUserByPasswordAndEmail(){
        String email="123gmail.com";
        String password="123";

        User user=new User();
        user.setEmail(email);
        user.setPassword(password);

        Mockito.when(userDao.getUserByEmail(email)).thenReturn(user);
        Assertions.assertEquals
                (true,userService.isRegisteredUserByPasswordAndEmail(password,email));
    }



}
