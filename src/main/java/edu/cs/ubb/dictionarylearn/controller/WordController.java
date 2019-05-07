package edu.cs.ubb.dictionarylearn.controller;

import edu.cs.ubb.dictionarylearn.model.Word;
import edu.cs.ubb.dictionarylearn.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
@RequestMapping("/word")
@CrossOrigin( origins = "http://localhost:3000")
public class WordController {

    private WordService service;

    @Autowired
    public WordController(WordService service){
        this.service = service;
    }

    @RequestMapping(path = "/{wordId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Word findByWordId(@PathVariable Long wordId){
        return this.service.findByWordId(wordId);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Word> getAllWord(){
        return this.service.findAll();
    }

    @RequestMapping(path = "/{hungarian}/{english}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Word findByHungarianAndEnglish(@PathVariable String hungarian, @PathVariable String english){
        return this.service.findByHungarianAndEnglish(hungarian, english);
    }

    @RequestMapping(path = "/hu/{hungarian}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Word> findAllByHungarian(@PathVariable String hungarian){
        return this.service.findAllByHungarian(hungarian);
    }

    @RequestMapping(path = "/en/{english}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Word> findAllByEnglish(@PathVariable String english){
        return this.service.findAllByEnglish( english);
    }



    @RequestMapping(path = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertwords() {
        Word word = new Word();
        try  {
            BufferedReader br = new BufferedReader(new FileReader("wlma.txt"));
            String line = br.readLine();
            while (line != null) {
                line = line.replace("]","ú");
                line = line.replace("~","í");
                line = line.replace("[","ö");
                line = line.replace(";","é");
                line = line.replace("+","á");
                line = line.replace("==","ó");
                line = line.replace("_","ü");
//                line = line.replace(")","ö");//ő
//                line = line.replace("|","ü");//ű
                word.setHungarian(line);


                String english=br.readLine();
                line = br.readLine();
                int count = english.length();
                while ( line.length()>0) {
                    if(count+line.length() <100) {
                        english = english + ", " + line;
                        count += english.length();
                    }
                    line = br.readLine();
                }
                line = br.readLine();
                word.setWordId(0L);
                word.setEnglish(english);

                service.saveWord(word);
            }

        }
        catch (FileNotFoundException e){
            System.out.println("Fail don't exist!");
        }
        catch (Exception e){
            System.out.println("Error!");
        }



    }

}