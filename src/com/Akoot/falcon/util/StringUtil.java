package com.Akoot.falcon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil 
{
	public static String getRegex(String regex, String data)
	{
		return getRegex(regex, data, 0);
	}

	public static String getRegex(String regex, String data, int group)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		if(matcher.find())
		{
			data = matcher.group(group);
		}
		return data;
	}
	
	public static String randomNumber(int min, int max)
	{
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min + "";
	}
	
	public static String randomNumber(int length)
	{
		String n = "";
		for(int i = 0; i < length; i++) n += new Random().nextInt(9);
		return n;
	}
	
	public static List<String> removeFrom(String[] list, Object o)
	{
		return removeFrom(Arrays.asList(list), o);
	}
	
	public static List<String> removeFrom(List<String> list, Object o)
	{
		if(list.contains(o)) list.remove(list.indexOf(o));
		return list;
	}

	public static String toString(String[] a)
	{
		return toString(Arrays.asList(a));
	}
	
	public static String toString(List<String> a)
	{
		String msg = "";
		for(String s: a) msg += s + " ";
		return msg.trim();
	}

	public static List<String> getQuotes(String data)
	{
		List<String> list = new ArrayList<String>();
		if(!data.contains("\""))
		{
			list.add(data);
			return list;
		}
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(data);
		while (m.find())
		{
			list.add(m.group(1));
		}
		return list;
	}
	
	public static String getArg(String data)
	{
		return getRegex("--(\\w+) (\\w+)", data, 2);
	}

	public static String getQuote(String data, int group)
	{
		return getQuotes(data).get(group);
	}
}
