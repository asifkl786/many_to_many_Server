package com.releation.mapping.service;

import java.util.List;

import com.releation.mapping.dto.CourseDTO;
import com.releation.mapping.dto.StudentDTO;

public interface StudentService {
	
	StudentDTO addStudent(StudentDTO studentDTO);
	StudentDTO getStudentById(Long id);
	List<StudentDTO> getAllStudent();
	StudentDTO updateStudent(Long id, StudentDTO studentDTO);
	void deleteStudent(Long id);
	List<StudentDTO> searchStudent(String name, String email, String gender);
	List<StudentDTO> searchStudentByQuery(String query);
	
	
	/* This Section represent to Course Entity*/
	CourseDTO addCourse(CourseDTO courseDTO);
	CourseDTO getCourseById(Long id);
	List<CourseDTO> getAllCourse();
	CourseDTO updateCourse(Long id,CourseDTO courseDTO);
	void deleteCourse(Long id);
	List<CourseDTO> searchCourse(String title,Integer price,String duration );

}
