package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactPositionRepository extends JpaRepository<ContactPosition,Integer> {

    public List<ContactPosition> findAllByContact(Contact contact);


}
