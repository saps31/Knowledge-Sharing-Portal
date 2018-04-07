/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;


import java.io.File;
import javax.servlet.http.HttpSession;
import org.knowledgeshare.knowledgesharing.entity.Project;
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
@RequestMapping("/upload")
public class UploadController 
{
    
    private static final String UPLOAD_DIR="F:\\upload\\";
    @Autowired
    private UserService userService;
    @GetMapping
    public String getUploadPage(Model model,HttpSession session)
    {
        if(session.getAttribute("userid")!=null)
        {
            model.addAttribute("project", new Project());
            return "upload";
        }
        else
        {
            return "login";
        }
    }
    
    @PostMapping
    public String postProject(@ModelAttribute("project")Project project,HttpSession session,Model model)
    {
        String imagepath = project.getImage().getOriginalFilename();
        String filepath = project.getFile().getOriginalFilename();
        project.setImageurl(imagepath);
        project.setFileurl(filepath);
        project.setVerify("false");
        
        if(project.getImage().getContentType().equals("image/jpeg") && project.getFile().getContentType().equals("application/pdf") )
        {
            try {
                
                
                   
                File f=new File(UPLOAD_DIR+filepath);
                project.getFile().transferTo(f);
                 
                  if(userService.check(filepath))
                  {
                        project.getImage().transferTo(new File(UPLOAD_DIR+imagepath));
                        if(userService.uploadProject(project, (String)session.getAttribute("userid")))
                            {         
                                model.addAttribute("uploaderror", "Project uploaded successfully");
                                return "upload";
                            }
                        else
                            {  
                                model.addAttribute("uploaderror", "Project not uploaded successfully1");
                                return "upload";   
    
                            }
                    }else
                    {
                            model.addAttribute("uploaderror", "Ooops..Plagiarism Found! We already have similar project idea as yours.");
                           // f.delete(); //delete uploaded pdf file
                            return "upload";    
                    }
                } catch (Exception e) {
                    model.addAttribute("uploaderror", "Project not uploaded successfully2");
                    e.printStackTrace();
                    return "upload";
            }
            
        }
        else{
         model.addAttribute("imageerror", "Please upload only image/pdf");
               
        return "upload";
        }
    }    
}
