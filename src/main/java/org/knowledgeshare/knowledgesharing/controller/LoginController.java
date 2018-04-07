/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.entity.User;
import org.knowledgeshare.knowledgesharing.repository.UserRepository;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author SAPS
 */

@Controller
public class LoginController
{
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }
    @PostMapping("login")
   
    public String performLogin(@RequestParam("email")String email,@RequestParam("pwd")String pwd,HttpSession session,Model model)
    {
       User u1=userService.login(email, pwd);
       if(u1 !=null)
       { 
           session.setAttribute("user", u1.getFirstname()+" "+u1.getLastname());
           session.setAttribute("userid", u1.getEmailid());
           
            if(u1.getEmailid().equals("admin@gmail.com") && u1.getPassword().equals("12345"))
            {
                List<Project> projects = userService.getAllProjects();
                for(Project p : projects)
                {
                    p.setImageurl("imagename/"+p.getImageurl());
                }
                model.addAttribute("p", projects);
                return "Main-Admin";
            }
            else if(u1.getCheckForAdmin().equals("true"))
            {
                // it is admin 
                 String category = userService.getCategory(u1.getEmailid()); 
                 List<Project> projects=userService.getProjectByCategory(category);
                 for(Project p: projects)
                 {
                     p.setImageurl("imagename/"+p.getImageurl());
                 }
                 model.addAttribute("p", projects);
                 return "admin-feed";
            }
            else
            {
                User u = userRepository.findById((String)session.getAttribute("userid")).get();
                //userService.deleteProject(q);
                List<Project> projects = userService.getMyProjects((String)session.getAttribute("userid"));
                for(Project p : projects)
                {
                    p.setImageurl("imagename/"+p.getImageurl());
                    
                }

                model.addAttribute("u",u);
                model.addAttribute("p", projects);
                return "dashboard";
                // it is normal user
                
            }
        }
        else
        {
            return "login";
        }
    }
}
