package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Capa de manipulacion de objetos
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    JPQL
    @Query("select u from User u where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("select u from User u where u.email like %?1%")
    List<User> findAndSort(String name, Sort sort);

//    Query methods
    List<User> findByName(String name);
    Optional<User> findByEmailAndName(String email, String name);
}
