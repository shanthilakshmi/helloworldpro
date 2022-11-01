package com.example.StudentProject;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceTest {
    @Autowired
    CourseRepository cr;
    @Autowired
    StudentRepository sr;

    @Test
    @Order(1)
    public void finfByCourseNameTest(){
         String res=cr.findByCoursename("DBMS");
         assertEquals("DS",res);
    }

    @Test
    @Order(2)
    public void saveCourseTest(){
        Course c=new Course(106,"Java");
        cr.save(c);
        Integer ans=106;
        assertEquals(ans,cr.findByCourseid(106));
    }

    @Test
    @Order(3)
    public void deleteCourseTest(){
        int id=105;
        String name=cr.findByCoursenameById(id);
        sr.deleteByCourseName(name);
        cr.deleteById(id);
        //cr.findByCourseid(103);
       assertEquals(null,cr.findByCourseid(id));
    }

    @Test
    @Order(4)
    public void UpdateCourseTest(){
        int id=101;
        String name="DBMS";
        Course c=new Course(id,name);
        String old_name=cr.findByCoursenameById(id);
        sr.updateCourse(name,old_name);
        cr.save(c);
        //cr.findByCourseid(103);
        assertEquals(name,cr.findByCoursenameById(id));
    }

    @Test
    @Order(5)
    public void findByCourseidTest(){
        int id=101;
        Integer res=cr.findByCourseid(id);
        assertEquals(id,res);
    }
}
