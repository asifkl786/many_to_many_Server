package com.releation.mapping.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private List<Long> courseIds;
   
}

