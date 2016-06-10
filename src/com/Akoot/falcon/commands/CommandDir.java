package com.Akoot.falcon.commands;

import java.io.File;

import com.Akoot.falcon.Falcon;
import com.Akoot.falcon.util.StringUtil;

public class CommandDir extends Command
{
	public CommandDir()
	{
		this.name = "dir";
		this.aliases.add("cd");
		this.aliases.add("directory");
	}
	
	@Override
	public void onCommand()
	{
		if(args.length == 0) trace("Current Directory: " + Falcon.dir.getAbsolutePath());
		else if(args.length > 0)
		{
			String newDir = "";
			if(args[0].startsWith("\""))
			{
				newDir = StringUtil.getRegex("\"([^\"]*)\"", StringUtil.toString(args), 1);
			}
			else
			{
				newDir = args[0];
			}
			File dir = new File(newDir);
			trace("Changed directory: " + Falcon.dir.getAbsolutePath() + " -> " + dir.getAbsolutePath());
			Falcon.dir = dir;
		}
	}
}
