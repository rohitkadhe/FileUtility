package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileMoveUtil extends FileUtil {

	public void moveFiles(String source, String dest, String query, String searchOption) {
		for (File file : getFilesInCurrentDir(source)) {
			if (query.isEmpty()) {
				return;
			}
			if (file.isDirectory()) {
				moveFiles(file.getPath(), dest, query, searchOption);
			} else if (matches(file.getName(), query, searchOption)) {
				moveFile(file.getPath(), dest);
			}
		}
	}

	public void copyFiles(String source, String dest, String query, String searchOption) throws IOException {
		for (File file : getFilesInCurrentDir(source)) {
			if (query.isEmpty()) {
				return;
			}
			if (file.isDirectory()) {
				copyFiles(file.getPath(), dest, query, searchOption);
			} else if (matches(file.getName(), query, searchOption)) {
				String path = getPath(dest, file);
				createDirIfDoesNotExist(dest);
				File destFile = new File(path);
				if (!destFile.exists()) {
					Files.copy(file.toPath(), destFile.toPath());
				}
			}
		}
	}

	private void moveFile(String sourcePath, String destPath) {
		File file = new File(sourcePath);
		String path = getPath(destPath, file);
		createDirIfDoesNotExist(destPath);
		if (!new File(path).exists()) {
			file.renameTo(new File(path));
		}
	}
}
