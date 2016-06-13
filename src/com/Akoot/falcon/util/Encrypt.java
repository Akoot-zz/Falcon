package com.Akoot.falcon.util;

import java.io.File;

public class Encrypt
{
	private String key;

	public Encrypt(String key)
	{
		this.key = key;
	}

	public Encrypt()
	{
		this("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
	}
	
	public void encrypt(File file)
	{
		String name = file.getName();
		File parent = file.getParentFile();
		String extension = name.contains(".") ? name.substring(name.indexOf(".")) : "";
		name = extension.isEmpty() ? name : name.substring(0, name.indexOf("."));
		String newName = name;
		
		if(extension.isEmpty()) newName = encrypt(name, name.length());
		else newName = encrypt(extension, extension.length());
		
		FileUtil.rename(file, newName);
	}

	public void decrypt(File file)
	{
		String name = file.getName();
		File parent = file.getParentFile();
		String extension = "";
		String newName = name;
		
		FileUtil.rename(file, newName + extension);
	}

	public String encrypt(String msg, int index)
	{
		String encryption = "";
		for(char c: msg.toCharArray())
		{
			int letter;
			String ch = Character.toString(c);//.toLowerCase();
			if(key.contains(ch))
			{
				letter = key.indexOf(ch) + index;
				while(letter >= key.length()) letter -= key.length();
				encryption += key.substring(letter, letter + 1);
			}
			else encryption += ch;
		}
		return encryption;
	}

	public String decrypt(String msg, int index)
	{
		return encrypt(msg, key.length() - index);
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public int getMax()
	{
		return this.key.length();
	}
}
