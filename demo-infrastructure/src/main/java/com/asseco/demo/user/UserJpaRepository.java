package com.asseco.demo.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "userWithContacts", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT u FROM User u")
    Set<User> findAllWithContacts();

    @EntityGraph(value = "userWithContacts", type = EntityGraph.EntityGraphType.FETCH)
    Page<User> findAll(Specification<User> specification, Pageable pageable);
}
