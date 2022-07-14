package com.revature.p1.orm.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name, username;
    private List<Avatars> avatars;


    //------------------------ classes -----------------------
    public Player (){
        super();
        this.id = 0;
        this.name = "";
        this.username = "";
        this.avatars = new ArrayList();
    }

    public Player (String username) {
        super();
        this.username = username;
    }

    public Player (int id, String name, String username){
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.avatars = new ArrayList();
    }

    //------------------------ methods -----------------------
    @Override
    public String toString() {
        return "Player [id=" + id + ", name =" + name + ", username=" + username + "avatars=" + avatars + "]";
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
