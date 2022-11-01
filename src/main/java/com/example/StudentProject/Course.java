package com.example.StudentProject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Course implements Comparable{
    @Id
    @Column
    int courseid;
    @Column
    String coursename;


    public Course() {
    }

    public Course(int courseid,String coursename){
        this.courseid=courseid;
        this.coursename=coursename;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Override
    public int compareTo(Object o) {
        return (this.getCourseid() < ((Course) o).getCourseid() ? -1 : (this.getCourseid() == ((Course) o).getCourseid() ? 0 : 1));
    }
}
