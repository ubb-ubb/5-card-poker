package com.ubb.game;

// User class
// Attributes; String name and int money.
// Methods; getters and setters
public class User {
	
	private String name;
	private int money = 10000;

	User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
