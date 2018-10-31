package com.rar.sampleapi.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtil {

	/**
	 * Instantiates a new file reader util.
	 */
	private FileReaderUtil() {

	}

	/**
	 * Gets the contents from file.
	 *
	 * @param file
	 *            the file
	 * @return the contents from file
	 * @throws IOException
	 */
	public static String getContentsFromFileTest(final String file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
}
