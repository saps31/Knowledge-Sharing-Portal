/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.List;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.entity.User;
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
 * @author Keval
 */
@Controller
@RequestMapping("/search")
public class searchController
{
    @Autowired
    private UserService userService;
    
    @GetMapping("/search")
    public String getLoginPage()
    {
        return "search";
    }
    @PostMapping
    public String searchPage(Model model,@RequestParam("search")String search)
    {
        List<Project> projects = userService.getProjectByKeywords(search);
        if(!projects.isEmpty())
        {
            for(Project p : projects)
            {
                p.setImageurl("imagename/"+p.getImageurl());
            }
            model.addAttribute("p", projects);
        }
        else
        {
            projects = userService.getProjectByInstitute(search);
            if(!projects.isEmpty())
            {
                for(Project p : projects)
                {
                    p.setImageurl("imagename/"+p.getImageurl());
                }
                model.addAttribute("p", projects);
            }
            else
            {
                projects = userService.getProjectByCategory(search);
                if(!projects.isEmpty())
                {
                    for(Project p : projects)
                    {
                        p.setImageurl("imagename/"+p.getImageurl());
                    }
                    model.addAttribute("p", projects);
                }
                else
                {
                    return "feed";
                }
            }
        }
        return "search";
    }
}
