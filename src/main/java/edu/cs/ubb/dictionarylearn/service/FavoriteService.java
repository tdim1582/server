package edu.cs.ubb.dictionarylearn.service;

import edu.cs.ubb.dictionarylearn.model.Favorite;
import edu.cs.ubb.dictionarylearn.model.User;
import edu.cs.ubb.dictionarylearn.model.Word;
import edu.cs.ubb.dictionarylearn.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private FavoriteRepository repository;

    @Autowired
    public FavoriteService(FavoriteRepository repository){
        this.repository = repository;
    }

    public Iterable<Favorite> findAll(){
        return this.repository.findAll();
    }

    public void save(Favorite favorite){
        this.repository.save(favorite);
    }

    public void deleteById(Long favoriteId){
        this.repository.deleteById(favoriteId);
    }

    public List<Favorite> findByUser(User user) { return this.repository.findAllByUser(user);}

    public Favorite findByUserAndWord(User user, Word word) { return this.repository.findByUserAndWord(user,word); }

    public void deleteAllByUser(User user) { this.repository.deleteAllByUser(user);}

    //public Favorite findByWordId(Long wordId) { return this.repository.findByWordId(wordId); }//  LONG-OT KERESEK. NEM TUDJA, HOGY MELYIK SZERINT?

}