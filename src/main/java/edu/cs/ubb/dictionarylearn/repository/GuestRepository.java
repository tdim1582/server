package edu.cs.ubb.dictionarylearn.repository;
//
//import edu.cs.ubb.dictionarylearn.model.Guest;
//import edu.cs.ubb.dictionarylearn.model.Told;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface GuestRepository extends CrudRepository<Told, Long> {
//    public Guest save(Guest guest);
//
//
//}

import edu.cs.ubb.dictionarylearn.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long>{

}

