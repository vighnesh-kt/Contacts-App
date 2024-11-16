package com.contactapi.service;

import java.nio.file.Files;
import com.contactapi.constant.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.contactapi.dao.ContactDao;
import com.contactapi.entity.Contact;

public class ContactServiceImplementation implements ContactService {

	@Autowired
	private ContactDao contactDao;

	@Override
	public Page<Contact> getAll(int page) {

		return contactDao.findAll(page);
	}

	public Contact getContact(String id) {
		return contactDao.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
	}

	public Contact createContact(Contact contact) {
		return contactDao.createContact(contact);
	}

	public void removeContact(String id) {
		contactDao.deleteContact(id);
	}

	public String uploadPhoto(String id, MultipartFile file) {
		Contact c = getContact(id);
		String photoUrl = null;
		c.setPhotoUrl(photoUrl);
		contactDao.createContact(c);
		return photoUrl;
	}

	private final Function<String, String> fileExtension = filename -> Optional.of(filename)
			.filter(name -> name.contains(".")).map(name -> "." + name.substring(filename.lastIndexOf(".") + 1))
			.orElse("-png"); // Default to "-png" if no extension is found

	private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
		String filename = id + fileExtension.apply(image.getOriginalFilename());

		try {
			// Define the file storage location
			Path fileStorageLocation = Paths.get(Constant.PHOTO_DIRECTORY).toAbsolutePath().normalize();

			// Create directories if they don't exist
			if (!Files.exists(fileStorageLocation)) {
				Files.createDirectories(fileStorageLocation);
			}

			// Copy the image to the storage location
			Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename),
					StandardCopyOption.REPLACE_EXISTING);

			// Return the URI of the stored image
			return ServletUriComponentsBuilder.fromCurrentContextPath().path("/contacts/image/" + filename)
					.toUriString();
		} catch (Exception exception) {
			throw new RuntimeException("Unable to save image", exception);
		}
	};
}
