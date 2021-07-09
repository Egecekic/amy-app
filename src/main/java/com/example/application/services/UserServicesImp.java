package com.example.application.services;

import com.example.application.Repositories.UserRepositories;
import com.example.application.models.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServicesImp implements UserServices {
    private final UserRepositories userRepositories;

    public UserServicesImp(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public Set<User> getList() {
        Set<User> userSet=new HashSet<>();
        System.out.println(userSet);
        userRepositories.findAll().iterator().forEachRemaining(userSet::add);
        return  userSet;
    }

    @Override
    public Set<User> getList(String filter, Long systemUserId) {
        return null;
    }

    @Override
    public Set<User> getCName(String name) {
        Set<User> userSet=new HashSet<>();
        userRepositories.findBycompanyName(name).iterator().forEachRemaining(userSet::add);
        return userSet;
    }

    @Override
    public Set<User> getNMail(String name) {
        Set<User> userSet=new HashSet<>();
        userRepositories.findByMail(name).iterator().forEachRemaining(userSet::add);
        return userSet;
    }

    @Override
    public User save(User u) {
        return userRepositories.save(u);
    }

    @Override
    public void delete(User u) {
        userRepositories.delete(u);
    }

    @Override
    public Set<User> getRecipes() {
        return null;
    }

    @Override
    public User findById(long id) {
        Optional<User> company=userRepositories.findById(id);
        if (!company.isPresent()){
            throw new RuntimeException("hata");
        }
        return company.get();
    }
}
