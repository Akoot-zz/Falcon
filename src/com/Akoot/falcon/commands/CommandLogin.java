package com.Akoot.falcon.commands;

import com.Akoot.falcon.user.Users;

public class CommandLogin extends Command
{
	public CommandLogin()
	{
		this.name = "login";
	}

	@Override
	public void onCommand()
	{
		if(args.length >= 1)
		{
			String username = args[0];
			trace("Attempting to login as \"%s\"", username);
			if(Users.hasUser(username))
			{
				String password = "";
				if(args.length == 2)
				{
					password = args[1];
				}
				trace(Users.login(username, password) == true ? "Success!" : "Wrong username or password.");
				return;
			}
			trace("User not found. Type \"register\" to register");
		}
	}
}
