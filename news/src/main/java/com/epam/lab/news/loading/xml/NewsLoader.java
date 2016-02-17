package com.epam.lab.news.loading.xml;

import java.nio.file.Path;

public interface NewsLoader {

	/**
	 * The method parseNews() is designed for parsing the file.
	 *
	 * @param path the path to the file
	 */
	void load(Path path);
	
	void init();
	
}
