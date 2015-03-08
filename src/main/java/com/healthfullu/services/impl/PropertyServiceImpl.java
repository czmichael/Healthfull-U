package com.healthfullu.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.healthfullu.services.PropertyService;

/**
 * @see PropertyService
 */
@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {

	private static Logger logger = LoggerFactory
			.getLogger(PropertyServiceImpl.class);
	public static Properties ruheProperties = null;
	private static String INPUT_FILE_NAME = "hu_property.xml";

	/*
	 * Load content of the XML property file
	 */
	public void loadProperties() {
		ruheProperties = new Properties();
		InputStream fis = null;

		try {
			fis = getClass().getClassLoader().getResourceAsStream(
					INPUT_FILE_NAME);
			ruheProperties.loadFromXML(fis);
		} catch (IOException e) {
			logger.error("Unable to find the properties file", e);
			System.exit(1);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	public String getProperty(String key, String... defaultValue) {
		loadProperties();

		if (hasProperty(key)) {
			return ruheProperties.getProperty(key);
		} else {
			if (defaultValue.length > 0) {
				return defaultValue[0];
			} else {
				throw new RuntimeException("property with key: " + key
						+ " not existed.");
			}
		}
	}

	@Override
	public Integer getPropertyAsInt(String key, Integer... defaultValue) {
		loadProperties();

		if (hasProperty(key)) {
			return Integer.parseInt(getProperty(key));
		} else {
			if (defaultValue.length > 0) {
				return defaultValue[0];
			} else {
				throw new RuntimeException("property with key: " + key
						+ " not existed.");
			}
		}
	}

	@Override
	public Boolean getPropertyAsBoolean(String key, Boolean... defaultValue) {
		loadProperties();
		if (hasProperty(key)) {
			return Boolean.parseBoolean(getProperty(key));
		} else {
			if (defaultValue.length > 0) {
				return defaultValue[0];
			} else {
				throw new RuntimeException("property with key: " + key
						+ " not existed.");
			}
		}
	}

	/*
	 * Test if named property exists.
	 */
	private boolean hasProperty(String key) {
		Object value = null;
		value = ruheProperties.get(key);
		return value != null;
	}
}
