package com.cgr.bbp.application.email.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EmailRequest {

    private List<Long> recipients; 
    // private String subject;          
    // private Map<String, String> templateData; 


}
