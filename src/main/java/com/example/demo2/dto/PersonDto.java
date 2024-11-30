package com.example.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<AddressDto> addressList = new ArrayList<>();
    private List<JobDto> jobList = new ArrayList<>();
}
