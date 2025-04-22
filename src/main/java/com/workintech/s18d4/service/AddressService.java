package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAll();
    Address find(Integer id);
    Address save(Address address);
    Address delete(Integer id);
}
