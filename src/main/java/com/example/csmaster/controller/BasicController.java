package com.example.csmaster.controller;

import com.example.csmaster.model.Customer;
import com.example.csmaster.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class BasicController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer data) {
        return customerService.createCustomer(data);
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer newDetails) {
        return customerService.updateCustomer(id,newDetails);
    }


    @DeleteMapping("/{id}")
    public String deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }

}