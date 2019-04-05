package br.eti.krebscode.service;

import java.util.List;

import br.eti.krebscode.domain.User;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
