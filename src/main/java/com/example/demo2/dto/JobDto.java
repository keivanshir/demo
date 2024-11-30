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
public class JobDto {
    private Long id;
    private String jobName;
    private String description;
    private List<AddressDto> address = new ArrayList<>();
}
