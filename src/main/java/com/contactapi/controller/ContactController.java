package com.contactapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contactapi.entity.Contact;
import com.contactapi.service.ContactServiceImplementation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactServiceImplementation contactService;

	@GetMapping("getpage")
	public Page<Contact> getPage(@RequestParam Integer page) {
		return contactService.getAll(page);
	}
	
	@GetMapping("/findall")
	public List<Contact> getAll() {
		return contactService.findAll();
	}
	
	@PostMapping("/createcontact")
	public Contact createContact(@RequestBody Contact contact) {
		return contactService.createContact(contact);
	}
	
	
	
	

}
