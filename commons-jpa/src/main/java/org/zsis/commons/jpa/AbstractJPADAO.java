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
package org.zsis.governanta.infra.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zsis.governanta.domain.entity.IEntity;
import org.zsis.governanta.infra.dao.IDAO;
import org.zsis.governanta.infra.dao.DAOException;

/**
 * TODO (dynamic 03/06/2013) - javadoc
 * @author dynamic
 * @version
 */
public class AbstractJPADAO <E extends IEntity<? extends Serializable>, I extends Serializable> implements IDAO {

	/** Propriedade que armazena o {@EntityManager} injetado pelo container */
	@PersistenceContext(unitName="derbyPersistenceUnit")
	private EntityManager entityManager;

	/** Propriedade que armazena o gerenciador de log desta classe */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractJPADAO.class);

	/**
	 * Retorna um entity manager.
	 * @return Entity manager.
	 */
	protected EntityManager getEntityManager() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Recuperando o " + entityManager.getClass().getSimpleName() + "[" + this.entityManager + "].");
		}

		return this.entityManager;
	}

	/**
	 * Seta um entity manager.
	 * @return Entity manager.
	 */
	protected void setEntityManager(final EntityManager entityManager) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Setando o " + entityManager.getClass().getSimpleName() + "[" + entityManager + "].");
		}

		this.entityManager = entityManager;
	}

	/**
	 * Fecha uma conexao, ou seja, fecha um entity manager sendo usado.
	 * @throws org.zsis.mopo.core.dao.DAOException Caso ocorra algum problema fechando a conexao.
	 */
	protected void fecharEntityManager() throws DAOException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Fechando o " + getEntityManager().getClass().getSimpleName() + "[" + getEntityManager() + "].");
		}

		if (getEntityManager() != null && getEntityManager().isOpen()) {
			getEntityManager().close();
		}
	}

	/**
	 * Persiste um objeto na base de dados.
	 * @param entidade Objeto que ser&aacute; persistido.
	 * @throws org.zsis.mopo.core.dao.DAOException Caso ocorra algum problema persistindo o objeto passado.
	 */
	protected void persistir(final Object entidade) throws DAOException {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getEntityManager().persist(): " + entidade);
			}

			getEntityManager().persist(entidade);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Atualiza na base de dados o objeto passado. Retorna a instancia da
	 * entidade passada, mas desta vez no status <b>managed</b>, gerenciado pelo {@link EntityManager}.
	 * @param entidade Entidade que sera atualizada.
	 * @return Entidade no status <b>managed</b>.
	 * @throws org.zsis.mopo.core.dao.DAOException Caso ocorre algum problema atualizando a entidade.
	 */
	protected E atualizar(final E entidade) throws DAOException {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getEntityManager().merge(): " + entidade);
			}

			return getEntityManager().merge(entidade);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Remove fisicamente um objeto da base de dados.
	 * @param entidade Objeto a ser removido.
	 * @throws org.zsis.mopo.core.dao.DAOException Caso ocorra algum problema excluindo o objeto.
	 */
	protected void remover(final Object entidade) throws DAOException {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getEntityManager().remove(): " + entidade);
			}

			getEntityManager().remove(entidade);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Renova as propriedades do objeto passado. Este metodo atualiza o objeto
	 * passado para representar exatamente os dados que estao na base de dados.
	 * @param entidade Objeto que sera atualizado.
	 * @throws org.zsis.mopo.core.dao.DAOException Caso ocorra algum problema atualizando o objeto passado.
	 */
	protected void recuperar(final Object entidade) throws DAOException {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getEntityManager().refresh(): " + entidade);
			}

			getEntityManager().refresh(entidade);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Retorna uma entidade a partir da chave passada. Este metodo faz a consulta
	 * na base de dados pela entidade com a chave passada.
	 * @param entidadeClass <code>Class</code> da entidade que procuramos.
	 * @param chavePrimaria Chave da entidade.
	 * @return Entidade gerenciada <b>managed</b>.
	 * @throws org.zsis.mopo.core.dao.DAOException Caso ocorra algum problema na procura da
	 * entidade na base de dados.
	 */
	protected E buscar(final Class<E> entidadeClass, final I chavePrimaria) throws DAOException {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getEntityManager().find(): " + entidadeClass.getSimpleName() + ":PK[" + chavePrimaria + "]");
			}

			return getEntityManager().find(entidadeClass, chavePrimaria);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Retorna todos os registros de uma entidade identificada pelo par&acirc;metro
	 * <code>entidadeClass</code> em uma lista ordenada pelos campos passados pelo
	 * par&acirc;metro <code>camposOrderBy</code>.
	 * @param entidadeClass Entidade que deve ser retornada a lista de registros.
	 * @param camposOrderBy Os campos que devem ordenar a lista de registros.
	 * @return Uma lista com todos os registros da entidade passada.
	 */
	protected List<E> buscarTodos(final Class<E> entidadeClass, final String ...camposOrderBy) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getEntityManager().findAll(): " + entidadeClass.getSimpleName() + ":orderBy[" + camposOrderBy + "]");
			}

			final StringBuilder consultaHQL = new StringBuilder();
			consultaHQL.append("from ");
			consultaHQL.append(entidadeClass.getSimpleName());
			consultaHQL.append(" order by ");

			for (String campo: camposOrderBy) {
				consultaHQL.append(campo).append(", ");
			}

			consultaHQL.deleteCharAt(consultaHQL.length() - 1);
			final Query query = getEntityManager().createQuery(consultaHQL.toString());

			@SuppressWarnings("unchecked")
			List<E> resultList = query.getResultList();

			if (resultList == null || resultList.isEmpty()) {
				resultList = Collections.emptyList();
			}

			return resultList;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

    /**
     * Este método facilita na verificação de uma lista de resultados retornados
     * por uma {@link Query}. Se a {@link Query} não encontrar resultados na
     * busca ela retorna <code>null</code>, portanto, este método trata o
     * resultado para não retornar um <code>null</code> e sim uma coleção vazia
     * do objeto esperado.
     *
     * @param query Objeto que contém a consulta a ser feita.
     * @return Uma lista não nula de objetos do tipo T.
     */
    protected Collection<E> getResultListNotNull(Query query) {
        @SuppressWarnings("unchecked")
		Collection<E> resultList = query.getResultList();

        if (resultList == null) {
            return new ArrayList<E>();
        }

        return resultList;
    }
}
