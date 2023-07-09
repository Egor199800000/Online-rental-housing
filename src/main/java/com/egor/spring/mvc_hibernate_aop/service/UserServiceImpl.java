package com.egor.spring.mvc_hibernate_aop.service;

import com.egor.spring.mvc_hibernate_aop.dao.UserDao;
import com.egor.spring.mvc_hibernate_aop.entity.House;
import com.egor.spring.mvc_hibernate_aop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
       return userDao.getUser(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public User getUserByPassword(String password) {
        return userDao.getUserByPassword(password);
    }



    @Override
    @Transactional
    public void deleteUser(int id) {
        User user=userDao.getUser(id);
        userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public boolean passwordAndMailAuthentication(User user) {
        User user1= userDao.getUserByEmail(user.getEmail());
        User user2= userDao.getUserByPassword(user.getPassword());

        if (user1.getId()==user2.getId()){
            System.err.println("Users equals");
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean isRegisteredUserByPasswordAndEmail(String password, String email){
        try {
            User user= userDao.getUserByEmail(email);
            if (user.getPassword().equals(password)){
                System.err.println("Users equals");
                return true;
            }
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Incorrect login or password");
        }
        return false;
    }

    @Override
    @Transactional
    public void addHouseToListHousesOwner(House house,User user){
       userDao.addHouseToListHousesOwner(house,user);
        System.err.println("Done");
    }
    @Override
    @Transactional
    public void addHouseToListHousesTenant(House house,User user) {
        userDao.addHouseToListHousesTenant(house,user);
        System.err.println("Done");
    }

    @Override
    @Transactional
    public User getAuthorizedUser() {
        return userDao.getAuthorizedUser();
    }

    @Override
    @Transactional
    public boolean isAuthorizedUser(User user) {
        User user1= userDao.getUserByEmail(user.getEmail());
        User user2= userDao.getUserByPassword(user.getPassword());

        if (user1.getId()==user2.getId()){
            System.err.println("Users equals");
            user=userDao.getUser(user1.getId());
            user.setAuthorized(true);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean isUniqueEmail(String email) {
                List<User> users=userDao.getAllUsers();
        for (int i=0; i<users.size(); i++){
            if ((users.get(i).getEmail()).equals(email)){
                throw new RuntimeException("email not unique");
            }
        }
        return true;
    }

    @Override
    @Transactional
    public List<House> getAllHousesOwnedByTheUser(User user) {
       return userDao.getAllHousesOwnedByTheUser(user);
    }
}
