package edu.cs.ubb.dictionarylearn.service;
//
//import edu.cs.ubb.dictionarylearn.model.Guest;
//import edu.cs.ubb.dictionarylearn.repository.GuestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class GuestService {
//
//    private GuestRepository repository;
//
//    @Autowired
//    public GuestService(GuestRepository repository){
//        this.repository = repository;
//    }
//
//    public Guest save(Guest guest){ return this.repository.save(guest); }
//
//    public void  deleteAll() { this.repository.deleteAll();}
//}

import edu.cs.ubb.dictionarylearn.model.Guest;
import edu.cs.ubb.dictionarylearn.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Guard;
import java.util.List;

@Service
public class GuestService{
    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public void deleteById(Long id) {
        guestRepository.deleteById(id);
    }

    public Guest addNew(Guest guest) {
        return guestRepository.save(guest);
    }

    public void update(Guest guest) {
        guestRepository.save(guest);
    }


}