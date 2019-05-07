package edu.cs.ubb.dictionarylearn.repository;

import edu.cs.ubb.dictionarylearn.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    public Word findByWordId(Long wordId);
    public Word findByHungarianAndEnglish(String hungarian, String english);
    public Iterable<Word> findAllByHungarian(String hungarian);
    public Iterable<Word> findAllByEnglish(String english);

//    @Query("SELECT w FROM Word w WHERE w.hungarian = :hungarian")
//    public Iterable<Word> findAllByHungarian(@Param("hungarian") String hungarian);
}