package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerService.findAll();
        return customers.stream().map(c -> new CustomerResponse(
                c.getId(),
                c.getEmail(),
                c.getSalary()
        )).toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Integer id) {
        Customer customer = customerService.find(id);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }

    @PostMapping()
    public CustomerResponse save(@RequestBody Customer customer) {
        Customer c = customerService.save(customer);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer c = customerService.save(customer);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Integer id) {
        Customer customer = customerService.delete(id);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }
}
