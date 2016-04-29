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

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.ps.commons.core.context.ContextException;
import org.ps.commons.core.context.DefaultContext;
import org.ps.commons.core.context.IEntry;
import org.ps.commons.core.context.ObjectNotFoundContextException;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
class SpringContext extends DefaultContext {

	/** TODO (dynamic 04/06/2013) - javadoc */
	private static final long serialVersionUID = -650941104001659322L;

	/** TODO (dynamic 04/06/2013) - javadoc */
	private final ApplicationContext applicationContext;

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 */
	public SpringContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.IContext#lookup(org.zsis.commons.core.IEntry)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T lookup(IEntry entry) {
		try {
			return (T) applicationContext.getBean(entry.getName());
		} catch (NoSuchBeanDefinitionException e) {
			throw new ObjectNotFoundContextException(entry.getName(), e);
		} catch (Exception e) {
			throw new ContextException("Context lookup error", e);
		}
	}

}
