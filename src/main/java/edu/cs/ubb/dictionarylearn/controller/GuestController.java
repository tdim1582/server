package edu.cs.ubb.dictionarylearn.controller;
//
//import edu.cs.ubb.dictionarylearn.model.Guest;
//import edu.cs.ubb.dictionarylearn.service.GuestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/guest")
//@CrossOrigin(origins = "http://localhost:3000")
//public class GuestController {
//
//    private GuestService service;
//
//    @Autowired
//    public GuestController( GuestService service){
//        this.service = service;
//    }
//
//    @RequestMapping(path = "/{guestName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Guest saveGuest(@PathVariable String guestName){
//        Guest guest = new Guest();
//        guest.setGuestId(0L);
//        guest.setGuestName(guestName);
//        System.out.println(guest.getGuestId()+" "+guest.getGuestName());
//        Guest guestNull = this.service.save(guest);
//        if(guestNull != null){
//            return guestNull;
//        }
//        return null;
//    }
//
//    @RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public void deletToldByTold(){
//        this.service.deleteAll();
//    }
//}

import edu.cs.ubb.dictionarylearn.model.Guest;
import edu.cs.ubb.dictionarylearn.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
@CrossOrigin(origins = "http://localhost:3000")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping
    public List<Guest> getAll(){
        return guestService.getAll();
    }


    @PostMapping("/{userName}")
    public Guest update(@PathVariable String userName){
        Guest guest = new Guest();
        guest.setName(userName);
        return guestService.addNew(guest);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable Long id){
        guestService.deleteById(id);
    }

}