package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User Save(User u){
        return userDao.save(u);
    }
    public void Delete(Long id){
        User u = new User();
        u.setId(id);
        userDao.delete(u);
    }
    public List<User> GetAllUsers(){
        return userDao.GetAllUsers();
    }

    public void ThrowError(){
        try {
            throw new RuntimeException("test error1");
        }catch (Exception e){
            System.out.println(e);
        }
        throw new RuntimeException("test error2");
    }
}
