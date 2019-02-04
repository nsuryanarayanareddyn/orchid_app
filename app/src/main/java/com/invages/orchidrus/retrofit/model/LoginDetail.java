package com.invages.orchidrus.retrofit.model;

public class LoginDetail {

    String status_code;
    String status;
    String message;
    String token;
    String userid;

    public LoginDetail(String status_code, String status, String message, String token,String userid) {
        this.status_code = status_code;
        this.status = status;
        this.message = message;
        this.token = token;
        this.userid = userid;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
