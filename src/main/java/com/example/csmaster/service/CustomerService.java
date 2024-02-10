package com.example.csmaster.service;

import com.example.csmaster.model.Customer;
import com.example.csmaster.exception.CustomerNotFoundException;
import com.example.csmaster.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomerById(Long id) {
        if(!customerRepository.existsById(id)) throw new CustomerNotFoundException();
        return customerRepository.findById(id).get();
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer data) {
        Customer newCustomer = new Customer();
        newCustomer.setName(data.getName());
        newCustomer.setDescriptions(data.getDescriptions());
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    public Customer updateCustomer(Long id, Customer newDetails) {
        if(!customerRepository.existsById(id)) throw new CustomerNotFoundException();

        Customer customer = customerRepository.findById(id).get();
        customer.setName(newDetails.getName());
        customer.setDescriptions(newDetails.getDescriptions());
        customerRepository.save(customer);
        return customer;
    }

    public String deleteCustomerById(Long id) {
        if(!customerRepository.existsById(id)) throw new CustomerNotFoundException();
        customerRepository.deleteById(id);
        return "User with id " + id + " is deleted.";
    }
}
