package com.external.files.readdata.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.external.files.readdata.Model.Customer;

public interface CustomerService {

	List<Customer> findAll();

	boolean saveDataFromUploadfile(MultipartFile file);

}
