package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {
    // Trả về Các Contact đã active
    @Query("SELECT c FROM Contact c WHERE c.isEnable = true")
    Collection<Contact> findByIs_enableTrue();

    Optional<Contact> findByEmail1(String gmail1);

    Optional<Contact> findByEmail2(String gmail2);

    Optional<Contact> findByPhone1(String phone1);

    Optional<Contact> findByPhone2(String phone2);


}
