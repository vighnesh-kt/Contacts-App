package com.contactapi.service;

import java.nio.file.Files;
import com.contactapi.constant.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.contactapi.dao.ContactDao;
import com.contactapi.entity.Contact;

@Service
public class ContactServiceImplementation implements ContactService {

	@Autowired
	private ContactDao contactDao;

	public Contact getContact(String id) {
		return contactDao.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
	}

	public Contact createContact(Contact contact) {
		return contactDao.createContact(contact);
	}

	public boolean removeContact(String id) {
		return contactDao.deleteContact(id);
	}

	  public String uploadPhoto(String id, MultipartFile file) {
	        Contact contact = getContact(id);
	        String photoUrl = photoFunction.apply(id, file);
	        contact.setPhotoUrl(photoUrl);
	        contactDao.createContact(contact);
	        return photoUrl;
	    }

	    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
	            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

	    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
	        String filename = id + fileExtension.apply(image.getOriginalFilename());
	        try {
	            Path fileStorageLocation = Paths.get(Constant.PHOTO_DIRECTORY).toAbsolutePath().normalize();
	            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
	            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
	            return ServletUriComponentsBuilder
	                    .fromCurrentContextPath()
	                    .path("/contacts/image/" + filename).toUriString();
	        }catch (Exception exception) {
	            throw new RuntimeException("Unable to save image");
	        }
	    };

	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return contactDao.findAll();
	}

	public Page<Contact> getAll(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return contactDao.findAll(page,size);
	}

	public Contact updateContact(String id, Contact contact) throws Exception {
	    // Fetch the existing contact by ID
	    Contact oldContact = contactDao.findById(id);

	    // Update the fields of the old contact with the new data
	    oldContact.setName(contact.getName());
	    oldContact.setEmail(contact.getEmail());
	    oldContact.setTitle(contact.getTitle());
	    oldContact.setAddress(contact.getAddress());
	    oldContact.setPhone(contact.getPhone());
	    oldContact.setStatus(contact.getStatus());
	    oldContact.setPhotoUrl(contact.getPhotoUrl());

	    // Save the updated contact
	    return contactDao.createContact(oldContact);
	}

}
