package com.example.StudentProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select courses from Student where stdid=?1")
    List<String> findCourses(int stdid);

    @Query("SELECT distinct t from Student t where t.stdid=?1 and t.password=?2")
    Student findStudent(int stdid,String password);

    @Modifying
    @Query(value = "insert into Student (stdid,fname,lname,password,courses) VALUES (:stdid,:fname,:lname,:password,:courses)", nativeQuery = true)
    @Transactional
    void newStud(@Param("stdid") int stdid, @Param("fname") String fname, @Param("lname") String lname, @Param("password") String password, @Param("courses") String courses);

    @Modifying
    @Query(value = "DELETE FROM Student WHERE courses = :coursen",nativeQuery = true)
    @Transactional
    void deleteByCourseName(@Param("coursen") String coursen);

    @Modifying
    @Transactional
    @Query(value = "update Student s set s.courses=:coursen1 WHERE s.courses = :coursen2",nativeQuery = true)
    void updateCourse(@Param("coursen1") String coursen1,@Param("coursen2") String coursen2);

    @Modifying
    @Transactional
    @Query(value = "update Student s set s.password=:password WHERE s.stdid = :stdid",nativeQuery = true)
    void updatePassword(@Param("stdid") int stdid,@Param("password") String password);

    @Query("select distinct s from Student s where s.stdid=?1")
    Student findByStudID(int stdid);
}