package com.releation.mapping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.releation.mapping.dto.CourseDTO;
import com.releation.mapping.dto.StudentDTO;
import com.releation.mapping.service.StudentService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;
	
	
	// Build Add Student REST API
	@PostMapping("/addStudent")
	public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
		logger.info("Recived Request to Add Student With Name:: {}",studentDTO.getName());
		StudentDTO student = studentService.addStudent(studentDTO);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	
	// Build Get Student By Id REST API
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){
		logger.info("Recived Request to get Student With Name:: {}",id);
		StudentDTO student = studentService.getStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	
	// Build Get All Student REST API
	@GetMapping("/getAll")
	public ResponseEntity<List<StudentDTO>> getAllStudent(){
		logger.info("Recived Request to getAll Student");
		List<StudentDTO> students = studentService.getAllStudent();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	
	// Build Update Student REST API
	@PutMapping("/update/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
		logger.info("Recived Request to Update Student With Name::{}",studentDTO.getName());
		StudentDTO student = studentService.updateStudent(id, studentDTO);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	
	// Build Delete Student REST API
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id){
		logger.info("Recived Request To Delete Student By Id:: {}",id);
	    studentService.deleteStudent(id);
		return new ResponseEntity<>("Student Successfully Deleted", HttpStatus.OK);
	}
	
	
	// Build Search Student REST API
	@GetMapping("/searchStudent")
	public ResponseEntity<List<StudentDTO>> searchStudent(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String gender){
		
		logger.info("Recived Request to Search Student By :: {}",name,email,gender);
		List<StudentDTO> students = studentService.searchStudent(name, email, gender);
		return new ResponseEntity<>(students,HttpStatus.OK) ;
	}
	
	// Build Search Student REST API
		@GetMapping("/searchStudents")
		public ResponseEntity<List<StudentDTO>> searchStudentByQuery(@RequestParam(required = false) String query){
			logger.info("Recived Request to Search Student By :: {}",query);
			List<StudentDTO> students = studentService.searchStudentByQuery(query);
			return new ResponseEntity<>(students,HttpStatus.OK) ;
		}
	
	
	
	
	
	
	/* This Section Represent to CourseEntity*/
	
	// Build Add Course REST API
	@PostMapping("/addCourse")
	public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO){
		logger.info("Recived Request to add course with title::{}",courseDTO.getTitle());
		CourseDTO course = studentService.addCourse(courseDTO);
		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}
	
	// Build getCourse By id REST API
	@GetMapping("/getCourse/{id}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id){
		logger.info("Recived Request to get Course by Id: {}",id);
		CourseDTO coursee = studentService.getCourseById(id);
		return new ResponseEntity<>(coursee, HttpStatus.OK);
	}
	
	// Build getAll Course REST API
	@GetMapping("/getAllCourse")
	public ResponseEntity<List<CourseDTO>> getAllCourse(){
		logger.info("Recived Request to get all Course");
		List<CourseDTO> courses = studentService.getAllCourse();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	// Build update Course REST API
	@PutMapping("/updateCourse/{id}")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO){
		logger.info("Recived Request to update Course with id: {}",id);
		CourseDTO updatedCourse = studentService.updateCourse(id, courseDTO);
		return new ResponseEntity<>(updatedCourse , HttpStatus.OK);
	}
	
	// Build Delete Course REST API
	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id){
		logger.info("Recived Request to Delete Course With Id:: {}",id);
		studentService.deleteCourse(id);
		return new ResponseEntity<>(" Course Delete Successfull", HttpStatus.OK);
	}
	
	// Build Search Course REST API
	@GetMapping("/search")
	public ResponseEntity<List<CourseDTO>> search(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) String duration,
			@RequestParam(required = false) Integer price){
		logger.info("Recived Request to Search Course By :: {}",title, price, duration);
		List<CourseDTO> courses = studentService.searchCourse(title, price, duration);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	

}
