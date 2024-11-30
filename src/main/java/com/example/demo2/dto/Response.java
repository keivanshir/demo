package com.example.demo2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Long status;
    private String message;

    private AddressDto addressDto;
    private JobDto jobDto;
    private PersonDto personDto;

    private List<AddressDto> addressDtoList;
    private List<JobDto> jobDtoList;
    private List<PersonDto> personDtoList;
}
