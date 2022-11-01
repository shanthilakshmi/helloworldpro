package com.example.StudentProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository sturep;
    @Autowired
    CourseService cs;

    private final EntityManagerFactory emf;



    public StudentService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /*public List<Student> getAllStudents(){
        List<Student> stu = new ArrayList<Student>();
        sturep.findAll().forEach(stu1 -> stu.add(stu1));
        return stu;
    }*/
    //saving the Student details
    public void saveOrUpdate(Student stu) { sturep.save(stu); }//done

    //deleting a course in student table that was deleted in course table
    public void delete(String coursen) { sturep.deleteByCourseName(coursen); }

    //updating course in student table that was updated in course table
    public void updateCourses(String coursen1, String coursen2) { sturep.updateCourse(coursen1,coursen2); }

    //changing password
    public void updatePassword(Student stu,String password){ sturep.updatePassword(stu.getStdid(),password); }  //done

    //getting student with particular id
    public Student findById(int id){ return sturep.findByStudID(id); }//done

    //checking the logged student is valid or not
    public Student findStudentLogin(int stdid,String password){ return sturep.findStudent(stdid,password); }  //done

    //getting the courses that a particular student registered
    public List<String> findCourse(int stdid){ return sturep.findCourses(stdid); }//done

    //registering a student with new course
    public void newStudCourse(Student s,String courses){ sturep.newStud(s.getStdid(),s.getFname(),s.getLname(),s.getPassword(),courses); }//done

    //public List<String> studCourses(int stdid){ return sturep.findCourses(stdid); }

    //list of available course
    public List<String> availableCourses(int stdid){
        List<String> c=cs.getAllCourseNames();
        List<String> co=sturep.findCourses(stdid);
        for(int i=0;i<co.size();i++){
            if(c.contains(co.get(i))){
                c.remove(co.get(i));
            }
        }
        return c;
    }

    public List<Object[]> courseConcat() {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
                .createQuery("select stdid,fname,group_concat(courses) from Student group by stdid,fname,lname");
        return query.getResultList();
    }
}
