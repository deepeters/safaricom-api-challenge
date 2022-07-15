package com.external.files.readdata.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.external.files.readdata.Model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
