package edu.cs.ubb.dictionarylearn.controller;

import edu.cs.ubb.dictionarylearn.model.*;
import edu.cs.ubb.dictionarylearn.service.AllowToldService;
import edu.cs.ubb.dictionarylearn.service.ToldService;
import edu.cs.ubb.dictionarylearn.service.UserService;
import edu.cs.ubb.dictionarylearn.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/allowtold")
@CrossOrigin( origins = "http://localhost:3000")
public class AllowToldController {

    private AllowToldService service;
    private ToldService toldService;
    private WordService wordService;
    private UserService userService;

    @Autowired
    public AllowToldController(AllowToldService service, ToldService toldService, UserService userService, WordService wordService ) {
        this.service = service;
        this.toldService = toldService;
        this.userService = userService;
        this.wordService = wordService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<AllowTold> getAllAllowTolds(){
        return this.service.findAll();
    }

    @RequestMapping(path = "/first", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AllowTold getFirstAllowTolds(){
        Iterable<AllowTold> allowTold = this.service.findAll();
        if(allowTold.iterator().hasNext()) {
            //ystem.out.println("Benne allowtold first");
            return allowTold.iterator().next();
        }
        //System.out.println("nincs benne allowtold first");
        AllowTold myallowTold = new AllowTold();
        //System.out.println(myallowTold.getAllowTold());
        return myallowTold;
    }

    @RequestMapping(path = "/{allowToldId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllowTolds(@PathVariable Long allowToldId){
        //System.out.println("Benne allowtold delete");
        this.service.deleteById(allowToldId);
    }

    @RequestMapping(path = "/{allowToldId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void acceptAllowTolds(@PathVariable Long allowToldId){
        AllowTold tempAllowTold = this.service.findByAllowToldId(allowToldId);
        Told told = new Told();
        told.setTold(tempAllowTold.getAllowTold());
        told.setWord(tempAllowTold.getWord());
        told.setToldId(0L);
        this.service.deleteById(allowToldId);
        System.out.println(this.toldService.findAllByTold(told.getTold()));
        if( this.toldService.findAllByTold(told.getTold()) == null) {
            //System.out.println("Benne allowtold accept");
            this.toldService.save(told);
        }
    }


    @PostMapping("/newallowtold")
    public void save(@RequestBody AllowToldSave allowToldSave){
        AllowTold allowTold = new AllowTold();
        String told = allowToldSave.getTold().replace('+',' ');
        allowTold.setAllowTold(told);
        System.out.println(told);
        Word word = this.wordService.findByWordId(allowToldSave.getWordId());
        String hungarian = word.getHungarian();
        hungarian = hungarian.replace("ő",")");
        hungarian = hungarian.replace("ű","|");
        word.setHungarian(hungarian);
        allowTold.setWord(word);
        User user = this.userService.findByEmail(allowToldSave.getEmail());
        allowTold.setUser(user);
        allowTold.setAllowToldId(0L);
        //System.out.println("Benne allowtold new");
        this.service.save(allowTold);
    }


}