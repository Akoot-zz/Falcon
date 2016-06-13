package com.Akoot.falcon.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.Akoot.falcon.Falcon;
import com.Akoot.falcon.util.CthFile;
import com.Akoot.falcon.util.StringUtil;

public class Users
{
	public static User lastLoggedIn()
	{
//		for(User u: getUsers())
//		{
//			if(u.getFile().getBoolean("logged-in")) return u;
//		}
		return new User("$");
	}

	public static User fakeUser()
	{
		return new User("User" + StringUtil.randomNumber(3));
	}

	public static List<User> getUsers()
	{
		List<User> list = new ArrayList<User>();
		for(File file: Falcon.users.listFiles())
		{
			CthFile user = new CthFile(file.getName());
			list.add(new User(user.getString("username"), user.getString("password"), UUID.fromString(file.getName())));
		}
		return list;
	}
	
	public static boolean hasUser(String username)
	{
		for(User user: getUsers()) if(user.getName().equals(username)) return true;
		return false;
	}

	public static boolean login(String username, String password)
	{
		for(User user: getUsers())
		{
			if(user.getName().equals(username) && user.getPassword().equals(password))
			{
				Falcon.user.logOut();
				user.logIn();
				return true;
			}
		}
		return false;
	}
	
	public static void register(String username, String password)
	{
		Falcon.user = new User(username, password);
		Falcon.user.logIn();
	}
}
