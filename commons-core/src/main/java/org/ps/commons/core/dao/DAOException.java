/**
 * catranfe-core
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
package org.ps.commons.core.dao;

/**
 * TODO (dynamic 03/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public class DAOException extends RuntimeException {

	/** TODO (dynamic 03/06/2013) - javadoc */
	private static final long serialVersionUID = 5712086570346980782L;

	/**
	 * Construtor que recebe uma mensagem descritiva desta exce&ccedil;&atilde;o.
	 * @param msg Mensagem do problema ocorrido.
	 */
	public DAOException(final String msg) {
		super(msg);
	}

	/**
	 * Construtor que recebe a exce&ccedil;&atilde;o de origem.
	 * @param cause Causa(origem) do problema.
	 */
	public DAOException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Construtor que recebe uma mensagem descritiva desta exce&ccedil;&atilde;o e
	 * a exce&ccedil;&atilde;o de origem.
	 * @param msg Mensagem do problema ocorrido.
	 * @param cause Causa(origem) do problema.
	 */
	public DAOException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

}
