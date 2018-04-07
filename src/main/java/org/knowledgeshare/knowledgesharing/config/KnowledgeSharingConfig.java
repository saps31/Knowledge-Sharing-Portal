/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author SAPS
 */

@EnableAutoConfiguration
@Component
public class KnowledgeSharingConfig extends WebMvcConfigurerAdapter
{
     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/static/assets/**").addResourceLocations("/static/css/");
        //registry.addResourceHandler("/static/images/**").addResourceLocations("/static/images/");
        //registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
    }
}
