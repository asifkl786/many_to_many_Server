package com.releation.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.releation.mapping.entity.Course;


public interface CourseRepository extends JpaRepository<Course,Long> {
	
	// Find By name and age
    @Query("SELECT c FROM Course c WHERE " +
            "(:title IS NULL OR c.title = :title) AND " +
            "(:duration IS NULL OR c.duration = :duration) AND " +
            "(:price IS NULL OR c.price = :price)")
     List<Course> searchCourse(@Param("title") String title, @Param("duration") String duration, @Param("price") Integer price);

}
