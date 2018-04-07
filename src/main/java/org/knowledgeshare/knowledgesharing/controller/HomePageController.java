/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author SAPS
 */
@Controller
public class HomePageController {
    @GetMapping("/index")
  public String getHomePage()
  { 
      return "index";
  } 
    
}
