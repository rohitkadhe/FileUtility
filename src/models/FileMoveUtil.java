package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileMoveUtil extends FileUtil {
    private ArrayList<File> filesMoved;
    private ArrayList<File> filesCopied;

    public void moveFile(String sourcePath, String destPath) {
	File file = new File(sourcePath);
	String path = getPath(destPath, file);
	createDirIfDoesNotExist(destPath);
	file.renameTo(new File(path));
    }

    public void moveFiles(String source, String dest, String query, String searchOption) {
	setFilesMoved(new ArrayList<File>());
	for (File file : getFilesInCurrentDir(source)) {
	    if (file.isDirectory()) {
		moveFiles(file.getPath(), dest, query, searchOption);
	    } else if (matches(file.getName(), query, searchOption)) {
		filesMoved.add(file);
		moveFile(file.getPath(), dest);
	    }
	}
    }

    public void copyFiles(String source, String dest, String query, String searchOption) throws IOException {
	setFilesCopied(filesCopied);
	for (File file : getFilesInCurrentDir(source)) {
	    if (file.isDirectory()) {
		copyFiles(file.getPath(), dest, query, searchOption);
	    } else if (matches(file.getName(), query, searchOption)) {
		String path = getPath(dest, file);
		createDirIfDoesNotExist(dest);
		File destFile = new File(path);
		Files.copy(file.toPath(), destFile.toPath());
	    }
	}
    }

    public ArrayList<File> getFilesMoved() {
	return filesMoved;
    }

    public void setFilesMoved(ArrayList<File> filesMoved) {
	this.filesMoved = filesMoved;
    }

    public ArrayList<File> getFilesCopied() {
	return filesCopied;
    }

    public void setFilesCopied(ArrayList<File> filesCopied) {
	this.filesCopied = filesCopied;
    }
}
