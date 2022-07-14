package com.revature.p1.orm.models;

import java.util.ArrayList;
import java.util.List;

public class Avatar {
    private int id;
    private String avatarName, gender, eyeColor, hairColor, shirtColor, pantColor;
    private int height, age, level, health, typeId;


    //------------------------ classes -----------------------
    public Avatar (){
        super();
        this.id = 0;
        this.avatarName = "";
        this.gender = "";
        this.eyeColor = "";
        this.hairColor = "";
        this.shirtColor = "";
        this.pantColor = "";
        this.height = 72;
        this.age = 25;
        this.level = 1;
        this.health = 0; //add getHealth method
        this.typeId = 1;
    }

    //------------------------ methods -----------------------
    //public int getHealth(typeId) {}
    
    @Override
    public String toString() {
        return "Avatar [id= " + id + ", avatar name = " + avatarName + ", gender= " + gender + ", eye color= " + eyeColor 
        + ", hair color= " + hairColor + ", shirt color= " + shirtColor + ", pant color= " + pantColor + ", height= " + height
        + ", age=" + age + ", level= " + ", health= " + health + ", typeId= " + typeId + "]";
    }

    //------------------------ getters & setters -----------------------
    //get & set id------------------------------------------------------------------
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    //get & set name------------------------------------------------------------------
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //get & set username------------------------------------------------------------------
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
}
