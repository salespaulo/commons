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
package org.ps.commons.core;

import java.io.Serializable;

import org.ps.commons.core.context.IContext;
import org.ps.commons.core.log.ILogConfigurer;

/**
 * TODO (dynamic 04/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public interface IEnvironment extends Serializable {

    /**
     * M&eacute;todo que deve retornar o diret&oacute;rio da aplica&ccedil;&atilde;o onde se encontra todos
     * os diret&oacute;rios e arquivos da que comp&otilde;em a aplica&ccedil;&atilde;o.
     *
     * @return Caminho do diret&oacute;rio principal da aplica&ccedil;&atilde;o.
     */
    String getDirectory();

    /**
     * M&eacute;todo que deve retornar uma implementa&ccedil;&atilde;o de contexto de
     * &lt;code&gt;lookup&lt;/code&gt; para a aplica&ccedil;&atilde;o.
     *
     * @return Contexto de &lt;code&gt;lookup&lt;/code&gt; da aplica&ccedil;&atilde;o.
     */
    IContext getContext();

	/**
	 * M&eacute;todo que deve retornar uma implementa&ccedil;&atilde;o de log
	 * para a aplica&ccedil;&atilde;o.
	 *
	 * @return Implementa&ccedil;&atilde;o de log para esta
	 *         aplica&ccedil;&atilde;o.
	 */
    ILogConfigurer getLogConfigurer();

}
