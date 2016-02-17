package com.epam.lab.news.loading.xml;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:configXmlLoading.properties")
public class DirectoryVisitor extends SimpleFileVisitor<Path> {

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	NewsLoader newsLoader;

	@Value("#{'${DirectoryVisitor.pathToErrorDirectory}'}")
	String pathToErrorDirectory;

	/**
	 * The method visitFile() is designed for working with a files.
	 *
	 * @param path
	 *            the path to the file
	 * @param fileAttributes
	 *            the file attributes
	 * @return the file visit result
	 */
	public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
		taskExecutor.execute(() -> {
			newsLoader.load(path);

		});
		return FileVisitResult.CONTINUE;
	}

	/**
	 * The method preVisitDirectory() is designed for browsing the directory.
	 *
	 * @param path
	 *            the path to the directory
	 * @param fileAttributes
	 *            the file attributes
	 * @return the file visit result
	 */
	public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
		String pathCurrently = path.toString();
		if (pathToErrorDirectory.equals(pathCurrently)) {
			return FileVisitResult.SKIP_SUBTREE;
		}
		return FileVisitResult.CONTINUE;
	}

}