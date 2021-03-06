/**
 * commons-app
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
package org.ps.commons.core.context;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public abstract class DefaultContext implements IContext {

	/** TODO (dynamic 04/06/2013) - javadoc */
	private static final long serialVersionUID = -7742124199837440067L;

	/* (non-Javadoc)
	 * @see org.zsis.commons.core.IContext#lookup(java.lang.Class)
	 */
	@Override
	public <T> T lookup(Class<?> clazz) {
		return lookup(EntryFactory.getInstance().get(clazz));
	}

}
