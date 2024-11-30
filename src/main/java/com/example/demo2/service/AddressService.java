package com.example.demo2.service;

import com.example.demo2.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAllAddresses(Integer page, Integer size);

    AddressDto saveAddress(AddressDto addressDto);

    AddressDto updateAddress(Long id, AddressDto addressDto);

    void deleteAddress(Long id);

}
