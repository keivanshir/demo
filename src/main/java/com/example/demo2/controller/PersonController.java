package com.example.demo2.controller;

import com.example.demo2.dto.AddressDto;
import com.example.demo2.dto.PersonDto;
import com.example.demo2.dto.Response;
import com.example.demo2.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Response> getAllPersons(@PathVariable Integer page,
                                                  @PathVariable Integer size){
        Response response = Response.builder()
                .personDtoList(personService.getAllPersons(page, size))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all/byAddress/{page}/{size}/{id}")
    public ResponseEntity<Response> getAllPersonsByAddressId(@PathVariable Integer page,
                                                             @PathVariable Integer size,
                                                             @PathVariable Long id){
        Response response = Response.builder()
                .personDtoList(personService.getAllPersonsByAddressId(page, size, id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/all/byId/{page}/{size}/{personId}")
    public ResponseEntity<Response> getAllAddressesByPersonId(@PathVariable Integer page,
                                                             @PathVariable Integer size,
                                                             @PathVariable Long personId){
        List<AddressDto> addressDtoList = personService.getAllAddressByPersonId(page, size, personId)
                .stream()
                .map(PersonDto::getAddressList)
                .toList()
                .get(0);
        Response response = Response.builder()
                .addressDtoList(addressDtoList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<Response> savePerson(@RequestBody PersonDto personDto){
        Response response = Response.builder()
                .personDto(personService.savePerson(personDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        Response response = Response.builder()
                .personDto(personService.updatePerson(id, personDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        Response response = Response.builder()
                .message("person deleted successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
