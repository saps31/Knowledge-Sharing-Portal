/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.service;


import java.util.List;
import org.knowledgeshare.knowledgesharing.entity.Enrollment;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.entity.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author SAPS
 */

@Service
public interface UserService 
{
   public boolean registerUser(User user);
   public String checkRegisterUser(User user);
   public String checkEmailUser(User user);
   public User login(String emailid,String password);
   public boolean uploadProject(Project project,String emailid);
   public String getCategory(String emailid);
  // public boolean userProfile(Profile profile);
   public List<Project> getAllProjects();
   public List<Project> getProjectByCategory(String category);
   public boolean editProfile(User user, String emailid);
   public boolean editPassword(User user,String emailid);
   public boolean verifiedProject(int projectid);
   public boolean notVerifiedProject(int projectid);
   public Project continueReading(int projectid);
   public List<Project> getVerifiedProjects();
   public List<Project> getProjectNotVerified(String category);
   public List<Project> getMyProjects(String emailid);
   public int countMyProjects(String emailid);
   public boolean deleteProject(int projectid);
   public List<Project> getProjectByKeywords(String keywords);
   public int getLikes(int projectid);
   public boolean addLike(int projectid,String email);
   public boolean getMylike(int projectid,String email);
   public boolean deleteLike(int projectid,String email);
   public User getUserprofile(String emailid);
   //public List<Project> getUserProject(String emailid);
   public boolean check(String path);
   public List<Project> getProjectByInstitute(String institute);
   public User getEmailIdUser(String emailid);
   public boolean addAdmin(User user);
  
}
