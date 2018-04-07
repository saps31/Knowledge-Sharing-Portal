/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author SAPS
 */


@Entity
public class User 
{
    
    private String firstname;
    private String lastname;
    private String institute;
    private String department;
    private BigInteger enroll;
    private String emailid;
    private String password;
    private String newpassword;
    private String confirmpassword;
    private String bio;
    private int age;
    //private MultipartFile image1;
           
   
//    private MultipartFile userprofile;     
    private String location;
    private List<Project> projects=new ArrayList<>();
    
   
    private String checkForAdmin;
    //private String idcardurl;
    //private MultipartFile useridcard;

    

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Id
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "emailid"),
            inverseJoinColumns = @JoinColumn(name = "projectid")
    )
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getCheckForAdmin() {
        return checkForAdmin;
    }

    public void setCheckForAdmin(String checkForAdmin) {
        this.checkForAdmin = checkForAdmin;
    }

    /*public String getIdcardurl() {
        return idcardurl;
    }

    public void setIdcardurl(String idcardurl) {
        this.idcardurl = idcardurl;
    }

    
    @Transient
    public MultipartFile getUseridcard() {
        return useridcard;
    }

    public void setUseridcard(MultipartFile useridcard) {
        this.useridcard = useridcard;
    }
*/
   
    
    public String getBio() {
        return bio;
    }

    
    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    
    public void setAge(int age) {
        this.age = age;
    }

    
    public String getLocation() {
        return location;
    }

    
    public void setLocation(String location) {
        this.location = location;
    }

    public BigInteger getEnroll() {
        return enroll;
    }

    public void setEnroll(BigInteger enroll) {
        this.enroll = enroll;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
 
}
