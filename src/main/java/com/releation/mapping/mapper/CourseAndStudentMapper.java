package com.releation.mapping.mapper;

import java.util.stream.Collectors;

import com.releation.mapping.dto.CourseDTO;
import com.releation.mapping.dto.StudentDTO;
import com.releation.mapping.entity.Course;
import com.releation.mapping.entity.Student;
import com.releation.mapping.repository.CourseRepository;
import com.releation.mapping.repository.StudentRepository;


public class CourseAndStudentMapper {
	
	
	/**
     * Convert CourseDTO to Course Entity
     */
	public static Course toCourseEntity(CourseDTO courseDTO, StudentRepository studentRepository) {
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setTitle(courseDTO.getTitle());
		course.setPrice(courseDTO.getPrice());
		course.setDuration(courseDTO.getDuration());
	//	course.setStudents(courseDTO.getStudentIds()); Ye value set nahi ho rahi thi
		
		if (courseDTO.getStudentIds() != null && !courseDTO.getStudentIds().isEmpty()) {
            course.setStudents(courseDTO.getStudentIds()
            		.stream()
                    .map(studentId -> studentRepository.findById(studentId)
                            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId)))
                    .collect(Collectors.toSet()));
        }

		return course;
	}
	
	
	
	 /**
     * Convert StudentDTO to Student Entity
     */
	public static Student toStudentEntity(StudentDTO studentDTO, CourseRepository courseRepository) {
		Student student = new Student();
		student.setId(studentDTO.getId());
		student.setName(studentDTO.getName());
		student.setEmail(studentDTO.getEmail());
		student.setGender(studentDTO.getGender());
	//	student.setCourses(studentDTO.getCourseIds()); Ye Value set nahi ho rahi thi
		
		if(studentDTO.getCourseIds() != null && !studentDTO.getCourseIds().isEmpty()) {
			student.setCourses(studentDTO.getCourseIds()
					.stream()
                    .map(courseId -> courseRepository.findById(courseId)
                            .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId)))
                    .collect(Collectors.toSet()));
		}
		return student;
	}
	
	
	
	
	
	// To Convert to CourseDTO
	public static CourseDTO toCourseDTO(Course course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(course.getId());
		courseDTO.setTitle(course.getTitle());
		courseDTO.setDuration(course.getDuration());
		courseDTO.setPrice(course.getPrice());
		courseDTO.setStudentIds(course.getStudents()
				.stream()
				.map(Student::getId)
				.collect(Collectors.toList()));
		return courseDTO;
	}
	
	// To Convert to StudentDTO
	public static StudentDTO toStudentDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setGender(student.getGender());
		studentDTO.setCourseIds(student.getCourses()
				.stream()
				.map(Course::getId)
				.collect(Collectors.toList()));
		return studentDTO;
	}

}
