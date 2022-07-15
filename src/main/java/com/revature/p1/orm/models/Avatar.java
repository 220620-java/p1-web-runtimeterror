package com.revature.p1.orm.models;

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
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
       
}
