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

import java.io.Serializable;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public interface IContext extends Serializable {

	/**
	 * Obtem o objeto identificado por <code>name</code> no contexto da aplicação.
	 *
	 * @param name Nome com o qual o objeto foi registrado no contexto da aplicação.
	 * @return A instância do objeto <code>name</code>.
	 */
	<T> T lookup(final IEntry entry);

	/**
	 * Obtem o objeto identificado por {@link Class#getSimpleName()}
	 * no contexto da aplicação.
	 * @param clazz {@link Class} do lookup desejado.
	 * @return A instância do objeto <code>name</code>.
	 */
	<T> T lookup(final Class<?> clazz);

}
