package com.Akoot.falcon.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil 
{
	public static void rename(File file, String name)
	{
		try 
		{
			Files.move(file.toPath(), file.toPath().resolveSibling(name));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
