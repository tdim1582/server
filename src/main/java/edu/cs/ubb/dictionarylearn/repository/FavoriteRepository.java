package edu.cs.ubb.dictionarylearn.repository;


import edu.cs.ubb.dictionarylearn.model.Favorite;
import edu.cs.ubb.dictionarylearn.model.User;
import edu.cs.ubb.dictionarylearn.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    public Favorite findByFavoriteId(Long favoriteId);
    public List<Favorite> findAllByUser(User user);
    public Favorite findByUserAndWord(User user, Word word);
    public void deleteAllByUser(User user);
    // public Favorite findByWordId(Long wordId);


}