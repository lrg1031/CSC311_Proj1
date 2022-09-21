/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lukegreen_assignment01_csc311;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Staib
 */
public class Tweet {
    
    @SerializedName("username")
    private String username;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("likes")
    private int likes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    
    
}
