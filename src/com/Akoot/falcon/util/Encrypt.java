package com.Akoot.falcon.util;

import java.io.File;

public class Encrypt
{
	private String key;
	
	public Encrypt()
	{
		this.key = "abcdefghijklmnopqrstuvwxyz";
	}
	
	public File encrypt(File file)
	{
		String nm = file.getName();
		
		if(!nm.contains(".")) System.out.println("Warning: " + nm + " does not have an extension!");
		
		String ext = (nm.contains(".") ? nm.substring(nm.indexOf(".") + 1) : "");
		String name = (nm.contains(".") ? nm.substring(0, nm.indexOf(".")) : nm);

		String newName = ext + name;
		newName = encrypt(newName, name.length()) + ext.length();
		
		FileUtil.rename(file, newName);

		return new File(file.getParentFile(), newName);
	}

	public File decrypt(File file)
	{
		String lastChar = file.getName().substring(file.getName().length() - 1, file.getName().length());
		try
		{
			int i = Integer.parseInt(lastChar);
			String ext = file.getName().substring(0, i);
			String name = file.getName().substring(i, file.getName().indexOf(i + ""));

			String newName = name + "." + ext;
			newName = decrypt(newName, name.length());

			FileUtil.rename(file, newName);

			return new File(file.getParentFile(), newName);
		}
		catch (Exception e)
		{
			System.out.println("Error with " + file + ": " + lastChar + " is not a number");
		}
		return file;
	}
	
	public String encrypt(String msg, int index)
	{
		String encryption = "";
		for(char c: msg.toCharArray())
		{
			int letter;
			String ch = Character.toString(c).toLowerCase();
			if(key.contains(ch))
			{
				letter = key.indexOf(ch) + index;
				while(letter >= key.length())
				{
					letter -= key.length();
				}
				encryption += key.substring(letter, letter + 1);
			}
			else
			{
				encryption += ch;
			}
		}
		return encryption;
	}

	public String decrypt(String msg, int index)
	{
		return encrypt(msg, key.length() - index);
	}
}
