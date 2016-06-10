package com.Akoot.falcon.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import com.Akoot.falcon.util.StringUtil;

public class CommandMove extends Command
{
	public CommandMove()
	{
		this.name = "move";
		this.aliases.add("mv");
	}

	@Override
	public void onCommand()
	{
		if(args.length >= 2)
		{
			File source, dest;
			String a = StringUtil.toString(StringUtil.removeFrom(args, "-f"));

			if(a.matches("\"([^\"]*)\" \"([^\"]*)\""))
			{
				source = new File(StringUtil.getQuote(a, 0));
				dest = new File(StringUtil.getQuote(a, 1));
				trace("matches case 1");
			}
			else if(a.contains("\""))
			{
				trace("contains quotes");
				if(a.matches("\"([^\"]*)\" [^\"]*"))
				{
					source = new File(StringUtil.getQuote(a, 0));
					dest = new File(a.replaceAll(source.getName(), "").replaceAll("\"", "").trim());
				}
				else if(a.matches("[^\"]* \"([^\"]*)\""))
				{
					source = new File(args[0]);
					dest = new File(StringUtil.getQuote(a, 0));
				}
				else return;
			}
			else
			{
				source = new File(args[0]);
				dest = new File(args[1]);
			}
			move(source, dest, Arrays.asList(args).contains("-f"));
		}
	}

	private void move(File source, File dest, boolean folder)
	{
		trace("Moved: %s -> %s", source, dest);
		if(!dest.exists()) dest.mkdir();
		if(!source.isDirectory() || !dest.isDirectory()) return;
		if(folder)
		{
			try
			{
				Files.move(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return;
		}
		for(File f: source.listFiles())
		{
			try
			{
				Files.move(f.toPath(), new File(dest, f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
