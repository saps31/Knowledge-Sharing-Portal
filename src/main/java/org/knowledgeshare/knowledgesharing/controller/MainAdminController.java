package org.knowledgeshare.knowledgesharing.controller;

import java.util.List;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Main-Admin")
public class MainAdminController 
{
    @Autowired
    private UserService userService;
    @GetMapping
    public String getMainAdminPage()
    {
        return "Main-Admin";
    }
    
    @PostMapping
    public String MainAdminDashboard(Model model)
    {
        List<Project> projects = userService.getAllProjects();
        for(Project p : projects)
        {
            p.setImageurl("imagename/"+p.getImageurl());
        }
        model.addAttribute("p", projects);
        return "Main-Admin";
    }
}
