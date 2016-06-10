package com.Akoot.falcon.commands;

public class CommandTest extends Command
{
	public CommandTest()
	{
		this.name = "test";
	}
	
	@Override
	public void onCommand()
	{
		trace("Test!");
	}
}
