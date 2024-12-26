package com.releation.mapping.service;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.releation.mapping.dto.CourseDTO;
import com.releation.mapping.dto.StudentDTO;
import com.releation.mapping.entity.Course;
import com.releation.mapping.entity.Student;
import com.releation.mapping.exception.ResourceNotFoundException;
import com.releation.mapping.mapper.CourseAndStudentMapper;
import com.releation.mapping.repository.CourseRepository;
import com.releation.mapping.repository.StudentRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class StudentServiceImple implements StudentService {

	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImple.class);
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	
	
	// Add Student
	@Override
	public StudentDTO addStudent(StudentDTO studentDTO) {
		  logger.info("Add Student With Name :: {}",studentDTO.getName());
	      Student student = CourseAndStudentMapper.toStudentEntity(studentDTO,courseRepository );
	      Student savedStudent = studentRepository.save(student);
	      logger.info("{} :: Student Save Successfully",savedStudent.getName());
		return CourseAndStudentMapper.toStudentDTO(savedStudent);
	}

	// Get Student By id
	@Override
	public StudentDTO getStudentById(Long id) {
		Student student = studentRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Student is not exists with given id :"  + id));
		logger.info("Student Successfully Found With Name:: {}",student.getName());
		return CourseAndStudentMapper.toStudentDTO(student);
	}

	
	// Get All Student 
	@Override
	public List<StudentDTO> getAllStudent() {
		List<Student> students = studentRepository.findAll();
		logger.info("{} :: Student Successfully found ",students.size());
		return students.stream().map(CourseAndStudentMapper::toStudentDTO).collect(Collectors.toList());
	}

	// Update  Student By Id
	@Override
	public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
		
		// Fetch ExistingStudent from database
		Student ExistingStudent = studentRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Student is not exists with given id :"  + id));
		logger.info("{} :: Student Successfully Found With Name ",ExistingStudent.getName());
		
		
		// Update basic fields
		ExistingStudent.setId(studentDTO.getId());
		ExistingStudent.setName(studentDTO.getName());
		ExistingStudent.setGender(studentDTO.getGender());
		ExistingStudent.setEmail(studentDTO.getEmail());
	//	ExistingStudent.setCourses(studentDTO.getCourseIds()); ye set nahi ho rahi thi
		
		
		// Update relationships with courses
		if(studentDTO.getCourseIds() != null && !studentDTO.getCourseIds().isEmpty()) {
			 Set<Course> updatedCourses = studentDTO.getCourseIds()
					.stream()
					.map(courseId -> courseRepository.findById(courseId)
					        .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId)))
			        .collect(Collectors.toSet());
			ExistingStudent.setCourses(updatedCourses);
		}
		
		// Updated Student Save into database
		Student savedStudent = studentRepository.save(ExistingStudent);
		logger.info("{}:: Student Successfully Update",savedStudent.getName());
		return CourseAndStudentMapper.toStudentDTO(savedStudent);
	}

	
	//  Delete Student By Id
	@Override
	public void deleteStudent(Long id) {
		Student student = studentRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Student is not exists with given id :"  + id));
		studentRepository.delete(student);
		logger.info("Student Successfully Deleted With Name::{}",student.getName());
		
	}

	// Search Student By name email and gender
	@Override
	public List<StudentDTO> searchStudent(String name, String email, String gender) {
		List<Student> students = studentRepository.searchStudent(name, email, gender);
		if (students.isEmpty()) {
            throw new ResourceNotFoundException("No Student found in with :{} " + name);
        }
	   //.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		logger.info("{}:: Student Successfully Found",name, email, gender);
		return students.stream().map(CourseAndStudentMapper::toStudentDTO).collect(Collectors.toList());
	}

	
	// Search Student By Query This is beter than above searchStudent method
	@Override
	public List<StudentDTO> searchStudentByQuery(String query) {
		List<Student> students = studentRepository.SearchStudentByQuery(query);
			if (students.isEmpty()) {
	            throw new ResourceNotFoundException("No Student found in with :{} " + query);
	        }
		   //.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
	  return students.stream().map(CourseAndStudentMapper::toStudentDTO).collect(Collectors.toList());
	}


	
	
	
	
	
	/* This Section Represent to CourseEntity*/
	
	
	
	// Add Course
	@Override
	public CourseDTO addCourse(CourseDTO courseDTO) {
		Course course = CourseAndStudentMapper.toCourseEntity(courseDTO, studentRepository);
	    Course savedCourse = courseRepository.save(course);
	    logger.info("{} Course Successfully add",savedCourse.getTitle());
		return CourseAndStudentMapper.toCourseDTO(savedCourse);
	}

	
	// Get Course By id
	@Override
	public CourseDTO getCourseById(Long id) {
		Course course = courseRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Course is not exists with given id :"  + id));
		logger.info("{} :: Course Successfully found");
		return CourseAndStudentMapper.toCourseDTO(course);
	}

	// Get All Course
	@Override
	public List<CourseDTO> getAllCourse() {
		List<Course> courses = courseRepository.findAll();
		logger.info("{} :: Courses Found Successfully",courses.size());
		return courses.stream().map(CourseAndStudentMapper::toCourseDTO).collect(Collectors.toList());
	}

	
	// Update Course By id
	@Override
	public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
		
		 // Fetch the course from the database
		Course existingCourse = courseRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Course is not exists with given id :"  + id));
		
		
		 // Update basic fields
		existingCourse.setId(courseDTO.getId());
		existingCourse.setTitle(courseDTO.getTitle());
		existingCourse.setDuration(courseDTO.getDuration());
		existingCourse.setPrice(courseDTO.getPrice());
		//existingCourse.setStudents(courseDTO.getStudentIds()); ye set nahi ho rahi thi
		
		// Update relationships with students
		if (courseDTO.getStudentIds() != null && !courseDTO.getStudentIds().isEmpty()) {
	        Set<Student> updatedStudents = courseDTO.getStudentIds()
	        		.stream()
	                .map(studentId -> studentRepository.findById(studentId)
	                        .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId)))
	                .collect(Collectors.toSet());
	        existingCourse.setStudents(updatedStudents);
	    }
		
		// Save the updated course
	    Course savedCourse = courseRepository.save(existingCourse);
	    logger.info("{} :: Course Update Successfully With Title:",savedCourse.getTitle());
	    return CourseAndStudentMapper.toCourseDTO(savedCourse);
	}

	// Delete Course
	@Override
	public void deleteCourse(Long id) {
		Course course = courseRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Course is not exists with given id :"  + id));
		courseRepository.deleteById(id);
		logger.info("{} :: Course Delete Successfully ",course.getTitle());
	}

	// Search Course
	@Override
	public List<CourseDTO> searchCourse(String title, Integer price, String duration) {
		List<Course> courses = courseRepository.searchCourse(title, duration, price);
			if (courses.isEmpty()) {
	            throw new ResourceNotFoundException("No course found in with :{} " + title);
	        }
		   //.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		logger.info("Course Successfully Found as {}", title,duration,price);
		return courses.stream().map(CourseAndStudentMapper::toCourseDTO).collect(Collectors.toList());
	}

}
