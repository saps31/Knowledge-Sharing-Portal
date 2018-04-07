/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author SAPS
 */
@Entity
public class Likes {
    
   
    private int likeid; 
    private String emailid;
    private int projectid;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getLikeid() {
        return likeid;
    }

    public String getEmailid() {
        return emailid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setLikeid(int likeid) {
        this.likeid = likeid;
    }
    
    public Likes()
    {
       
    }
    public Likes(String emailid, int projectid) {
        this.emailid = emailid;
        this.projectid = projectid;
    }
    
   

    
    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    
    
}
