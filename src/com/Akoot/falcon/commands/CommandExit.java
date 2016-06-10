package com.Akoot.falcon.commands;

import com.Akoot.falcon.Falcon;

public class CommandExit extends Command
{
	public CommandExit()
	{
		this.name = "exit";
		this.aliases.add("quit");
		this.aliases.add("close");
	}
	
	@Override
	public void onCommand()
	{
		trace("Goodbye.");
		Falcon.exit();
	}
}
