package com.adventure.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

public class ConfigurationParser {

	public Configuration parse(File configFile) throws ConfigurationException {
		
		Configuration config = new Configuration();
		
		BufferedReader reader = null;
		try {
			reader = Files.newBufferedReader(configFile.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new ConfigurationException("Failed to open input configuration file: " + configFile.getPath());
		}
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arguments = line.split("-");
				
				String type = arguments[0].trim();
				
				if (!Arrays.stream(Types.values()).anyMatch((t) -> t.name().equals(type))) {
					throw new ConfigurationException("Type " + type + " is forbidden.");
				}
				
				
				String x = arguments[1].trim();
				String y = arguments[2].trim();
				
				System.out.println(type + x + y);
			}
		} catch (IOException e) {
			throw new ConfigurationException("Failed to read lines of configuration file: " + configFile.getPath());
		}
		
		return config;
	}

}
