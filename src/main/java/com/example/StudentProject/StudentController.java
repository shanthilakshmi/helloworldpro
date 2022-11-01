package com.example.StudentProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService stuser;
    @Autowired
    CourseService couser;

    Student loggedStu=null;

    @GetMapping("/")
    public String showHome(Model model){
        Student stu=new Student();
        model.addAttribute("student",stu);
        return "index";
    }

    @GetMapping("/registration_form")
    public String showRegistrationForm(Model model){
        Student stu=new Student();
        model.addAttribute("student",stu);
        return "registration_form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student stu, Model model, @RequestParam("password1") String password1, RedirectAttributes redirAttrs){
        if(stuser.findById(stu.getStdid())==null){
        int i=stu.getStdid();
        String f=stu.getFname();
        String l=stu.getLname();
        String p=stu.getPassword();
        if(password1.equals(p)){
             Student s=new Student(i,f,l,p);
             stuser.saveOrUpdate(s);
            redirAttrs.addFlashAttribute("message", "Successfully Registered");
            return "redirect:/";
        }
        else{
            redirAttrs.addFlashAttribute("message", "Password & Re-Password doesn't match");
            return "redirect:/registration_form";
        }
        }
        else {
            redirAttrs.addFlashAttribute("message", "User Already Exists");
            return "redirect:/";
        }
    }

    @PostMapping("/checkStudent")
    public String checkStudentLogin(@ModelAttribute Student stu,Model model,RedirectAttributes redirAttrs,@ModelAttribute Course cou){
        //Student l=stuser.findStudentLogin(stu.getStdid(),stu.getPassword());
        loggedStu=stuser.findStudentLogin(stu.getStdid(),stu.getPassword());
        if(loggedStu==null){
            redirAttrs.addFlashAttribute("message", "Invalid Credentials");
            model.addAttribute("student",stu);
            return "redirect:/";
        }
        else {
            List<String> l1=stuser.findCourse(loggedStu.getStdid());
            //model.addAttribute("student",stu);
            List<String> ac=stuser.availableCourses(loggedStu.getStdid());
            //model.addAttribute("c",l1);
            model.addAttribute("s",l1);
            model.addAttribute("c",ac);
            model.addAttribute("li",loggedStu);
            return "display";
        }
    }

    @GetMapping("/registerCourses")
    public String showRegister(Model model){
        List<String> ac=stuser.availableCourses(loggedStu.getStdid());
        model.addAttribute("c",ac);
        model.addAttribute("s",loggedStu);
        return "/registerCourses";
    }

    @PostMapping("/registernewCourse")
    public String showRegister(Model model,@RequestParam("courses") String courses,RedirectAttributes redirAttrs){
        List<String> cou=stuser.findCourse(loggedStu.getStdid());
        if(!cou.contains(courses)){
            stuser.newStudCourse(loggedStu,courses);
            List<String> l1=stuser.findCourse(loggedStu.getStdid());
            List<String> ac=stuser.availableCourses(loggedStu.getStdid());
            model.addAttribute("li", loggedStu);
            model.addAttribute("s",l1);
            model.addAttribute("c", ac);
            return "display";
        }
        else {
            //List<Course> l1=couser.getAllCourses();
            List<String> ac=stuser.availableCourses(loggedStu.getStdid());
            model.addAttribute("c",ac);
            model.addAttribute("s",loggedStu);
            redirAttrs.addFlashAttribute("message", "Already exists");
            return "redirect:/registerCourses";
        }
    }

    @GetMapping("/changePassword")
    public String showChangePasswordPage(Model model){
        model.addAttribute("student",loggedStu);
        return "/changePassword";
    }

    @PostMapping("/changeStudentPassword")
    public String changeStudPassword(@ModelAttribute Student student,@RequestParam("Password1") String password1,@RequestParam("Password2") String password2,RedirectAttributes redirAttrs){
        Student s=stuser.findStudentLogin(student.getStdid(),student.getPassword());
        if(s==null){
            redirAttrs.addFlashAttribute("message","wrong password entered");
            return "redirect:/changePassword";
        }
        else{
            if(password1.equals(password2)){
                stuser.updatePassword(loggedStu,password1);
                redirAttrs.addFlashAttribute("message","password changed successfully!");
                return "redirect:/";
            }
            else {
                redirAttrs.addFlashAttribute("message","password and re-entered password didn't match");
                return "redirect:/changePassword";
            }
        }
    }
}
