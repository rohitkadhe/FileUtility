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
	        	
	        }
	        catch (IOException ioe) 
	        {
	            ioe.printStackTrace();
	        }
	    }
	}
	
	public void moveToRecycleBin(String path, String query, String searchOption) 
	{	
		
		for(File file:getFilesInCurrentDir(path)) 
		{
			if (file.isDirectory()) {
				moveToRecycleBin(file.getPath(), query, searchOption);
		    } 
		    else if (matches(file.getName(), query, searchOption)) {
				moveToTrash(file);
		    }
		}
	}
}
