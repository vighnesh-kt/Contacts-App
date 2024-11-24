package com.contactapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.PutExchange;

import com.contactapi.constant.Constant;
import com.contactapi.entity.Contact;
import com.contactapi.service.ContactServiceImplementation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;


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
	@PutMapping("/image")
	public String insertImage(@RequestParam String id,@RequestParam("file")MultipartFile file) {
		//TODO: process POST request
		
		return contactService.uploadPhoto(id, file);
	}
	
	@GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(Constant.PHOTO_DIRECTORY + filename));
    }
	
	@GetMapping("/getcontact")
	public Contact getMethodName(@RequestParam String id) {
		return contactService.getContact(id);
	}
	
	
	
	
	
	

}
