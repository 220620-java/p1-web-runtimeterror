package com.revature.p1.web.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name, username;
    private List<Avatar> avatars;


    //------------------------ constructors -----------------------
    public Player (){
        super();
        this.id = 0;
        this.name = "";
        this.username = "";
    }

    public Player (String username) {
        super();
        this.username = username;
    }

    public Player(int id, String name, String username, List<Avatar> avatars) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.avatars = avatars;
	}

    //------------------------ methods -----------------------
    @Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", username=" + username + ", avatars=" + avatars + "]";
	}

    //------------------------ getters & setters -----------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Avatar> getAvatars() {
		return avatars;
	}

	public void setAvatars(List<Avatar> avatars) {
		this.avatars = avatars;
	}
    
}
