package models;

import java.io.File;
import java.io.IOException;

import com.sun.jna.platform.FileUtils;

public class FileDeleteUtil extends FileSearchUtil {
	public void moveToTrash(File file)
	{
	FileUtils fileUtils = FileUtils.getInstance();
	    if (fileUtils.hasTrash()) 
	    {
	        try 
	        {
	            fileUtils.moveToTrash( new File[] {file });                
	        }
	        catch(NullPointerException e) 
	        {
	        	System.out.println("Item you are trying to delete doesn't exist");
	        }
	        catch (IOException ioe) 
	        {
	            ioe.printStackTrace();
	        }
	    }
	}
	
	public void deleteAllFiles(String path) 
	{	
		
		for(File file:getFilesInCurrentDir(path)) 
		{
			if(file.isDirectory()) 
			{
				deleteAllFiles(file.getPath());
			}
			moveToTrash(file);
		}
	}
}
