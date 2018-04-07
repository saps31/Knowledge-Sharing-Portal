
package org.knowledgeshare.knowledgesharing.controller;

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
 * @author Keval
 */
@Controller
@RequestMapping("/Main-Admin-Add-Admin")
public class Main_Admin_Add_AdminController 
{
    @Autowired
    private UserService userService;
    @GetMapping
    public String Add_Admin(Model model)
    {
        model.addAttribute("user", new User());
        return "Main-Admin-Add-Admin";
    }
    
    @PostMapping
    public String Add_AdminPage(Model model,@ModelAttribute("user")User user)
    {
        
            if(userService.addAdmin(user))
            {    
                
                model.addAttribute("done", "Successfully added");
                return "Main-Admin-Add-Admin";
            }
            else
            {   model.addAttribute("done", "Failed..add again");
                return "Main-Admin";
            }         
            
    }
}
