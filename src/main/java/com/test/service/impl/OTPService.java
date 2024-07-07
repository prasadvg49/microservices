package com.test.service.impl;

import org.springframework.stereotype.Service;

@Service
public class OTPService 

{

	public String generateOTP() 
    {  //int randomPin declared to store the otp
        //since we using Math.random() hence we have to type cast it int
        //because Math.random() returns decimal value
        int randomPin   =(int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        System.out.println("OTP : "+otp);
        return otp; //returning value of otp
    }
       
        
    
}
	

