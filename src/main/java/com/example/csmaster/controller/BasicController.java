package com.example.csmaster.controller;

import com.example.csmaster.model.Customer;
import com.example.csmaster.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class BasicController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Retrieve all customer details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All customers are listed", content = @Content),
            @ApiResponse(responseCode = "404",description = "Entity not found",content = @Content),
            @ApiResponse(responseCode = "500",description = "Invalid customer id or other server-side error",content = @Content)})
    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    @Operation(summary = "Retrieve customer details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer details by specific id will be shown",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404",description = "Entity not found",content = @Content),
            @ApiResponse(responseCode = "500",description = "Invalid customer id or other server-side error",content = @Content)})
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @Operation(summary = "Create new customer with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New customer is added to database",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "500",description = "Invalid customer id or other server-side error",content = @Content)})
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer data) {
        return customerService.createCustomer(data);
    }
    @Operation(summary = "Update customer details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer by that Id is updated",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404",description = "Entity not found",content = @Content),
            @ApiResponse(responseCode = "500",description = "Invalid customer id or other server-side error",content = @Content)})
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer newDetails) {
        return customerService.updateCustomer(id,newDetails);
    }


    @Operation(summary = "Delete a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers by that Id is deleted", content = @Content),
            @ApiResponse(responseCode = "404",description = "Entity not found",content = @Content),
            @ApiResponse(responseCode = "500",description = "Invalid customer id or other server-side error",content = @Content)})
    @DeleteMapping("/{id}")
    public String deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }

}