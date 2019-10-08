package models;

import java.io.File;
import java.util.ArrayList;
import interfaces.SearchOption;

public class FileSearchUtil extends FileUtil implements SearchOption {

    public ArrayList<File> searchFiles(String path, String searchQuery, String searchOption) {
	ArrayList<File> files = new ArrayList<File>();
	searchFile(path, searchQuery, files, searchOption);
	return files;
    }

    private void searchFile(String path, String searchQuery, ArrayList<File> files, String searchOption) {
	for (File file : getFilesInCurrentDir(path)) {
	    if (file.isDirectory()) {
		searchFile(file.getPath(), searchQuery, files, searchOption);
	    } else if (matches(file.getName(), searchQuery, searchOption)) {
		fileCount++;
		files.add(file);
	    }
	}
    }
}
