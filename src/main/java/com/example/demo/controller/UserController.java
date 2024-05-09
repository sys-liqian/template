package com.example.demo.controller;

import com.example.demo.annotation.Permission;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Permission(roles = {"admin","ops"})
    public User save(@RequestBody User user){
        return service.Save(user);
    }

    @DeleteMapping("/{id}")
    @Permission(roles = {"admin","ops"})
    public void delete(@PathVariable Long id){
        service.Delete(id);
    }

    @GetMapping
    @Permission(roles = {"user","ops"})
    public List<User> list(){
        return service.GetAllUsers();
    }

    @GetMapping("error")
    public void error(){
        service.ThrowError();
    }
}



