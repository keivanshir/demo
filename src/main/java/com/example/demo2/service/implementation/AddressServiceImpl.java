package com.example.demo2.service.implementation;

import com.example.demo2.dto.AddressDto;
import com.example.demo2.entity.Address;
import com.example.demo2.exception.NotFoundException;
import com.example.demo2.mapper.EntityMapper;
import com.example.demo2.repository.AddressRepository;
import com.example.demo2.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private EntityMapper mapper;

    @Override
    public List<AddressDto> getAllAddresses(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return addressRepository.findAll(pageable)
                .stream()
                .map(address -> mapper.mapToAddressDto(address))
                .toList();
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());
        Address savedAddress = addressRepository.save(address);
        return mapper.mapToAddressDto(savedAddress);

    }

    @Override
    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());
        Address savedAddress = addressRepository.save(address);
        return mapper.mapToAddressDto(savedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
        addressRepository.delete(address);
    }
}
