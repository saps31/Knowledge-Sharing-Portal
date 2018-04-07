/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import java.util.List;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.repository.ProjectRepository;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author SAPS
 */
@Controller
public class ContinueReading {
    @Autowired
    private UserService userService;
    @GetMapping("/read-more")
    public String getDetails(Model model,@RequestParam("pid")int q)
    {   
        
        //Project continueReading = userService.continueReading(@RequestParam("pid")int projectid);
        
        Project p = userService.continueReading(q);
        model.addAttribute("pr", p);
        return "read-more";
    }
    
}
