package com.example.demo2.mapper;

import com.example.demo2.dto.AddressDto;
import com.example.demo2.dto.JobDto;
import com.example.demo2.dto.PersonDto;
import com.example.demo2.entity.Address;
import com.example.demo2.entity.Job;
import com.example.demo2.entity.Person;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityMapper {

    public PersonDto mapToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());

        if (person.getAddressList() != null) {
                personDto.setAddressList(person.getAddressList().stream()
                        .map(this::mapToAddressDto)
                        .collect(Collectors.toList()));
        }

        if (person.getJob() != null) {
            personDto.setJobList(
                    person.getJob().stream()
                            .map(this::mapToJobDto)
                            .collect(Collectors.toList())
            );
        }

        return personDto;
    }

    public AddressDto mapToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setPostalCode(address.getPostalCode());
        return addressDto;
    }

    public JobDto mapToJobDto(Job job) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setJobName(job.getJobName());
        jobDto.setDescription(job.getDescription());
        if (job.getAddress() != null) {
            jobDto.setAddress(
                    job.getAddress().stream()
                            .map(this::mapToAddressDto)
                            .collect(Collectors.toList())
            );
        }
        return jobDto;
    }


    public Person mapToPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());

        if (personDto.getAddressList() != null) {
            person.setAddressList(personDto.getAddressList().stream()
                    .map(this::mapToAddress)
                    .toList());
        }

        if (personDto.getJobList() != null) {
            person.setJob(
                    personDto.getJobList().stream()
                            .map(this::mapToJob)
                            .toList()
            );
        }

        return person;
    }

    public Address mapToAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());
        return address;
    }

    public Job mapToJob(JobDto jobDto) {
        Job job = new Job();
        job.setId(jobDto.getId());
        job.setJobName(jobDto.getJobName());
        job.setDescription(jobDto.getDescription());
        if (jobDto.getAddress() != null) {
            job.setAddress(jobDto.getAddress().stream()
                    .map(this::mapToAddress)
                    .toList());
        }
        return job;
    }
}
