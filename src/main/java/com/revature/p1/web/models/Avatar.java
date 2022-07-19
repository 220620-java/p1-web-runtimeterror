package com.revature.p1.web.models;

public class Avatar {
    private int id;
    private String avatarName, gender, eyeColor, hairColor, shirtColor, pantColor;
    private int height, age, level, health, tradeId;


    //------------------------ constructors -----------------------
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
        this.tradeId = 1;
    }
    
    public Avatar(String avatarName, int level, int health, Trade trade) {
		// TODO Auto-generated constructor stub
    	super();
    	this.avatarName = avatarName;
    	this.level = level;
    	this.health = health;
    	this.tradeId = trade.getId();
	}

    public Avatar(int id, String avatarName, String gender, String eyeColor, String hairColor, String shirtColor,
			String pantColor, int height, int age, int level, int health, int tradeId) {
		super();
		this.id = id;
		this.avatarName = avatarName;
		this.gender = gender;
		this.eyeColor = eyeColor;
		this.hairColor = hairColor;
		this.shirtColor = shirtColor;
		this.pantColor = pantColor;
		this.height = height;
		this.age = age;
		this.level = level;
		this.health = health;
		this.tradeId = tradeId;
	}

	//------------------------ methods -----------------------
    //public int getHealth(typeId) {}

	@Override
    public String toString() {
        return "Avatar [id= " + id + ", avatar name = " + avatarName + ", gender= " + gender + ", eye color= " + eyeColor 
        + ", hair color= " + hairColor + ", shirt color= " + shirtColor + ", pant color= " + pantColor + ", height= " + height
        + ", age=" + age + ", level= " + ", health= " + health + ", tradeId= " + tradeId + "]";
    }

    //------------------------ getters & setters -----------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	//-------------------------------------------------------
	public String getAvatarName() {
		return avatarName;
	}
	
	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}
	//-------------------------------------------------------
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	//-------------------------------------------------------
	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	//-------------------------------------------------------
	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	//-------------------------------------------------------
	public String getShirtColor() {
		return shirtColor;
	}

	public void setShirtColor(String shirtColor) {
		this.shirtColor = shirtColor;
	}
	//-------------------------------------------------------
	public String getPantColor() {
		return pantColor;
	}

	public void setPantColor(String pantColor) {
		this.pantColor = pantColor;
	}
	//-------------------------------------------------------
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	//-------------------------------------------------------
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	//-------------------------------------------------------
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	//-------------------------------------------------------
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	//-------------------------------------------------------
	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
       
}
