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
package org.zsis.commons.core.log;

import java.io.Serializable;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public interface ILogConfigurer extends Serializable {

	/**
	 * Retorna o nome completo do arquivo de propriedades de configuração do log
	 * do eva-framework.
	 *
	 * @return O nome do arquivo (com o caminho completo).
	 */
	String getPath();

	/**
	 * TODO (dynamic 06/06/2013) - javadoc
	 * @return
	 */
	boolean isConfigureAndWatch();

	/**
	 * Retorna o valor da propriedade indicada por <code>key</code>.
	 *
	 * @param key
	 *            Nome da propriedade.
	 * @return O valor da propriedade indicada por <code>key</code>.
	 */
	String getProperty(String key);

	/**
	 * Retorna a definição do log root, no formato:
	 * <code>NIVEL_DO_LOG, APPENDER_1, APPENDER_2,
	 * APPENDER_N</code>.
	 * <p>
	 *
	 * <b>Exemplo:</b><br>
	 * <code>DEBUG, file, stdout</code>
	 *
	 * @return A {@link String} que define o log.
	 */
	String getRootLogger();

	/**
	 * Retorna a definição do log, no formato:
	 * <code>NIVEL_DO_LOG, APPENDER_1, APPENDER_2,
	 * APPENDER_N</code>.
	 * <p>
	 *
	 * <b>Exemplo:</b><br>
	 * <code>DEBUG, file, stdout</code>
	 *
	 * @param clazz
	 * @return A {@link String} que define o log.
	 */
	String getLogger(final Class<?> clazz);

	/**
	 * TODO (dynamic 06/06/2013) - javadoc
	 * @param mode
	 */
	void setLoggerMode(final LoggerMode mode);

	/**
	 * Retorna o modo de log que está ativo atualmente.
	 * <p>
	 *
	 * Esses modos podem ser: <code>DEBUG, INFO, ERROR ou OFF</code>.
	 *
	 * @return O modo de log atual.
	 */
	LoggerMode getCurrentMode();

}
