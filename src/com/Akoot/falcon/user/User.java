package com.Akoot.falcon.user;

import java.util.UUID;

import com.Akoot.falcon.Falcon;
import com.Akoot.falcon.util.CthFile;

public class User 
{
	private String username;
	private String password;
	private UUID uuid;
	
	private boolean loggedIn;
	
	private CthFile userFile;

	public User(String username)
	{
		this(username, "", UUID.randomUUID());
	}
	
	public User(String username, String password)
	{
		this(username, password, UUID.randomUUID());
	}
	
	public User(String username, String password, UUID uuid)
	{
		this.username = username;
		this.password = password;
		this.uuid = (this.username.equals("$") ? UUID.fromString("00000000-0000-0000-0000-000000000000") : uuid);
		this.userFile = new CthFile(Falcon.users, uuid.toString());
		this.updateFile();
	}
	
	public CthFile getFile()
	{
		return userFile;
	}

	public String getName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public UUID getUniqueID()
	{
		return uuid;
	}
	
	public void setPassword(String password, String newPassword)
	{
		if(this.password.equals(password)) this.password = newPassword;
		updateFile();	
	}
	
	public void logOut()
	{
		this.loggedIn = false;
		this.updateFile();
		Falcon.user = new User("$");
	}
	
	public void logIn()
	{
		this.loggedIn = true;
		this.updateFile();
		Falcon.user = this;
	}
	
	private void updateFile()
	{
		if(!userFile.exists()) userFile.create();
		this.userFile.set("username", username);
		this.userFile.set("password", password);
		this.userFile.set("logged-in", loggedIn);
	}

	public void setName(String name)
	{
		this.username = name;
		this.updateFile();
	}
}
