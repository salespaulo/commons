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

import org.zsis.commons.core.log.ILogConfigurer;
import org.zsis.commons.core.log.ILogConfigurerBuilder;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public class Log4jPropertiesConfigurerBuilder implements ILogConfigurerBuilder {

	/** TODO (dynamic 06/06/2013) - javadoc */
	private static final long serialVersionUID = -2625481982322819928L;

	/** TODO (dynamic 04/06/2013) - javadoc */
	private static final Log4jPropertiesConfigurerBuilder INSTANCE = new Log4jPropertiesConfigurerBuilder();

	/** TODO (dynamic 04/06/2013) - javadoc */
	private String log4jPropertiesPath;

	/** TODO (dynamic 04/06/2013) - javadoc */
	private boolean configureAndWatch = true;

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param log4jPropertiesPath
	 * @return
	 */
	public static Log4jPropertiesConfigurerBuilder getInstance(final String log4jPropertiesPath, final boolean configureAndWatch) {
		INSTANCE.setLog4jPropertiesPath(log4jPropertiesPath);
		INSTANCE.setConfigureAndWatch(configureAndWatch);
		return INSTANCE;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param log4jPropertiesPath
	 * @return
	 */
	public static Log4jPropertiesConfigurerBuilder getInstance(final String log4jPropertiesPath) {
		INSTANCE.setLog4jPropertiesPath(log4jPropertiesPath);
		return INSTANCE;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @return
	 */
	public static Log4jPropertiesConfigurerBuilder getInstance() {
		return INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.ILogConfigurerBuilder#build()
	 */
	@Override
	public ILogConfigurer build() {
		return new Log4jPropertiesConfigurer(this.log4jPropertiesPath, this.configureAndWatch);
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param log4jPropertiesPath the log4jPropertiesPath to set
	 */
	public void setLog4jPropertiesPath(String log4jPropertiesPath) {
		this.log4jPropertiesPath = log4jPropertiesPath;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param configureAndWatch the configureAndWatch to set
	 */
	public void setConfigureAndWatch(boolean configureAndWatch) {
		this.configureAndWatch = configureAndWatch;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 */
	private Log4jPropertiesConfigurerBuilder() {
		super();
	}

}
