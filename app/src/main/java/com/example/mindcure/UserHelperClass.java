package com.example.mindcure;

public class UserHelperClass {
    String name;
    String username;
    String email;
    String password;
    String confirmPass;

    public UserHelperClass() {
    }
    public UserHelperClass(String name, String username, String email, String password, String confirmPass) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPass = confirmPass;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPass(){
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

}

