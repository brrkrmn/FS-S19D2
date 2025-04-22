package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AddressService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    private CustomerService customerService;

    @Autowired
    public AddressController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @GetMapping()
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable Integer id) {
        return addressService.find(id);
    }

    @PostMapping()
    public Address save(@RequestBody Address address) {
        Customer customer = customerService.find(address.getCustomer().getId());
        customer.setAddress(address);
        address.setCustomer(customer);
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Integer id, @RequestBody Address address) {
        Customer customer = customerService.find(address.getCustomer().getId());
        customer.setAddress(address);
        address.setCustomer(customer);
        return addressService.save(address);
    }

    @DeleteMapping("/{id}")
    public Address delete(@PathVariable Integer id) {
        return addressService.delete(id);
    }
}
