/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import java.util.AbstractList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.repository.ProjectRepository;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author SAPS
 */

@Controller
@RequestMapping("/admin-feed")
public class AdminFeedController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String feedpage(Model model,HttpSession session,@RequestParam(name="q",required = false)Integer q,@RequestParam(name="r",required = false)Integer r)
    {
       //@RequestParam(name="q",required = false)int q
        
        if(session.getAttribute("userid")!=null)
        {   
            if(q==r)
           {
            
            String category = userService.getCategory((String)session.getAttribute("userid"));
                   List<Project> projects=userService.getProjectNotVerified(category);
                   for(Project p: projects)
                   {
                       p.setImageurl("imagename/"+p.getImageurl());
                   }
                  model.addAttribute("p", projects);


            return "admin-feed";
           }
            else if(q!=null)
            {
                userService.verifiedProject(q);
                String category = userService.getCategory((String)session.getAttribute("userid"));
                   List<Project> projects=userService.getProjectNotVerified(category);
                   for(Project p: projects)
                   {
                       p.setImageurl("imagename/"+p.getImageurl());
                   }
                  model.addAttribute("p", projects);


            return "admin-feed";
            }
            else if(r!=null)
            {
            userService.notVerifiedProject(r);
            String category = userService.getCategory((String)session.getAttribute("userid"));
                   List<Project> projects=userService.getProjectNotVerified(category);
                   for(Project p: projects)
                   {
                       p.setImageurl("imagename/"+p.getImageurl());
                   }
                  model.addAttribute("p", projects);


            return "admin-feed";
            }
            else
            {
                return "admin-feed";
            }
        }
        else
        {
            return "login";
        }
    }
    
    /*@PostMapping
    public String feedpagepost(Model model,HttpSession session,@RequestParam("q")int q)
    {
        
        
        if(session.getAttribute("userid")!=null)
        {    
            userService.verifiedProject(q);
        userService.notVerifiedProject(q);
            String category = userService.getCategory((String)session.getAttribute("userid"));
                   List<Project> projects=userService.getProjectNotVerified(category);
                   for(Project p: projects)
                   {
                       p.setImageurl("imagename/"+p.getImageurl());
                   }
                  model.addAttribute("p", projects);


            return "admin-feed1";
        }
        else
        {
            return "login";
        }
    }*/
    
    
    
//    @GetMapping("/verify")
//    public String verified(@RequestParam("q")int q,Model model,HttpSession session)
//    {
//        if(userService.verifiedProject(q)){
//            
//             
//             String category = userService.getCategory((String)session.getAttribute("userid")); 
//        model.addAttribute("p", userService.getProjectByCategory(category));
//         
//            return "admin-feed"; 
//            
//        }
//        else
//        {
//            return "xyz";
//        }
//       
//    }
//    
}
