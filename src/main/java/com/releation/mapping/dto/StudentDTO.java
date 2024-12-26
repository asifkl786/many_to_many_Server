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
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Long> getCourseIds() {
		return courseIds;
	}
	public void setCourseIds(List<Long> courseIds) {
		this.courseIds = courseIds;
	}
    
}

