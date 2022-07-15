package com.external.files.readdata.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "film")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "height")
	private String height;
	
	@Column(name = "addresses")
	public String addresses;
	
	@Column(name = "idNumber")
	public String idNumber;
	
	@Column(name = "passportNumber")
	public String passportNumber;
	
	@Column(name = "nationality")
	public String nationality;
	
	@Column(name = "dateCreated")
	public String dateCreated;
	
	@Column(name = "lastUpdate")
	public String lastUpdate;
	
	@Column(name = "active")
	public boolean active;
	
	@Column(name = "lastUpdated")
	public String lastUpdated;
	
	@Column(name = "expiryDate")
	public String expiryDate;
	
	@Transient
	private MultipartFile file;
	
	public Customer() {
		
	}
	

	public Customer(Long id, String name, String surname, String age, String height, String addresses, String idNumber,
			String passportNumber, String nationality, String dateCreated, String lastUpdate, boolean active,
			String lastUpdated, String expiryDate, MultipartFile file) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.height = height;
		this.addresses = addresses;
		this.idNumber = idNumber;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
		this.dateCreated = dateCreated;
		this.lastUpdate = lastUpdate;
		this.active = active;
		this.lastUpdated = lastUpdated;
		this.expiryDate = expiryDate;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAge() {
		return age;
	}

	public String getHeight() {
		return height;
	}

	public String getAddresses() {
		return addresses;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public boolean isActive() {
		return active;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
	
	//Constructors
	
