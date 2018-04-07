/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;
import java.util.List;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sneha
 */

@Controller
@RequestMapping("/comp")
public class CompController
{
    @Autowired 
    private UserService userService;
    @GetMapping  
    public String displaypage(String category,Model model)
    {
        
            List<Project> projects = userService.getProjectByCategory("computer");
            for(Project p : projects)
            {
                p.setImageurl("imagename/"+p.getImageurl());
            }
            model.addAttribute("p", projects);
            return "comp";
}
}