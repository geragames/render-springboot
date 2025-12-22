package com.fich.sarh.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiResponse {

    private Date date = new Date();
    private String message;
    private String url;

    public ApiResponse(String message, String url){
        this.message = message;
        this.url = url;
    }
}
