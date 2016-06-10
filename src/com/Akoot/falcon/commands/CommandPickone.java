package com.Akoot.falcon.commands;

import java.util.Random;

public class CommandPickone extends Command
{
	public CommandPickone()
	{
		this.name = "pickone";
	}
	
	@Override
	public void onCommand()
	{
		Random random = new Random();
		if(args.length >= 2)
		{
			trace(args[random.nextInt(args.length)]);
		}
		else
		{
			trace("Usage: pickone [option1] [option2] [option3...]");
		}
	}
}
