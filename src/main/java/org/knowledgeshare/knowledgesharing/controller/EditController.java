/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import javax.servlet.http.HttpSession;
import org.knowledgeshare.knowledgesharing.entity.User;
import org.knowledgeshare.knowledgesharing.repository.UserRepository;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sneha
 */
@Controller
//@RequestMapping("/edit-profile")
public class EditController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/edit-profile")
    public String getEditPage(Model model,HttpSession session)
    {
        int count = userService.countMyProjects((String)session.getAttribute("userid"));
        model.addAttribute("user", new User());
        model.addAttribute("pcount",count);
        return "edit-profile";
    }
    
    
    
    @PostMapping("/edit-profile")
    public String getEditedProfile(@ModelAttribute("user")User user,HttpSession session,Model model)
    {
        if(userService.editProfile(user,(String)session.getAttribute("userid")))
        {
            User user1=userRepository.findById((String)session.getAttribute("userid")).get();
            model.addAttribute("u", user1);
            return "dashboard";
        }
        else
            return "edit-profile";                   
    }
        
    @PostMapping("/edit-password")
    public String getEditedPassword(@ModelAttribute("user")User user,HttpSession session,Model model)
    {
        if(userService.editPassword(user,(String)session.getAttribute("userid"))){
            User user1=userRepository.findById((String)session.getAttribute("userid")).get();
         model.addAttribute("u", user1);
            return "dashboard";
        }
        else
            return "edit-profile";                   
    }

}
