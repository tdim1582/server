package edu.cs.ubb.dictionarylearn.controller;

import edu.cs.ubb.dictionarylearn.model.Favorite;
import edu.cs.ubb.dictionarylearn.model.User;
import edu.cs.ubb.dictionarylearn.model.Word;
import edu.cs.ubb.dictionarylearn.service.FavoriteService;
import edu.cs.ubb.dictionarylearn.service.UserService;
import edu.cs.ubb.dictionarylearn.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/favorite")
@CrossOrigin ( origins = "http://localhost:3000")
public class FavoriteController {

    private FavoriteService service;
    private UserService userService;
    private WordService wordService;
    private int[] randomNumber;

    @Autowired
    public FavoriteController(FavoriteService service, UserService userService, WordService wordService){
        this.service = service;
        this.userService = userService;
        this.wordService = wordService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Favorite> getAllFavorite(){
        return this.service.findAll();
    }


    @RequestMapping(path = "/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Favorite> findByEmail(@PathVariable String email){
        List<Favorite> favorite = this.service.findByUser( this.userService.findByEmail(email));
        List<Favorite> favorites = new ArrayList<Favorite>();
        for(Favorite f:favorite){
            Word word = f.getWord();
            String hungarian = word.getHungarian();
            hungarian = hungarian.replace(")","ő");
            hungarian = hungarian.replace("|","ű");
            word.setHungarian(hungarian);
            f.setWord(word);
            favorites.add(f);
        }
        return  favorites;
    }

    @RequestMapping(path = "/{email}/{wordId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Favorite findByEmailAndWordId(@PathVariable String email, @PathVariable Long wordId){
        Favorite favorite = new Favorite();
        User user = this.userService.findByEmail(email);
        Word word = this.wordService.findByWordId(wordId);
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        word.setHungarian(hungarian);
        if(this.service.findByUserAndWord( user, word) != null){
            //System.out.println("megkapta");
            favorite = this.service.findByUserAndWord( user, word);
        }
        return favorite;

    }

    @RequestMapping(path = "/game/{email}/{piece}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Favorite> findfirstTwentyByEmail(@PathVariable String email, @PathVariable int piece){
        List<Favorite> favorite = this.service.findByUser( this.userService.findByEmail(email));
        List<Favorite> favorites = new ArrayList<Favorite>();
        for(Favorite f:favorite){
            Word word = f.getWord();
            String hungarian = word.getHungarian();
            hungarian = hungarian.replace(")","ő");
            hungarian = hungarian.replace("|","ű");
            word.setHungarian(hungarian);
            f.setWord(word);
            favorites.add(f);
        }
        if( favorites.size() <= piece) {
            return favorite;
        } else{
            return randomList(favorites.size(),favorites,piece);
        }

    }

    @RequestMapping(path = "/{email}/{wordId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@PathVariable String email, @PathVariable Long wordId){
        Favorite favorite = new Favorite();
        favorite.setUser( this.userService.findByEmail(email) );
        Word word = this.wordService.findByWordId(wordId);
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        word.setHungarian(hungarian);
        favorite.setWord(word);
        favorite.setFavoriteId( 0L );
        this.service.save( favorite );
    }

    @RequestMapping(path = "/{email}/{wordId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable String email, @PathVariable Long wordId){
        Word word = this.wordService.findByWordId(wordId);
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        word.setHungarian(hungarian);
        this.service.deleteById( this.service.findByUserAndWord( this.userService.findByEmail(email), word).getfavoriteId() );
    }

    private List<Favorite> randomList(int n, List<Favorite> favorites,int piece){
        randomNumber = new int[n];
        randomInt(n,piece);
        List<Favorite> list = new ArrayList<>();

        for(int i=0;i<piece;i++)
            list.add(favorites.get(randomNumber[i]));
        return list;
    }

    private void randomInt(int n,int piece){
        for(int i=0; i<n; i++)
            randomNumber[i] = i;
        for(int i=0; i<piece; i++){
            int random = new Random().nextInt(n);
            int change = randomNumber[i];
            randomNumber[i] = randomNumber[random];
            randomNumber[random] = change;
        }
    }

}