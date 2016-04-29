/**
 * commons-spring
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
package org.zsis.commons.context.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ps.commons.core.context.IContext;
import org.ps.commons.core.context.IContextBuilder;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public class SpringContextBuilder implements IContextBuilder {

	/** TODO (dynamic 04/06/2013) - javadoc */
	private static final long serialVersionUID = -5980649346671401956L;

	/** TODO (dynamic 04/06/2013) - javadoc */
	private static final SpringContextBuilder INSTANCE = new SpringContextBuilder();

	/** TODO (dynamic 04/06/2013) - javadoc */
	private ApplicationContext applicationContext;

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param springContextFileName
	 * @return
	 */
	public static SpringContextBuilder getInstance(final String springContextFileName) {
		INSTANCE.setApplicationContext(new ClassPathXmlApplicationContext("classpath:" + springContextFileName));
		return INSTANCE;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @return
	 */
	public static SpringContextBuilder getInstance(final ApplicationContext applicationContext) {
		INSTANCE.setApplicationContext(applicationContext);
		return INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.IContextBuilder#build()
	 */
	@Override
	public IContext build() {
		return new SpringContext(applicationContext);
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 */
	private SpringContextBuilder() {
		super();
	}

}
