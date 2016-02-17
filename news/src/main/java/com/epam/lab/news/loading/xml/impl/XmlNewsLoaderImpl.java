package com.epam.lab.news.loading.xml.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.xml.sax.SAXException;

import com.epam.lab.news.entity.News;
import com.epam.lab.news.loading.xml.NewsLoader;
import com.epam.lab.news.service.NewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
@PropertySource(value = "classpath:configXmlLoading.properties")
public class XmlNewsLoaderImpl implements NewsLoader {

	/** The news service. */
	@Autowired
	private NewsService newsService;

	/** The size blocking queue. */
	@Value("#{'${NewsLoader.sizeBlockingQueue}'}")
	private int sizeBlockingQueue;

	/** The path to error directory. */
	@Value("#{'${NewsLoader.pathToErrorDirectory}'}")
	private String pathToErrorDirectory;

	/** The regexp for search file. */
	@Value("#{'${NewsLoader.regexpForSearchFile}'}")
	private String regexpForSearchFile;

	/** The matcher. */
	private PathMatcher matcher;

	/** The blocking queue. */
	private BlockingQueue<Path> blockingQueue;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(XmlNewsLoaderImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.loading.xml.NewsLoader#init()
	 */
	@Override
	@PostConstruct
	public void init() {
		matcher = FileSystems.getDefault().getPathMatcher(regexpForSearchFile);
		blockingQueue = new ArrayBlockingQueue<Path>(sizeBlockingQueue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.lab.news.xmlLoading.NewsLoader#parseFile(java.nio.file.Path)
	 */
	@Override
	@Transactional
	public void load(Path path) {
		Path fileName = path.getFileName();
		if (matcher.matches(fileName)) {
			if (!blockingQueue.contains(fileName) && Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
				try {
					blockingQueue.put(fileName);
					Path fileAbsolutePath = path.toAbsolutePath();
					if (validateXmlFile(fileAbsolutePath)) {
						parseFile(path, fileName);
						Files.delete(path);
					} else {
						processingInvalidXml(path);
					}
				} catch (FileSystemException e) {
					LOGGER.info(e.getMessage());
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				} catch (JpaSystemException e) {
					LOGGER.info(e.getMessage());
					processingInvalidXml(path);
				} catch (IOException | InterruptedException e) {
					LOGGER.error(e.getMessage());
				} finally {
					blockingQueue.remove(fileName);
				}
			}
		} else {
			invalidFileToErrorDirectory(path, fileName);
		}
	}

	/**
	 * The method parseFile() is designed for parsing and addition a news to.
	 *
	 * @param path
	 *            the path to the file
	 * @param fileName
	 *            the file name
	 */
	private void parseFile(Path path, Path fileName) {
		Path fileAbsolutePath = path.toAbsolutePath();
		try (InputStream stream = Files.newInputStream(fileAbsolutePath);) {
			blockingQueue.put(fileName);
			ObjectMapper xmlMapper = new XmlMapper();
			List<News> listNews = xmlMapper.readValue(stream, new TypeReference<List<News>>() {
			});
			newsService.addListNews(listNews);
		} catch (IOException | InterruptedException e) {
			LOGGER.error(e.getMessage());
		}

	}

	/**
	 * The method invalidXmlToErrorDirectory() is designed for checking the
	 * existence of the invalid xml file. In the case of the existence of that
	 * file, move it to the error directory.
	 *
	 * @param path
	 *            the path to the invalid file
	 */
	public void processingInvalidXml(Path path) {
		while (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			move(path);
		}
	}

	/**
	 * The method invalidFileToErrorDirectory() is designed for checking the
	 * existence of the invalid file. In the case of the existence of that file,
	 * move it to the error directory.
	 *
	 * @param path
	 *            the path to the invalid file
	 * @param fileName
	 *            the file name
	 */
	private void invalidFileToErrorDirectory(Path path, Path fileName) {
		if (!blockingQueue.contains(fileName) && Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			try {
				blockingQueue.put(fileName);
				while (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
					move(path);
				}
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			} finally {
				blockingQueue.remove(fileName);
			}
		}
	}

	/**
	 * The method move() is designed for moving a invalid file to the error
	 * directory.
	 *
	 * @param path
	 *            the path to the invalid file
	 */
	private void move(Path path) {
		try {
			Path errorDirectory = Paths.get(pathToErrorDirectory);
			Path pathWrongFile = path.toAbsolutePath();
			String newNameWrongFile = FilenameUtils.getBaseName(path.toString()) + System.currentTimeMillis() + "."
					+ FilenameUtils.getExtension(pathWrongFile.toString());
			Path p = Paths.get(errorDirectory + "\\" + newNameWrongFile);
			Path pathToNewWrongFile = Files.createFile(p);
			Files.move(path, pathToNewWrongFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (FileSystemException e1) {
			LOGGER.info(e1.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * The method validateXmlFile() is designed for validation the xml file.
	 *
	 * @param path
	 *            the path to the file
	 * @return true, if that file is validate
	 */
	private boolean validateXmlFile(Path path) {
		try (FileInputStream fileInputStream = new FileInputStream(new File(path.toString()))) {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Resource resource = new ClassPathResource("newsXMLSchema.xsd");
			Source schemaLocation = new StreamSource(resource.getInputStream());
			Schema schema = schemaFactory.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(fileInputStream);
			validator.validate(source);
			return true;
		} catch (SAXException | FileSystemException e) {
			LOGGER.info(e.getMessage());
			return false;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

}
