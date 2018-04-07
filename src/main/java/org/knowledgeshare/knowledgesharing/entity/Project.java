/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**a
 *
 * @author SAPS
 */

@Entity
public class Project 
{
   private int projectid;
   private String projecttitle;
   private String shortdesc;
   private String longdesc;
   private String category;
   private String keywords;
   private String imageurl;
   private String fileurl;
   private String language1;
   private String subcategory;
   private String technologystack;
   
   private MultipartFile image;
   private MultipartFile file;
   private String verify;
   private int likes;
   private String mylike;
   //private String email;
   private String u_emailid;
   private String institution;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProjectid() {
        return projectid;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getMylike() {
        return mylike;
    }

    public void setMylike(String mylike) {
        this.mylike = mylike;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institute) {
        this.institution = institute;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language) {
        this.language1 = language;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTechnologystack() {
        return technologystack;
    }

    public void setTechnologystack(String technologystack) {
        this.technologystack = technologystack;
    }

    
    
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    
    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

   // @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public String getU_emailid() {
        
        return u_emailid;
    }

    public void setU_emailid(String u) {
        this.u_emailid = u;
    }

   
    public String getProjecttitle() {
        return projecttitle;
    }

    

    public void setProjecttitle(String projecttitle) {
        this.projecttitle = projecttitle;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getLongdesc() {
        return longdesc;
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Transient
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Transient
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
   
   
}
