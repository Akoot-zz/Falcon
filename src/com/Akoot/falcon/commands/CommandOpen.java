package com.Akoot.falcon.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandOpen extends Command
{

	private boolean p;

	public CommandOpen()
	{
		this.name = "open";
		this.aliases.add("openurl");
		this.aliases.add("open-url");
	}

	public void open(List<String> urls)
	{
		for(String url: urls) 
		if(!url.matches("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$")) urls.remove(urls.indexOf(url));

		try
		{
			@SuppressWarnings("unused")
			Process process;
			//"C:\\Program Files\\Mozilla\\Firefox Developer Edition\\firefox.exe"
			ProcessBuilder pb;
			if(p)
			{
				for(String url: urls)
				{
					pb = new ProcessBuilder("C:\\Program Files\\Mozilla\\Firefox Developer Edition\\firefox.exe", "-private-window", url);
					process = pb.start();
					trace("private: " + pb.command() + "");
				}
			}
			else
			{
				List<String> args = new ArrayList<String>();
				args.add("C:\\Program Files\\Mozilla\\Firefox Developer Edition\\firefox.exe");
				args.addAll(urls);
				pb = new ProcessBuilder(args);
				process = pb.start();
				trace("normal: " + pb.command() + "");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onCommand()
	{
		List<String> urls = new ArrayList<String>();
		if(args.length > 0)
		{
			if(Arrays.asList(args).contains("-p")) p = true;
			if(!Arrays.asList(args).contains("-f"))
			{
				for(String url: args) if(!url.equalsIgnoreCase("-p")) urls.add(url);
				open(urls);
			}
			else
			{
				String fileName = Arrays.asList(args).get(args.length - 1);
				try 
				{
					Scanner in = new Scanner(new File(fileName));
					while(in.hasNextLine()) urls.add(in.nextLine());
					in.close();
					open(urls);
				}
				catch (FileNotFoundException e)
				{
					error("File not found: " + fileName);
				}
			}
		}
		else
		{
			sendUsage("http://website.com", "http://website1.com http://website2.com", "-f WebsiteList.txt", "-p http://private-site.com");
		}
	}
}
