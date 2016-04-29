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
package org.zsis.commons.core.context;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public class ContextFactory {

	/** TODO (dynamic 04/06/2013) - javadoc */
	private static final ContextFactory INSTANCE = new ContextFactory();

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @return
	 */
	public static ContextFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * TODO (dynamic 04/06/2013) - javadoc
	 * @param builder
	 * @return
	 */
	public IContext get(final IContextBuilder builder) {
		return builder.build();
	}

}
