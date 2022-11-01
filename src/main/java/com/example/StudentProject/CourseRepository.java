package com.example.StudentProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query("SELECT coursename FROM Course  WHERE coursename =?1 ")
    String findByCoursename(String c);

    @Query("SELECT courseid FROM Course  WHERE courseid =?1 ")
    Integer findByCourseid(int c1);

    @Query("select coursename from Course")
    List<String> findAllCourseNames();

    @Query("SELECT coursename FROM Course  WHERE courseid =?1 ")
    String findByCoursenameById(int c1);
}
