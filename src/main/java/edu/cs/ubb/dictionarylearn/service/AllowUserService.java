package edu.cs.ubb.dictionarylearn.service;

import edu.cs.ubb.dictionarylearn.model.AllowUser;
import edu.cs.ubb.dictionarylearn.repository.AllowUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllowUserService {

    private AllowUserRepository repository;

    @Autowired
    public AllowUserService(AllowUserRepository repository) { this.repository = repository; }

    public Iterable<AllowUser> findAll(){
        return this.repository.findAll();
    }

    public AllowUser save(AllowUser usr){ return this.repository.save(usr); }

    public void deleteById(String email){
        this.repository.deleteById(email);
    }

    public AllowUser findByAllowUserEmail(String email){
        return this.repository.findByAllowUserEmail(email);
    }
}