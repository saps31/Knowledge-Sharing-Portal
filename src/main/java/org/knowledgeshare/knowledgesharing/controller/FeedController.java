/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.entity.User;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author SAPS
 */

@Controller
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String feedpage(Model model,HttpSession session,@RequestParam(name="q",required = false)Integer q,@RequestParam(name="action",required = false)String action)
    {
        if(session.getAttribute("userid")!=null)
        {
            
            if(q!=null && (!action.equals(null)))
            {
                
                if(action.equals("add"))
                {
                    //System.out.println("in add");
                    userService.addLike(q,(String)session.getAttribute("userid") );
                }else if(action.equals("delete"))
                {
                     //System.out.println("in delete");
                    userService.deleteLike(q,(String)session.getAttribute("userid") );
                }
            }
            List<Project> projects = userService.getVerifiedProjects();
            List<User> users=new ArrayList<>();
            
            
            for(Project p : projects)
            {
             //   String u=p.getU().getFirstname();
             //   System.out.println(u);
                
                users.add(userService.getUserprofile(p.getU_emailid()));
                p.setImageurl("imagename/"+p.getImageurl());
                int count=userService.getLikes(p.getProjectid());
                p.setLikes(count);
                //System.out.println(count+"count");
                if(userService.getMylike(p.getProjectid(), (String)session.getAttribute("userid")))
                {   p.setMylike("true");
                //System.out.println("true");
                }else
                {   p.setMylike("false");
                //System.out.println("false");
                }
            }
            model.addAttribute("p", projects);
            model.addAttribute("u", users);
           
            return "feed";
            }
            
        else
        {
            return "login";
        }
    }
    
    
}
