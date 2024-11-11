package com.contactapi.entity;

import org.hibernate.annotations.UuidGenerator;

import com.contactapi.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="contacts")
public class Contact {

	@Id
	@UuidGenerator
	@Column(name="id",unique = true,nullable = false)
	private String id;
	private String name;
	private String email;
	private String title;
	@Embedded
	private Address address;
	private long phone;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String photoUrl;
}
