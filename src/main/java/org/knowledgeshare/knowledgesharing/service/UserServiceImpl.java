/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.service;




import com.testautomationguru.utility.PDFUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.knowledgeshare.knowledgesharing.entity.Enrollment;
import org.knowledgeshare.knowledgesharing.entity.Likes;
import org.knowledgeshare.knowledgesharing.entity.Project;
import org.knowledgeshare.knowledgesharing.entity.User;
import org.knowledgeshare.knowledgesharing.repository.EnrollmentRepository;
import org.knowledgeshare.knowledgesharing.repository.LikesRepository;
import org.knowledgeshare.knowledgesharing.repository.ProjectRepository;

import org.knowledgeshare.knowledgesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SAPS
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean registerUser(User user) {
        user.setBio("add more");
        return userRepository.saveAndFlush(user) != null;
      
    }
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Override
    public String checkRegisterUser(User user) {
       
    try
    {
     BigInteger enrollmentid = user.getEnroll();
        //System.out.println(enrollmentid+"");
      Enrollment e = (Enrollment)enrollmentRepository.findById(enrollmentid).get();
     //List<Enrollment> ls= enrollmentRepository.findAll().stream().collect(Collectors.toList());
      //System.out.println("count:"+ls.size());
      
      if(e.getRegistered().equals("false"))
      {
              e.setRegistered("true");
              return "done";
      }
      else
      {
         return "already"; 
      }
    }catch(Exception e)
    {
        e.printStackTrace(System.out);
        return "invalid"; //pass error msg of error
    }
    
 
    }

    @Override
    public boolean check(String path)
    {
        
        List<Project> projects=getVerifiedProjects();
       for(Project p:projects)
       {
          PDFUtil pdfUtil = new PDFUtil();
        try
        {
         String data1=pdfUtil.getText("F:/upload/"+path); 
           System.out.println("data1:"+data1);
         String data2=pdfUtil.getText("F:/upload/"+p.getFileurl()); 
           System.out.println("data2:"+data2);
        //   int pecen=lock_match(data1, data2);
          // System.out.println("pecentage:"+pecen);
            double distance = StringUtils.getLevenshteinDistance(data1, data2);
            int bigger = Integer.max(data1.length(), data2.length());
            double pct = ((bigger - distance) / bigger)*100;
            System.out.println(pct);
            
            if(pct>=25)
            {
                return false;
            }
            
         
        }catch(Exception e){
          
           e.printStackTrace();
           return false;
        } 
        
       
       }
       return true;
    }
 
   
    @Override
    public String checkEmailUser(User user) {
       try
    {
     String email = user.getEmailid();
        //System.out.println(enrollmentid+"");
      User u = (User)userRepository.findById(email).get();
     //List<Enrollment> ls= enrollmentRepository.findAll().stream().collect(Collectors.toList());
      //System.out.println("count:"+ls.size());
      
      if(!(u.getEmailid().equals(email)))
      {
              return "done";
      }
      else
      {
         return "already"; 
      }
    }catch(Exception e)
    {
        e.printStackTrace(System.out);
        return "done"; //pass error msg of error
    } 
    }

    
    
    
    @Override
    public User login(String emailid, String password) {
        
        try{
       
        return userRepository.findAll().stream().filter((user) -> {
            return user.getEmailid().equals(emailid) && user.getPassword().equals(password);
             
             
        }).findFirst().get();
        
        }catch(Exception e)
        {
            return null;
        }
       
    }
    

    @Override
    public boolean uploadProject(Project project,String emailid) {
        User u=userRepository.findById(emailid).get();
        project.setU_emailid(emailid);
        u.getProjects().add(project); 
        return userRepository.saveAndFlush(u) != null;
    }  

    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public List<Project> getAllProjects() {
        List<Project> p = new ArrayList<>();
        projectRepository.findAll().forEach(p::add);
        return p;
        
        
    }
    
   
    @Override
    public List<Project> getProjectByCategory(String category)
    {
        
       List<Project> p=
        projectRepository.findAll().stream().filter((project)-> {
           return project.getCategory().equals(category);
       }).collect(Collectors.toList());
        
        return p;
    }
     @Override
    public List<Project> getVerifiedProjects()
    {
        
       List<Project> p=
        projectRepository.findAll().stream().filter((project)-> {
           return project.getVerify().equals("true");
       }).collect(Collectors.toList());
        
        return p;
    }
    
    
    /*@Autowired
    @Override
    public List<Project> getUserProject(String emailid) {
        List<Project> p = new ArrayList<>();
        User u = userRepository.findById(emailid).get();
        //projectRepository.fin
        return p;
    }*/

    @Override
    public String getCategory(String emailid) {
    return userRepository.findById(emailid).get().getDepartment();
    }
    
    @Override
    public boolean editProfile(User user,String emailid) {
       User u=userRepository.findById(emailid).get();
       u.setAge(user.getAge());
       u.setBio(user.getBio());
       u.setLocation(user.getLocation());
       return userRepository.saveAndFlush(u) != null;
      
       
    }
    
    @Override
    public boolean editPassword(User user,String emailid){
         User u=userRepository.findById(emailid).get();
         u.setConfirmpassword(user.getConfirmpassword());
       
       String str =user.getNewpassword().substring(0, user.getNewpassword().length() - 1);
       u.setNewpassword(str);
       //System.out.println(str);
       String str1 = user.getConfirmpassword().substring(0, user.getConfirmpassword().length()-1);
       u.setConfirmpassword(str1);
        
       if(str1.equals(str))
       {
            u.setPassword(str);
            return userRepository.saveAndFlush(u) != null;
       }
       else
           return false;
    }
    @Override
    public User getUserprofile(String emailid) {
        
       User u=userRepository.findById(emailid).get();
       return u;
       
    }
    @Override
    public boolean verifiedProject(int projectid) {
        Project p = (Project)projectRepository.findById(projectid).get();
        p.setVerify("true");
        return projectRepository.saveAndFlush(p) != null;
        
    }
    
    @Override
    public boolean notVerifiedProject(int projectid) {
        Project p = (Project)projectRepository.findById(projectid).get();
        p.setVerify("rejected");
        return projectRepository.saveAndFlush(p) != null;
        
    }

    @Override//user id se user go get kro...u.getproject.remove(project)....u.saveandflush
    public boolean deleteProject(int projectid) {
       Project p = (Project)projectRepository.findById(projectid).get();
       projectRepository.delete(p);
       projectRepository.flush();
       return true; 
    }

   
    
    
    @Override
    public Project continueReading(int projectid) {
       Project p = (Project)projectRepository.findById(projectid).get();
       p.setImageurl("imagename/"+p.getImageurl());
        
        return p;
       
    }
    
