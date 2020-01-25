package models;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import interfaces.SearchOption;

public class FileUtil implements SearchOption {
    protected static File[] getFilesInCurrentDir(String path) {
		File directory = new File(path);
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File[] files = fsv.getFiles(directory, false);
		return files;
    }

    public static boolean fileExists(File file) {
    	return file.exists();
    }

    protected static String removeFileNameExt(String fileName) {
    	String fileNameWithoutExt = "";
    	String regex = "(\\.[^.]+)$";
		fileNameWithoutExt = fileName.replaceAll(regex, "");
		return fileNameWithoutExt;
    }

    protected static String getPath(String destPath, File file) {
		String moveToPath = destPath + "\\" + file.getName();
		return moveToPath;
    }

    protected static void createDirIfDoesNotExist(String destPath) {
    	File destFile = new File(destPath);
		if (!destFile.exists()) {
		    destFile.mkdir();
		}
    }

    protected static boolean beginsWith(String input, String query) {
    	String detectIfAStringBeginsWithRegex = "^";
		    detectIfAStringBeginsWithRegex += escapeSpecialCharacters(query) + ".*$";
		    return input.matches(detectIfAStringBeginsWithRegex);
    }

    protected static boolean endsWith(String input, String userQuery) {
		String in = input;
    	if(!userQueryContainsExtension(userQuery)) {
    		in = removeFileNameExt(input);
		}
    	String detectIfAStringEndsWithRegex = ".*";
	    detectIfAStringEndsWithRegex += escapeSpecialCharacters(userQuery) + "$";
	    return in.matches(detectIfAStringEndsWithRegex);
    }

    private static boolean userQueryContainsExtension(String query) {
    	String regex = "(\\.[^.]+)$";
    	if(query.matches(regex)) {
    		return true;
    	}
    	return false;
    }
    protected static boolean contains(String input, String query) {
    	return input.contains(query);
    }

    protected static String escapeSpecialCharacters(String input) {
		String detectSpecialCharactersRegex = "^\\W";
		if(input.matches(detectSpecialCharactersRegex)) {
			return input = "\\" + input;
		};
		return input;
    }

    public static boolean matches(String input, String query, String option) {
    	switch (option) {
			case EQUALS:
			    return removeFileNameExt(input).equals(query) || input.equals(query);
			case BEGINS_WITH:
			    return beginsWith(input, query);
			case CONTAINS:
			    return contains(input, query);
			case ENDS_WITH:
			    return endsWith(input, query);
			default:
				return false;
		}
    }
}
