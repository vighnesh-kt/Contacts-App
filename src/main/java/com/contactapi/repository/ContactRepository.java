package com.contactapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactapi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, String>{

	
}
