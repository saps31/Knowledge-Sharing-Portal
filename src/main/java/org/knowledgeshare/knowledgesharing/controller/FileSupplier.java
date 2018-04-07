/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.controller;

import java.io.File;
import java.nio.file.Files;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SAPS
 */
@RestController
@RequestMapping("/imagename/{imageName}")
public class FileSupplier 
{
    private static final String UPLOAD_DIR="F:\\upload\\";
    @GetMapping
    @ResponseBody
   public byte[]  getImage(@PathVariable("imageName")String imageName) 
   {
       try {
           File fiel=new File(UPLOAD_DIR+imageName);
           return Files.readAllBytes(fiel.toPath());
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
}
