package com.Akoot.falcon.commands;

import com.Akoot.falcon.util.StringUtil;

public class CommandTest extends Command
{
	public CommandTest()
	{
		this.name = "test";
	}
	
	@Override
	public void onCommand()
	{
		trace("Test of the day!");
		String data = "do -f google -g \"g h h h\"";
		String[] args = data.split("\\s");
		trace(StringUtil.toString(args));
		trace(StringUtil.getArgs(data).toString());
		trace(StringUtil.getQuotes(data).toString());
		
	}
}
