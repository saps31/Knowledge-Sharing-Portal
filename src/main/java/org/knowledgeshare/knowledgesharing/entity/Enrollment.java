/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knowledgeshare.knowledgesharing.entity;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author SAPS
 */

@Entity
public class Enrollment {

    
    
    @Id
    private BigInteger enroll;//aa levanu ne ??
    
    private String registered;

    public BigInteger getEnroll() {
        return enroll;
    }

    public void setEnroll(BigInteger enroll) {
        this.enroll = enroll;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    
    
}
