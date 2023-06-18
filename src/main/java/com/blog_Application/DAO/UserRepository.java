package com.blog_Application.DAO;

import com.blog_Application.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{

/* Note: DML(data manipulation language)-->(insert,update,delete) operation is not allowed implicitly
* so enable them use @Modifying and @Transactional on serviceIMpl(class where the delete query is called) also
* do not forget to enable Transaction management from main spring boot application by
* using @EnableTransactionManagement
* */



@Modifying
 @Query("delete from User u where u.name= :name")
 void deleteByUserName(@Param(value="name")  String name);



@Query("select u from User u where u.name=:Name")
Optional<User> findByName(@Param("Name") String name);
}