//    public void checkpdf()
//    {
//       PDFParser parser = null;
//    PDDocument pdDoc = null;
//    COSDocument cosDoc = null;
//    PDFTextStripper pdfStripper;
//
//    String parsedText;
//    String fileName = "E:\\Files\\Small Files\\PDF\\JDBC.pdf";
//    File file = new File(fileName);
//    try {
//        parser = new PDFParser(new FileInputStream(file));
//        parser.parse();
//        cosDoc = parser.getDocument();
//        pdfStripper = new PDFTextStripper();
//        pdDoc = new PDDocument(cosDoc);
//        parsedText = pdfStripper.getText(pdDoc);
//        System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
//    } catch (Exception e) {
//        e.printStackTrace();
//        try {
//            if (cosDoc != null)
//                cosDoc.close();
//            if (pdDoc != null)
//                pdDoc.close();
//        } catch (Exception e1) {
//            e.printStackTrace();
//        }
//
//    }
//    }

    @Override
    public List<Project> getProjectNotVerified(String category) {
     List<Project> p=
        projectRepository.findAll().stream().filter((project)-> {
           return (project.getCategory().equals(category) && project.getVerify().equals("false"));
       }).collect(Collectors.toList());
        return p;
    }

    @Override
    public List<Project> getMyProjects(String emailid) 
    {
        User u=userRepository.findById(emailid).get();
        List<Project> p=u.getProjects();
        return p;
    }
    
    @Override
    public int countMyProjects(String emailid) 
    {
        User u=userRepository.findById(emailid).get();
        int p=u.getProjects().size();
        return p;
    }
    
@Override
    public List<Project> getProjectByKeywords(String keywords)
    {
        List<Project> p=
        projectRepository.findAll().stream().filter((project)-> {
           return project.getKeywords().contains(keywords);
        }).collect(Collectors.toList());
        return p;
    }
   
    @Override
    public List<Project> getProjectByInstitute(String institute)
    {
       List<Project> p=
        projectRepository.findAll().stream().filter((project)-> {
           return project.getInstitution().equals(institute);
       }).collect(Collectors.toList());
        
        return p;
    }

    @Autowired
    LikesRepository likesRepository;
    
    @Override
    public int getLikes(int projectid) {
        
        List<Likes> p=likesRepository.findAll().stream().filter((likes)-> {
           return likes.getProjectid()==(projectid);
        }).collect(Collectors.toList());
        return p.size();    
    }

    @Override
    public boolean addLike(int projectid, String email) {
        Likes l=new Likes(email, projectid);
        return likesRepository.saveAndFlush(l) != null;
        
    }

    @Override
    public boolean getMylike(int projectid, String email) {
      try{
            List<Likes> p=likesRepository.findAll().stream().filter((likes)-> {
            return likes.getProjectid()==(projectid) && likes.getEmailid().equals(email);
            }).collect(Collectors.toList());
            if(p.size()>=1)
            {
                return true;
            }
     
      }catch(Exception e)
      {
          return false;
      }
      return false;
              
    }

    @Override
    public boolean deleteLike(int projectid, String email) {
        
        List<Likes> p=likesRepository.findAll().stream().filter((likes)-> {
            return likes.getProjectid()==(projectid) && likes.getEmailid().equals(email);
            }).collect(Collectors.toList());
        
        
          //System.out.println("in delete method"+email+projectid);
       likesRepository.deleteInBatch(p);
       likesRepository.flush();
       return true;
       
         }
    
    @Override
    public User getEmailIdUser(String emailid)
    {
        try{
       
        return userRepository.findAll().stream().filter((user) -> {
            return user.getEmailid().equals(emailid);
             
             //To change body of generated lambdas, choose Tools | Templates.
        }).findFirst().get();
        
        }catch(Exception e)
        {
            return null;
        }
    }
    
    @Override
    public boolean addAdmin(User user) {
        user.setCheckForAdmin("true");
        return userRepository.saveAndFlush(user) != null;
    }



}
