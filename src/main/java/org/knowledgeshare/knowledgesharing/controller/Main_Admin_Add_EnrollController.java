/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Keval
 */
@Controller
@RequestMapping("/Main-Admin-Add-Enroll")
public class Main_Admin_Add_EnrollController 
{
    @Autowired
    @GetMapping
    public String page(){
      return "tru";  
    }
}
