package main.java.ru.javawebinar.topjava.service;


import main.java.ru.javawebinar.topjava.model.User;
import main.java.ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);
}
