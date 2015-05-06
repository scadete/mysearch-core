package br.com.saulomachado.search.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileSystemCrawler {

	public static List<File> fetchFilesFromDirectory(String rootDirectoryPath,
			String extension) {
		return fetchFilesFromDirectory(new File(rootDirectoryPath), extension);
	}

	public static List<File> fetchFilesFromDirectory(File rootDirectory,
			String extension) {

		List<File> fileList = new ArrayList<File>();

		LinkedList<File> directoriesToVisit = new LinkedList<File>();
		directoriesToVisit.push(rootDirectory);

		while (!directoriesToVisit.isEmpty()) {
			File currentDir = directoriesToVisit.pop();
			File[] children = currentDir.listFiles();
			for (int i = children.length - 1; i >= 0; i--) {
				File child = children[i];
				if (child.isDirectory()) {
					directoriesToVisit.push(child);
				} else if (child.getName().toLowerCase().endsWith(extension)) {
					fileList.add(child);
				}
			}
		}

		return fileList;
	}
}
