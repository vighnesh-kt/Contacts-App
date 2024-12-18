package com.contactapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.contactapi.entity.Contact;
import com.contactapi.repository.ContactRepository;

@Repository
public class ContactDao {
	
	@Autowired
	private ContactRepository contactRepository;

	public Page<Contact> findAll(Integer page, Integer size) {
	    return contactRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
	}
	
	public Optional<Contact> getContact(String id) {
		return contactRepository.findById(id);
	}

	public Contact createContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepository.save(contact);
		
	}
	
	
	public boolean deleteContact(String id) {
		if (contactRepository.existsById(id)) { 
			contactRepository.deleteById(id); 
			return true; 
		}
		return false;

	}

	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	public Contact findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return contactRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
	}
	

}
