package com.example.demo2.controller;

import com.example.demo2.dto.AddressDto;
import com.example.demo2.dto.Response;
import com.example.demo2.service.implementation.AddressServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/address")
public class AddressController {

    private AddressServiceImpl addressService;

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Response> getAllAddresses(@PathVariable("page") Integer page,
                                                    @PathVariable("size") Integer size){
        Response response = Response.builder()
                .addressDtoList(addressService.getAllAddresses(page, size))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveAddress(@RequestBody AddressDto addressDto){
        Response response = Response.builder()
                .addressDto(addressService.saveAddress(addressDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto){
        Response response = Response.builder()
                .addressDto(addressService.updateAddress(id, addressDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        Response response = Response.builder()
                .message("address deleted successfully!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
