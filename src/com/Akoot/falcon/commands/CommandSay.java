package com.Akoot.falcon.commands;

import com.Akoot.falcon.util.ChatColor;

public class CommandSay extends Command
{
	public CommandSay()
	{
		this.name = "say";
		this.aliases.add("echo");
		this.aliases.add("print");
	}
	
	@Override
	public void onCommand()
	{
		String msg = "";
		for(String s: args)
		{
			msg += s + " ";
		}
		trace(ChatColor.RESET + msg.trim());
	}
}
