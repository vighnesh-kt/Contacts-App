package com.contactapi.service;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.contactapi.dao.ContactDao;
import com.contactapi.entity.Contact;

public class ContactServiceImplementation implements ContactService{

	@Autowired
	private ContactDao contactDao;
	
	@Override
	public Page<Contact> getAll(int page) {
		
		return contactDao.findAll(page);
	}
	
	public Contact getContact(String id) {
		return contactDao.getContact(id).orElseThrow(()->new RuntimeException("Contact not found"));
	}
	
	public Contact createContact(Contact contact) {
		return contactDao.createContact(contact);
	}
	
	public void removeContact(String id) {
		contactDao.deleteContact(id);
	}
	
	
	public String uploadPhoto(String id,MultipartFile file) {
		Contact c=getContact(id);
		String photoUrl=null;
		c.setPhotoUrl(photoUrl);
		contactDao.createContact(c);
		return photoUrl;
	}
	
	private final BiFunction<String,MultipartFile,String> photoFunction=(id,image)->{
		try {
			
			Path fileStorageLocation=Paths.get("").toAbsolutePath().normalize();
			
		} catch (Exception e) {
			throw new RuntimeException("Unable to save image");
		}
	}
}
