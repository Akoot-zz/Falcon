package com.Akoot.falcon.commands;

import com.Akoot.falcon.util.Encrypt;
import com.Akoot.falcon.util.StringUtil;

public class CommandShift extends Command
{
	public CommandShift()
	{
		this.name = "shift";
		this.aliases.add("caesarshift");
		this.aliases.add("cs");
	}

	@Override
	public void onCommand()
	{
		Encrypt enc = new Encrypt();
		int index = 1 + (int) (Math.random() * enc.getMax());
		if(args.length == 0)
		{
			trace("[To encrypt]");
			sendUsage("<message>", "<message> [index]", "\"<message with spaces>\" [index]");
			trace("[To decrypt]");
			sendUsage("-d <message>", "-d <message> [index]", "-d \"<message with spaces>\" [index]");
		}
		else if(args.length == 1)
		{
			index = args[0].length();
			trace("Index: " + index);
			trace("Encrypted: " + enc.encrypt(args[0], index));
		}
		else if(args.length == 2)
		{
			if(args[0].equalsIgnoreCase("-d"))
			{
				index = args[1].length();
				trace("Index: " + index);
				trace("Decrypted: " + enc.decrypt(args[1], index));
			}
			else trace("Decrypted: " + enc.encrypt(args[0], Integer.parseInt(args[1])));
		}
		else if(args.length >= 3)
		{
			boolean decrypt = args[0].equalsIgnoreCase("-d");
			String a1 = decrypt ? args[1] : args[0];
			String lastArg = args[args.length - 1];
			if(!StringUtil.isNan(lastArg))
			{
				index = Integer.parseInt(lastArg);
				args = StringUtil.removeArg(args, args.length);
			}
			String msg = a1.startsWith("\"") ? StringUtil.toString(args) : StringUtil.getQuote(StringUtil.toString(args), 0);

			if(decrypt)
			{
				trace("d: " + msg);
			}
			else
			{
				trace("e: " + msg);
			}

		}
	}
}
