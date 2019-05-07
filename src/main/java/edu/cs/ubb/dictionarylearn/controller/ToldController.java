package edu.cs.ubb.dictionarylearn.controller;

import edu.cs.ubb.dictionarylearn.model.Told;
import edu.cs.ubb.dictionarylearn.model.Word;
import edu.cs.ubb.dictionarylearn.service.ToldService;
import edu.cs.ubb.dictionarylearn.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/told")
@CrossOrigin( origins = "http://localhost:3000")
public class ToldController {

    private ToldService service;
    private WordService wordService;

    @Autowired
    public ToldController(ToldService service, WordService wordService){
        this.service = service;
        this.wordService = wordService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Told> getAllTold(){
        return this.service.findAll();
    }

    @PostMapping("/ptold")
    public Told getToldByTold(@RequestBody String told){
        told = told.replace('+',' ');
        told = told.replace("%21","!");
        told = told.replace("%3F","?");
        told = told.replace("%2C",",");

        told = told.substring(0,told.length()-1);
        //System.out.println(told);
        return this.service.findAllByTold(told);
    }

    @PostMapping("/newtold")
    public void save(@RequestBody String told, @RequestBody Long wordId){
        told = told.replace('+',' ');
        told = told.substring(0,told.length()-1);
        //System.out.println(told);
        Told mytold = new Told();
        mytold.setTold(told);
        Word word = this.wordService.findByWordId(wordId);
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        word.setHungarian(hungarian);
        mytold.setWord(word);
        this.service.save(mytold);
    }

    @RequestMapping(path = "/{toldId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletToldByTold(@PathVariable Long toldId){
        this.service.deleteById( toldId);
    }

    @RequestMapping(path = "/tolds/{wordId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Told> findAllByWord(@PathVariable Long wordId){
        Word word = this.wordService.findByWordId(wordId);
        //System.out.println(word.getWordId());
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        word.setHungarian(hungarian);
        return this.service.findAllByWord( word);
    }


}