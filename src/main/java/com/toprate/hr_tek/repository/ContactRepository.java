package com.toprate.hr_tek.repository;

import com.toprate.hr_tek.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
