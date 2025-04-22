package com.workintech.s18d4.service;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer find(Integer id);
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer delete(Integer id);
}
