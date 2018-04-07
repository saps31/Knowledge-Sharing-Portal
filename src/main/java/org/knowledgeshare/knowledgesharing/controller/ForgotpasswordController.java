package org.knowledgeshare.knowledgesharing.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.knowledgeshare.knowledgesharing.entity.User;
import org.knowledgeshare.knowledgesharing.service.UserService;
import org.knowledgeshare.knowledgesharing.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/forgotpassword")
public class ForgotpasswordController 
{
    @Autowired
    private UserService userService;
    @GetMapping
    public String getForgotPasswordPage()
    {
        return "forgotpassword";
    }

    @PostMapping
    public String forgotpassword(@RequestParam("email")String email,Model model)
    {         
        User u=userService.getEmailIdUser(email);
       if(u !=null)
       { 
        String from="knowledgesharingportal123@gmail.com";
               String to=email;
               String sub="Knowledge Sharing Platform Password";
               String msg="Your Password for Knowledge Sharing Platform ID is:"+u.getPassword();
               String password="bitsjaks1082";
               
              Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session1 = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session1);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);} 
          return "login";
       }
       else
       {
           model.addAttribute("error", "Invalid E-Mail ID...");
           return "forgotpassword";
       }
        }        
    }
