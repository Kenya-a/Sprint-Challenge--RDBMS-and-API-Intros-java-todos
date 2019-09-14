package com.lambdaschool.javatodos.service;

import com.lambdaschool.javatodos.models.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    User findUserByName(String name);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);
}