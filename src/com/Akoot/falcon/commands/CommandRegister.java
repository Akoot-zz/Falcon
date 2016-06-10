package com.Akoot.falcon.commands;

import com.Akoot.falcon.user.Users;

public class CommandRegister extends Command
{
	public CommandRegister()
	{
		this.name = "register";
	}

	@Override
	public void onCommand()
	{
		if(args.length >= 1)
		{
			String username = args[0];
			if(!Users.hasUser(username))
			{
				String password = "";
				if(args.length == 2)
				{
					password = args[1];
				}
				Users.register(username, password);
				trace("Sucessfully registered!");
				return;
			}
			trace("Cannot register as " + username + ": user already exists.");
		}
	}
}
