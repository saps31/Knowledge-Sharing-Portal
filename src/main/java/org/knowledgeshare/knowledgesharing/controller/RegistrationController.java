/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import java.io.File;
import org.knowledgeshare.knowledgesharing.entity.User;
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
 * @author SAPS
 */

@Controller
@RequestMapping("/register")
public class RegistrationController
{
    //private static final String UPLOAD_DIR="F:\\upload\\";
    @Autowired
    private UserService userService;
    @GetMapping
  public String registrationPage(Model model)
  {
      model.addAttribute("user", new User());
      return "register";
  }
  
  
  
  
  @PostMapping
  public String registerMe(@ModelAttribute("user")User user,Model model)
  {
      String response=userService.checkRegisterUser(user);
      String response1=userService.checkEmailUser(user);
      if(response.equals("done") && response1.equals("done")){
      /*String imagepath = user.getUseridcard().getOriginalFilename();
           
           try
           {
               user.getUseridcard().transferTo(new File(UPLOAD_DIR+imagepath));
           }catch(Exception e)
           {
               System.out.println(e);
           }
           user.setIdcardurl(imagepath);*/
           user.setCheckForAdmin("false");
           
           
      if(userService.registerUser(user))
          return "login";
      else
          return "register";
      
      }else if(response.equals("already")){
          model.addAttribute("error", "Enrollment already registred!");
          return "register"; 
      }else if(response1.equals("already")){
          model.addAttribute("error", "Email already registred!");
          return "register"; 
      }
      
      else{
          model.addAttribute("error", "Invalid Enrollment Number!");
          return "register";
          
      }
      
  }
  
}
