package com.Akoot.falcon.commands;

import java.io.File;

import com.Akoot.falcon.Falcon;
import com.Akoot.falcon.util.ChatColor;
import com.Akoot.falcon.util.Encrypt;
import com.Akoot.falcon.util.StringUtil;

public class CommandFF extends Command
{
	public CommandFF()
	{
		this.name = "ff";
	}

	@Override
	public void onCommand()
	{
		if(args.length == 0)
		{
			//
		}
		else if(args.length >= 1)
		{
			boolean enc = false;
			if(args[0].matches("e.*"))
			{
				enc = true;
			}
			else if(args[0].matches("d.*"))
			{
				enc = false;
			}
			else
			{
				invalidSyntax(0);
				return;
			}

			if(args.length >= 2)
			{
				if(args[1].startsWith("\"")) ff(enc, new File(StringUtil.getQuote(StringUtil.toString(args), 0)));
				else ff(enc, new File(args[1]));
			}
			else ff(enc, Falcon.dir);
		}
	}

	private void ff(boolean enc, File source)
	{
		trace(ChatColor.PURPLE + (enc ? "en" : "de") + "crypting...");
		Encrypt e = new Encrypt();
		if(!source.isDirectory())
		{
			e.encrypt(source);
			trace("Done.");
			return;
		}
		for(File f: source.listFiles())
		{
			if(enc) e.encrypt(f);
			else e.decrypt(f);
		}
		trace("Done.");
	}
}
