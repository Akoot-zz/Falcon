package com.Akoot.falcon;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import com.Akoot.falcon.user.User;
import com.Akoot.falcon.user.Users;
import com.Akoot.falcon.util.ChatColor;

public class Falcon
{
	public static final String NAME = "Falcon";
	public static final String VERSION = "0.0.6b";
	
	public static Scanner in;
	public static Console console;
	public static User user;

	public static File storage;
	public static File users;
	
	public static File dir;

	public static void main(String[] args)
	{
		in = new Scanner(System.in);
		console = new Console(NAME + "-" + VERSION);

		storage = new File(System.getProperty("user.home"), "cthulhu");
		users = new File(storage, "users");

		if(!storage.exists()) storage.mkdir();
		if(!users.exists()) users.mkdir();
		
		user = Users.lastLoggedIn();
		user.logIn();
		processArgs(args);
		
		console.trace("Logged in as " + (user.getName().equals("$") ? "a guest" : user.getName()) + ".");
		
		dir = new File(".");
		
		run();
	}
	
	public static void processArgs(String[] args)
	{
		if(args.length > 0)
		{
		}
	}

	public static void run()
	{
		System.out.print(ChatColor.WHITE + user.getName() + ChatColor.RESET + " ");
		String input = in.nextLine();
		exec(input);
		run();
	}

	public static void exec(String input)
	{
		String[] args = input.split(" ");
		String cmd = args[0];
		args = Arrays.copyOfRange(args, 1, args.length);
		if(!console.executeCommand(user, cmd, args)) console.trace(ChatColor.RED + "Unknown command: " + cmd);
	}

	public static void exit()
	{
		in.close();
		System.exit(0);
	}
}