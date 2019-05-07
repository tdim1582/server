package edu.cs.ubb.dictionarylearn.service;

import edu.cs.ubb.dictionarylearn.model.AllowTold;
import edu.cs.ubb.dictionarylearn.model.User;
import edu.cs.ubb.dictionarylearn.repository.AllowToldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllowToldService {

    private AllowToldRepository repository;

    @Autowired
    public  AllowToldService(AllowToldRepository repository) { this.repository = repository; }

    public Iterable<AllowTold> findAll(){
        return this.repository.findAll();
    }

    public void save(AllowTold allowTold){
        this.repository.save(allowTold);
    }

    public void deleteById(Long allowToldId){
        this.repository.deleteById(allowToldId);
    }

    public AllowTold findByAllowToldId(Long allowToldId){
        return this.repository.findByAllowToldId(allowToldId);
    }

    public void deleteAllByUser(User user) { this.repository.deleteAllByUser(user);}
}