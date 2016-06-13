package com.Akoot.falcon;

import java.util.ArrayList;
import java.util.List;

import com.Akoot.falcon.commands.*;
import com.Akoot.falcon.user.User;
import com.Akoot.falcon.util.ChatColor;

public class Console 
{
	public String name;
	private List<Command> commands;
	
	public Console(String name)
	{
		this.name = name;
		this.commands = new ArrayList<Command>();
		trace(ChatColor.CYAN + name + ChatColor.RESET);
		registerCommands();
	}
	
	public void registerCommands()
	{
		this.commands.add(new CommandSay());
		this.commands.add(new CommandExit());
		this.commands.add(new CommandLogin());
		this.commands.add(new CommandPickone());
		this.commands.add(new CommandOpen());
		this.commands.add(new CommandFF());
		this.commands.add(new CommandTest());
		this.commands.add(new CommandDir());
		this.commands.add(new CommandMove());
		this.commands.add(new CommandRegister());
		this.commands.add(new CommandShift());
	}
	
	public void trace(String msg, Object... format)
	{
		System.out.println(String.format(msg, format));
	}
	
	public void trace()
	{
		System.out.println();
	}
	
	public boolean executeCommand(User user, String cmd, String[] args)
	{
		for(Command command: commands)
		{
			if(command.name.equalsIgnoreCase(cmd) || command.aliases.contains(cmd))
			{
				command.console = this;
				command.user = user;
				command.args = args;
				command.onCommand();
				return true;
			}
		}
		return false;
	}
}
