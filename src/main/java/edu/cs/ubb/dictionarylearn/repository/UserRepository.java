package edu.cs.ubb.dictionarylearn.repository;

import edu.cs.ubb.dictionarylearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
    public void deleteByEmail(String email);
    public void deleteUserByEmail(String email);

//    @Query("delete from user u WHERE u.email = :email")
//    public void deleteUserByEmail(@Param("email") String email);
}