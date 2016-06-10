package com.Akoot.falcon.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.Akoot.falcon.Console;
import com.Akoot.falcon.user.User;
import com.Akoot.falcon.util.ChatColor;
import com.Akoot.falcon.util.StringUtil;

public class Command
{
	public User user;
	public Console console;
	public String name;
	public List<String> aliases = new ArrayList<String>();
	public String[] args;
	
	public Command() {}
	
	public void onCommand() {}
	
	public void trace()
	{
		console.trace();
	}
	
	public String getArgFor(String arg)
	{
		if(Arrays.asList(args).contains(arg))
		{
			int x = Arrays.asList(args).indexOf(arg) + 1;
			
			String s = "";
			for(int i = x; i < args.length; i++)
			{
				s += args[i] + " ";
			}
			s = s.trim();
			
			if(s.matches("\".*\"")) return StringUtil.getRegex("\"([^\"]*)\"", s, 1).replaceAll("\"", "");
			else return Arrays.asList(args).get(x).replaceAll("\"", "");
		}
		return "";
	}
	
	public void trace(String msg, Object... format)
	{
		console.trace(msg, format);
	}
	
	public void invalidSyntax(int index)
	{
		console.trace(ChatColor.RED + "Invalid syntax at argument %s: \"%s\"", index, args[index]);
	}
	
	public void invalidSyntax(String msg)
	{
		console.trace(ChatColor.YELLOW + "Invalid syntax: " + msg);
	}
	
	public void error(String error)
	{
		console.trace(ChatColor.RED + "Error: " + error);
	}
	
	public void sendUsage(String msg, String... other)
	{
		console.trace(ChatColor.CYAN + "Usage: %s %s", name, msg);
		for(String alt: other)
		{
			console.trace("or");
			console.trace("%s %s", name, alt);
		}
	}
}
