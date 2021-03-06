package com.external.files.readdata.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.external.files.readdata.Model.Customer;
import com.external.files.readdata.Repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Transactional
public class CustomerServiceImplementation implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadfile(MultipartFile file) {
		// TODO Auto-generated method stub
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		if (extension.equalsIgnoreCase("json")) {
			isFlag = readDataFromJson(file);
		}
		return isFlag;
	}


	
	//JSON External Data File
	private boolean readDataFromJson(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Customer> customers = Arrays.asList(mapper.readValue(inputStream, Customer[].class));
			if(customers!= null && customers.size()>0) {
				for (Customer customer : customers) {
					//customer.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					customerRepository.save(customer);
				}
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	

	

}
