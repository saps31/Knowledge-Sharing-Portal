/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author SAPS
 */

@Controller
@RequestMapping("/dashboard")
public class MyProjectsController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String dashboard(Model model,HttpSession session,@RequestParam(name="q",required = false)Integer q)
    {
        if(session.getAttribute("userid")!=null)
        {
            if(q==null)
            {    
                User user = userRepository.findById((String)session.getAttribute("userid")).get();
                //userService.deleteProject(q);
                List<Project> projects = userService.getMyProjects((String)session.getAttribute("userid"));
                for(Project p : projects)
                {
                    p.setImageurl("imagename/"+p.getImageurl());
                    /*if(p.getVerify().equals("false"))
                        model.addAttribute("check","false");
                    else if(p.getVerify().equals("true"))
                        model.addAttribute("check", "true");
                    else if(p.getVerify().equals("rejected"))
                        model.addAttribute("check", "rejected");*/
                }

                model.addAttribute("u",user);
                model.addAttribute("p", projects);
                return "dashboard";
            }
            else
            {
               userService.deleteProject(q);
                List<Project> projects = userService.getMyProjects((String)session.getAttribute("userid"));
                for(Project p : projects)
                {
                    p.setImageurl("imagename/"+p.getImageurl());
                }
                model.addAttribute("p", projects);
                return "dashboard"; 
            }
        }
        else
        {
            return "login";
        }
    }
    
    
}
