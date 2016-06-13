package main.java.ru.javawebinar.topjava.service;

import main.java.ru.javawebinar.topjava.model.User;
import main.java.ru.javawebinar.topjava.repository.UserRepository;
import main.java.ru.javawebinar.topjava.util.exception.ExceptionUtil;
import main.java.ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        repository.save(user);
    }
}
