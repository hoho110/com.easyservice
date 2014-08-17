package com.easyservice.test;

import com.easyservice.security.Permit;

public class User implements Permit{
	private int privilege;
	private String name;
	public User(){}
	public User(String name,int privilege)
	{
		this.name=name;
		this.privilege=privilege;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int getPrivilege() {
		return privilege;
	}
	@Override
	public void setPrivilege(int privilege) {
		this.privilege=privilege;
	}
}
