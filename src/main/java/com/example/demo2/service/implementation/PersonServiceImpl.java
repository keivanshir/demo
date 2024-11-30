package com.example.demo2.service.implementation;

import com.example.demo2.dto.PersonDto;
import com.example.demo2.entity.Person;
import com.example.demo2.exception.NotFoundException;
import com.example.demo2.mapper.EntityMapper;
import com.example.demo2.repository.PersonRepository;
import com.example.demo2.service.PersonService;
import com.example.demo2.specification.PersonSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private EntityMapper mapper;

    @Override
    public List<PersonDto> getAllPersons(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return personRepository.findAll(pageable)
                .stream()
                .map(person -> mapper.mapToPersonDto(person))
                .toList();
    }

    @Override
    public List<PersonDto> getAllPersonsByAddressId(Integer page, Integer size, Long addressId) {
        Pageable pageable = PageRequest.of(page, size);

        return personRepository
                .findAll(PersonSpecification.personsByAddressId(addressId), pageable)
                .getContent()
                .stream()
                .map(obj -> mapper.mapToPersonDto((Person) obj)).toList();
    }

    public List<PersonDto> getAllAddressByPersonId(Integer page, Integer size, Long personId) {
        Pageable pageable = PageRequest.of(page, size);
        return personRepository
                .findAllById(personId, pageable)
                .getContent()
                .stream()
                .map(obj -> mapper.mapToPersonDto(obj)).toList();
    }

    @Override
    public PersonDto savePerson(PersonDto personDto) {

        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());

        if (personDto.getAddressList() != null){
            person.setAddressList(personDto.getAddressList()
                    .stream()
                    .map(addressDto -> mapper.mapToAddress(addressDto))
                    .collect(Collectors.toList()));
        }

        if (personDto.getJobList() != null){
            person.setJob(personDto.getJobList()
                    .stream()
                    .map(jobDto -> mapper.mapToJob(jobDto))
                    .collect(Collectors.toList()));
        }

        Person savedPerson = personRepository.save(person);

        return mapper.mapToPersonDto(savedPerson);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {

        Person person = personRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("person not found"));
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());

        if (personDto.getAddressList() != null){
            person.setAddressList(personDto.getAddressList()
                    .stream()
                    .map(addressDto -> mapper.mapToAddress(addressDto))
                    .collect(Collectors.toList()));
        }

        if (personDto.getJobList() != null){
            person.setJob(personDto.getJobList()
                    .stream()
                    .map(jobDto -> mapper.mapToJob(jobDto))
                    .collect(Collectors.toList()));
        }

        return mapper.mapToPersonDto( personRepository.save(person));
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("person not found"));
        personRepository.delete(person);
    }
}
