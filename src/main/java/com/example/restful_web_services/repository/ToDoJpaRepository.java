package com.example.restful_web_services.repository;

import com.example.restful_web_services.pojo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoJpaRepository extends JpaRepository<Todo,Long> {

    List<Todo> findByUsername(String username);

    @Query(value = "SELECT t FROM Todo t WHERE t.username = ?1 and t.id = ?2")
    Optional<Todo> findByUsernameAndId(String username, long id);

    //Transactional and Modifying annotations necessary
    //initiate and commit the transaction.
    //Update/ Delete queries must be annotated with @Modifying, otherwise an InvalidDataAccessApiUsageException will be thrown.
    @Transactional
    @Modifying
    @Query("DELETE FROM Todo t WHERE t.username = :username AND t.id = :id")
    void deleteBooksByTitle(@Param("username") String username, @Param("id") long id);

}
