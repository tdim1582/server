package edu.cs.ubb.dictionarylearn.service;

import edu.cs.ubb.dictionarylearn.model.Word;
import edu.cs.ubb.dictionarylearn.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordService {

    private WordRepository repository;

    @Autowired
    public WordService(WordRepository repository){
        this.repository = repository;
    }

    public List<Word> findAll(){
        Iterable<Word> words = this.repository.findAll();
        List<Word> list = new ArrayList<Word>();
        for (Word w:words) {
            String hungarian = w.getHungarian();
            hungarian = hungarian.replace(")","ő");//ő
            hungarian = hungarian.replace("|","ű");//ű
            w.setHungarian(hungarian);
            list.add(w);
        }
        return list;
    }

    public Word findByWordId(Long wordId) {
        Word word = this.repository.findByWordId(wordId);
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace(")","ő");//ő
        hungarian = hungarian.replace("|","ű");//ű
        word.setHungarian(hungarian);
        return  word;
    }

    public Word findByHungarianAndEnglish(String hungarian, String english) {
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        Word word = this.repository.findByHungarianAndEnglish(hungarian,english);
        hungarian = hungarian.replace(")","ő");//ő
        hungarian = hungarian.replace("|","ű");//ű
        word.setHungarian(hungarian);
        return word;
    }

    public List<Word> findAllByHungarian(String hungarian) {
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        Iterable<Word> words = this.repository.findAllByHungarian(hungarian);
        List<Word> list = new ArrayList<Word>();
        for( Word w:words) {
            hungarian = w.getHungarian();
            hungarian = hungarian.replace(")", "ő");//ő
            hungarian = hungarian.replace("|", "ű");//ű
            w.setHungarian(hungarian);
            list.add(w);
        }
        return list;
    }

    public Iterable<Word> findAllByEnglish(String english) {
        Iterable<Word> words = this.repository.findAllByEnglish(english);;
        List<Word> list = new ArrayList<Word>();
        for( Word w:words) {
            String hungarian = w.getHungarian();
            hungarian = hungarian.replace(")", "ő");//ő
            hungarian = hungarian.replace("|", "ű");//ű
            w.setHungarian(hungarian);
            list.add(w);
        }
        return list;
    }

    public void saveWord(Word word){
        this.repository.save(word);
    }
}