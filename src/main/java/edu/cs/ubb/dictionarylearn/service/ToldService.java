package edu.cs.ubb.dictionarylearn.service;

import com.sun.corba.se.impl.oa.toa.TOA;
import edu.cs.ubb.dictionarylearn.model.Told;
import edu.cs.ubb.dictionarylearn.model.Word;
import edu.cs.ubb.dictionarylearn.repository.ToldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToldService {

    private ToldRepository repository;

    @Autowired
    public ToldService(ToldRepository repository) {this.repository=repository;}

    public Iterable<Told> findAll(){
        return this.repository.findAll();
    }

    public void save(Told told){ this.repository.save(told); }

    public void deleteById(Long toldId){
        this.repository.deleteById(toldId);
    }

    public Told findByToldId(Long toldId){
        return this.repository.findByToldId(toldId);
    }

    public Iterable<Told> findAllByWord(Word word) { return this.repository.findAllByWord(word);}

    public  Told findAllByTold(String told) { return repository.findByTold(told);}

    public void deleteByTold(String told) { repository.deleteByTold(told);}
}