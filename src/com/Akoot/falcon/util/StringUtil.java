package com.Akoot.falcon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	
	public static String[] removeArg(String[] a, int index)
	{
		List<String> list = new LinkedList<String>(Arrays.asList(a));
		list.remove(index);
		return list.toArray(a);
	}
	
	public static boolean isNan(String s)
	{
		try
		{
			Integer.parseInt(s);
			return false;
		}
		catch (NumberFormatException e)
		{
			return true;
		}
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
	
	public static String[] removeFrom(String[] a, String s)
	{
		List<String> list = new ArrayList<String>();
		for(String str: a) if(str != s) list.add(str);
		return list.toArray(new String[0]);
	}
	
	public static String[] removeFrom(String[] a, int index)
	{
		return removeFrom(a, a[index]);
	}
	
	public static List<String> removeFrom(List<String> list, String s)
	{
		if(list.contains(s)) list.remove(list.indexOf(s));
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
		while (m.find()) list.add(m.group(1));
		return list;
	}
	
	public static List<String> getArgs(String data)
	{
		List<String> list = new ArrayList<String>();
		String regex = "\"([^\"]*)\"";
		Matcher matcher = Pattern.compile(regex).matcher(data);
		while(matcher.find()) list.add(matcher.group());
		return list;
	}

	public static String getQuote(String data, int group)
	{
		return getQuotes(data).get(group);
	}
}
