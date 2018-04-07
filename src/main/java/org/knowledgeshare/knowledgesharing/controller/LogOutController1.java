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
public class LogOutController1
{
    
    @Autowired
    private UserService userService;
   @GetMapping("/logout")
   public String getLogoutPage(HttpSession session)
   {
       session.invalidate();
       return "login";
   }
  
}
