package com.releation.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.releation.mapping.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	// Find By name and age
    @Query("SELECT c FROM Student c WHERE " +
            "(:name IS NULL OR c.name = :name) AND " +
            "(:email IS NULL OR c.email = :email) AND " +
            "(:gender IS NULL OR c.gender = :gender)")
     List<Student> searchStudent(@Param("name") String name, @Param("email") String email, @Param("gender") String gender);

    
 // This is custom search method JPQL QUERY This is better approach for above
    @Query("SELECT p FROM Student p WHERE " +
           "p.name LIKE CONCAT('%', :query, '%')" +
            "Or p.email LIKE CONCAT('%', :query, '%')" +
            "Or p.gender LIKE CONCAT('%', :query, '%')" )
    List<Student> SearchStudentByQuery(String query);

}
