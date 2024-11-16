package com.contactapi.service;

import org.springframework.data.domain.Page;

import com.contactapi.entity.Contact;

public interface ContactService {
	
	Page<Contact> getAll(int page);
	
	

}
