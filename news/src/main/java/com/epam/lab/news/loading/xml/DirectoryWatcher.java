package com.epam.lab.news.loading.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:configXmlLoading.properties")
public class DirectoryWatcher {

	@Autowired
	DirectoryVisitor fileVisitor;

	@Value("#{'${DirectoryWatcher.startPath}'}")
	private String startPath;

	private static final Logger LOGGER = Logger.getLogger(DirectoryWatcher.class);

	/**
	 * The method Watch() designed for watching specified directory and
	 * searching files.
	 */
	@Scheduled(cron = "#{'${dirWatchingSchedule}'}")
	public void watch() {
		Path pathToDirectory = Paths.get(startPath);
		try {
			Files.walkFileTree(pathToDirectory, fileVisitor);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

}
