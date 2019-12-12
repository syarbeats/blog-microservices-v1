package com.mitrais.cdc.blogmicroservices.utility;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private String authToken= new String();

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}