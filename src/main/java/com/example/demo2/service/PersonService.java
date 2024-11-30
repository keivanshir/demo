package com.example.demo2.service;

import com.example.demo2.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAllPersons(Integer page, Integer size);
    List<PersonDto> getAllPersonsByAddressId(Integer page, Integer size, Long addressId);
    List<PersonDto> getAllAddressByPersonId(Integer page, Integer size, Long personId);
    PersonDto savePerson(PersonDto personDto);
    PersonDto updatePerson(Long id, PersonDto personDto);
    void deletePerson(Long id);
}
