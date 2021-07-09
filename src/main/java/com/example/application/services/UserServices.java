package com.example.application.services;

import com.example.application.models.User;

import java.util.Set;

public interface UserServices {
    Set<User> getList();
    Set<User> getList(String filter,Long systemUserId);
    Set<User> getCName(String name);
    Set<User> getNMail(String name);
    User save (User u);
    void delete(User u);
    Set<User> getRecipes();
    User findById(long id);
}
