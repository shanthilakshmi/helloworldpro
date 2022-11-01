package com.example.StudentProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courep;

    public List<Course> getAllCourses(){
        List<Course> course = new ArrayList<Course>();
        courep.findAll().forEach(stu1 -> course.add(stu1));
        Collections.sort(course);
        return course;
    }

    public void saveOrUpdate(Course course)
    {

        courep.save(course);//done
    }

    public void delete(int id)
    {
        courep.deleteById(id);
    }


    public Integer getCourseById(int courseid)
    {
        return courep.findByCourseid(courseid);//done
    }

    public String getCourseByName(String coursename){
        return courep.findByCoursename(coursename);//done
    }

    public List<String> getAllCourseNames(){
        return courep.findAllCourseNames();
    }

    public String getCourseNameById(int courseid){
        return courep.findByCoursenameById(courseid);
    }

}
