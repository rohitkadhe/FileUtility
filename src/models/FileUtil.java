package models;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.SearchOption;

public class FileUtil implements SearchOption {
    public int fileCount = 0;

    protected static File[] getFilesInCurrentDir(String path) {
	File directory = new File(path);
	File[] files = directory.listFiles();
	return files;
    }

    public static boolean fileExists(File file) {
	return file.exists();
    }

    protected static String removeFileNameExt(String fileName) {
	String fileNameWithoutExt = "";
	for (int i = 0; i < fileName.length(); i++) {
	    if (fileName.charAt(i) == '.') {
		break;
	    }
	    fileNameWithoutExt += fileName.charAt(i);
	}
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
	String detectIfAStringBeginsWith = "^";
	if (textHasSpecialCharacters(query)) {
	    detectIfAStringBeginsWith += "\\" + query;
	    Pattern p = Pattern.compile(detectIfAStringBeginsWith);
	    Matcher m = p.matcher(input);
	    return m.find();
	} else {
	    detectIfAStringBeginsWith += query;
	    Pattern p = Pattern.compile(detectIfAStringBeginsWith);
	    Matcher m = p.matcher(input);
	    return m.find();
	}
    }

    protected static boolean endsWith(String input, String query) {
	String detectIfAStringEndsWith = "$";
	if (textHasSpecialCharacters(query)) {
	    detectIfAStringEndsWith = "\\" + query + detectIfAStringEndsWith;
	    Pattern p = Pattern.compile(detectIfAStringEndsWith);
	    Matcher m = p.matcher(input);
	    return m.find();
	} else {
	    detectIfAStringEndsWith = query + detectIfAStringEndsWith;
	    Pattern p = Pattern.compile(detectIfAStringEndsWith);
	    Matcher m = p.matcher(input);
	    return m.find();
	}
    }

    protected static boolean contains(String input, String query) {
	return input.contains(query);
    }

    protected static boolean textHasSpecialCharacters(String input) {
	String detectSpecialCharacters = "^\\W";
	Pattern p = Pattern.compile(detectSpecialCharacters);
	Matcher m = p.matcher(input);
	return m.find();
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
