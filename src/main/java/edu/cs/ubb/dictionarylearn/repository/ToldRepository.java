package edu.cs.ubb.dictionarylearn.repository;

import edu.cs.ubb.dictionarylearn.model.Told;
import edu.cs.ubb.dictionarylearn.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToldRepository extends CrudRepository<Told, Long> {
    public Told findByToldId(Long toldId);
    public Iterable<Told> findAllByWord(Word word);
    public  Told findByTold(String told);
    public void deleteByTold(String told);

}