package edu.cs.ubb.dictionarylearn.repository;

import edu.cs.ubb.dictionarylearn.model.AllowUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllowUserRepository extends CrudRepository<AllowUser, String> {

    public AllowUser findByAllowUserEmail(String email);
}