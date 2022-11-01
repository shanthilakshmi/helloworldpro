package com.example.StudentProject;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
public class StudentServiceTest {
    @Autowired
    StudentRepository sr;

    @Test
    @Order(1)
    public void saveOrUpdateTest(){
        Student s=new Student(1008,"Aryaan","Pandey","abcd");
        sr.save(s);
        Student res=sr.findByStudID(1007);
        assertEquals(s.getStdid(),res.getStdid());

    }

    @Test
    @Order(2)
    public void findCourseTest(){
        List<String> res=sr.findCourses(1001);
        List<String> ans=new ArrayList<>();
        ans.add(null);
        ans.add("DBMS");
        assertEquals(ans,res);
    }

    @Test
    @Order(3)
    public void newStudeCourseTest(){
        Student s=new Student(1006,"Alizeh","Hammock","abcd","Artificial Intelligence");
        sr.newStud(s.getStdid(),s.getFname(),s.getLname(),s.getPassword(),s.getCourses());
        List<String> ans=new ArrayList<>();
        ans.add(null);
        ans.add("Artificial Intelligence");
        assertEquals(ans,sr.findCourses(s.getStdid()));
    }

    @Test
    @Order(4)
    public void findStudentLoginTest(){
        Student s=sr.findStudent(1001,"potter");
        assertEquals(sr.findByStudID(1001).getStdid(),s.getStdid());
    }

    @Test
    @Order(5)
    public void updatePassword(){
        sr.updatePassword(1001,"pot");
        Student s=sr.findByStudID(1001);
        assertEquals("potter",s.getPassword());
    }
}
