package com.asseco.demo.usercontacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactJpaRepository extends JpaRepository<UserContact, Long> {

}
