package com.example.StudentProject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Student {
    @Id
    @Column
    private int stdid;
    @Column
    private String fname;
    @Column
    private String lname;
    @Column
    private String courses;


    public Student(){}

    public Student(int stdid,String fname,String lname,String password) {
        this.fname = fname;
        this.lname = lname;
        this.stdid = stdid;
        this.password = password;

    }
    public Student(int stdid,String fname,String lname,String password,String courses){
        this.fname = fname;
        this.lname = lname;
        this.stdid = stdid;
        this.password = password;
        this.courses=courses;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Column
    private String password;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCourses() {
        return courses;
    }

    public int getStdid() {
        return stdid;
    }

}

