package edu.cs.ubb.dictionarylearn.service;

import edu.cs.ubb.dictionarylearn.model.User;
import edu.cs.ubb.dictionarylearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public Iterable<User> findAll(){
        return this.repository.findAll();
    }

    public User save(User user){
        return this.repository.save(user);
    }

    @Transactional
    public void deleteByEmail(String email){
        this.repository.deleteUserByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password){
        return this.repository.findByEmailAndPassword(email, password);
    }

    public User findByEmail(String email){
        return this.repository.findByEmail(email);
    }
}