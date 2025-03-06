package com.releation.mapping.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private Integer price;
    private String duration;
    private List<Long> studentIds;
   
}

