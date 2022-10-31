package com.example.StudentProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService couser;
    @Autowired
    private StudentService stuservice;

    @GetMapping("/adminLogin")
    public String showAdminLogin(Model model){
        Course course=new Course();
        model.addAttribute("course",course);
        return "adminLogin";
    }

    @PostMapping("/checkAdminLogin")
    public String checkAdminPage(@ModelAttribute Course course,@ModelAttribute Student stu, Model model, @RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirAttrs){
        if(username.equals("Admin") && password.equals("hello")) {
            List<Object[]>o=stuservice.courseConcat();
            model.addAttribute("s",o);
            model.addAttribute("c", couser.getAllCourses());
            return "display_admin";
        }
        redirAttrs.addFlashAttribute("message", "Invalid Credentials");
        return "redirect:/adminLogin";
    }

    @PostMapping("/updatec")
    public String updateCourse(@RequestParam("coursename") String coursename1,@RequestParam("bookId") int courseid, Model model,@ModelAttribute Course course,@RequestParam("bookName") String coursename2){
        stuservice.updateCourses(coursename1,coursename2);
        Course c=new Course(courseid,coursename1);
        couser.saveOrUpdate(c);
        List<Course> l1=couser.getAllCourses();
        List<Object[]>o=stuservice.courseConcat();
        model.addAttribute("s",o);
        model.addAttribute("c", l1);
        return "display_admin";
    }

    @PostMapping("/deletec")
    public String deleteCourse(@RequestParam("bId") int courseid,Model model,@RequestParam("bName") String coursename){
        int k=courseid;
        String n=couser.getCourseNameById(courseid);
        stuservice.delete(couser.getCourseNameById(courseid));
        couser.delete(courseid);
        List<Course> l1=couser.getAllCourses();
        List<Object[]>o=stuservice.courseConcat();
        model.addAttribute("s",o);
        model.addAttribute("c", l1);
        return "/display_admin";
    }

    @PostMapping("/saveCourse")
    public String saveCourses(Model model,@RequestParam("courseid") int courseid,@RequestParam("coursename") String coursename,RedirectAttributes redirAttrs){
        //Course cou=new Course(courseid,coursename);
        //couser.saveOrUpdate(cou);
        Integer check1=couser.getCourseById(courseid);
        String check2=couser.getCourseByName(coursename);
        if(check1==null && check2==null){
            Course cou=new Course(courseid,coursename);
            couser.saveOrUpdate(cou);
            List<Course> l1=couser.getAllCourses();
            List<Object[]>o=stuservice.courseConcat();
            model.addAttribute("s",o);
            model.addAttribute("c", l1);
            return "/display_admin";
        }
        else
        redirAttrs.addFlashAttribute("message", "Already Exists");
        return "redirect:/addCourse";
    }

    @GetMapping("/addCourse")
    public String addCourses(Model model){
        Course course=new Course();
        model.addAttribute("course",course);
        return "addCourse";
    }

}
