package com.revature.p1.web.models;

public class Trade {
	private int id;
	private String tradeName, skill1, skill2;
	private int tradeHealth, skill1damage, skill2damage;
	
	//------------------------ constructors -----------------------
	public Trade() {
		super();
		this.id = 1;
		this.tradeName = "archer";
		this.skill1 = "arrow";
		this.skill2 = "flamingarrow";
		this.tradeHealth = 100;
		this.skill1damage = 5;
		this.skill2damage = 8;
	}
	
	public Trade(int id, String tradeName) {
		super();
		this.id = id;
		this.tradeName = tradeName;
	}

    public Trade(int id, String tradeName, String skill1, String skill2, int tradeHealth, int skill1damage,
			int skill2damage) {
		super();
		this.id = id;
		this.tradeName = tradeName;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.tradeHealth = tradeHealth;
		this.skill1damage = skill1damage;
		this.skill2damage = skill2damage;
	}

    //------------------------ methods -----------------------
    //public int getHealth(typeId) {}
	@Override
	public String toString() {
		return "Trade [id=" + id + ", trade=" + tradeName + ", skill1=" + skill1 + ", skill2=" + skill2 + ", tradeHealth="
				+ tradeHealth + ", skill1damage=" + skill1damage + ", skill2damage=" + skill2damage + "]";
	}

	
	//------------------------ getters & setters -----------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	//-------------------------------------------------------
	public String getTrade() {
		return tradeName;
	}

	public void setTrade(String trade) {
		this.tradeName = trade;
	}
	//-------------------------------------------------------
	public String getSkill1() {
		return skill1;
	}

	public void setSkill1(String skill1) {
		this.skill1 = skill1;
	}
	//-------------------------------------------------------
	public String getSkill2() {
		return skill2;
	}

	public void setSkill2(String skill2) {
		this.skill2 = skill2;
	}
	//-------------------------------------------------------
	public int getTradeHealth() {
		return tradeHealth;
	}

	public void setTradeHealth(int tradeHealth) {
		this.tradeHealth = tradeHealth;
	}
	//-------------------------------------------------------
	public int getSkill1damage() {
		return skill1damage;
	}

	public void setSkill1damage(int skill1damage) {
		this.skill1damage = skill1damage;
	}
	//-------------------------------------------------------
	public int getSkill2damage() {
		return skill2damage;
	}

	public void setSkill2damage(int skill2damage) {
		this.skill2damage = skill2damage;
	}
	
}
