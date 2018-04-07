/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.repository;

import org.knowledgeshare.knowledgesharing.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAPS
 */

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer>{
    
}
