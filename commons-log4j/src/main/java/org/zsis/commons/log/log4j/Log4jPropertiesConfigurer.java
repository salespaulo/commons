/**
 * commons-log4j
 *
 * Copyright [2013] [Z Sistemas Ltda]

 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.zsis.commons.log.log4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.zsis.commons.core.log.DefaultLogConfigurer;
import org.zsis.commons.core.log.LogConfigurerException;
import org.zsis.commons.core.log.LoggerMode;
import org.zsis.commons.core.util.LoaderUtils;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
class Log4jPropertiesConfigurer extends DefaultLogConfigurer {

	/** TODO (dynamic 06/06/2013) - javadoc */
	private static final long serialVersionUID = 7583596570693072604L;

	/** TODO (dynamic 06/06/2013) - javadoc */
	private static final String ROOTLOGGER = "log4j.rootLogger";
	/** TODO (dynamic 06/06/2013) - javadoc */
	private static final String PREFIX_LOGGER = "log4j.logger.";

	/** TODO (dynamic 06/06/2013) - javadoc */
	private final Properties properties = new Properties();

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param path
	 */
	public Log4jPropertiesConfigurer(final String path, final boolean configureAndWatch) {
		super(path, configureAndWatch);
		final InputStream input = LoaderUtils.getInputStream(path);

		try {
			this.properties.load(input);
		} catch (IOException eIO) {
			throw new LogConfigurerException("Read log4j properties error", eIO);
		} finally {
			IOUtils.closeQuietly(input);
		}
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.ILogConfigurer#getProperty(java.lang.String)
	 */
	@Override
	public String getProperty(String key) {
		return properties.getProperty(key, "");
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.ILogConfigurer#getRootLoggerDefinition()
	 */
	@Override
	public String getRootLogger() {
		return properties.getProperty(ROOTLOGGER, "");
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.ILogConfigurer#getLoggerDefinition()
	 */
	@Override
	public String getLogger(final Class<?> clazz) {
		String packageName = clazz.getPackage().getName();

		while (packageName.lastIndexOf('.') != -1) {
			if (properties.getProperty(PREFIX_LOGGER + packageName) != null) {
				return properties.getProperty(PREFIX_LOGGER + packageName);
			}

			if (packageName.lastIndexOf('.') != -1) {
				packageName = packageName.substring(0, packageName.lastIndexOf('.'));
			}
		}

		return "";
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.ILogConfigurer#setLoggerMode(org.zsis.commons.core.LoggerMode)
	 */
	@Override
	public void setLoggerMode(final LoggerMode mode) {
		final Iterator<Object> keys = properties.keySet().iterator();

		while (keys.hasNext()) {
			final String key = (String) keys.next();

			if (key.contains(PREFIX_LOGGER)) {
				final String value = properties.getProperty(key);
				final StringBuilder newValue = new StringBuilder();
				newValue.append(mode);
				newValue.append(value.substring(value.indexOf(',')));
				properties.setProperty(key, newValue.toString());
			}
		}

		try {
			final FileOutputStream stream = new FileOutputStream(LoaderUtils.getFile(getPath()));
			properties.store(stream, "set log mode to " + mode);
			IOUtils.closeQuietly(stream);
		} catch(IOException eIO) {
			throw new LogConfigurerException("Write log4j properties error", eIO);
		}
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.ILogConfigurer#getCurrentMode()
	 */
	@Override
	public LoggerMode getCurrentMode() {
		final Iterator<Object> keys = properties.keySet().iterator();

		while (keys.hasNext()) {
			final String key = (String) keys.next();

			if (key.contains(PREFIX_LOGGER)) {
				final String value = properties.getProperty(key);

				if (value.indexOf(',') != -1) {
					return LoggerMode.valueOf(value.substring(0, value.indexOf(',')));
				}

				return LoggerMode.valueOf(value);
			}
		}

		return null;
	}

}
