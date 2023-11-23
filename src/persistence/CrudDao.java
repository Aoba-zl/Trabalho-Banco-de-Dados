package persistence;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica para operações CRUD (Create, Read, Update, Delete) em um banco de dados.
 * @param <T> Tipo de objeto associado à operação CRUD.
 */
public interface CrudDao<T>
{

	/**
	 * Insere um objeto no banco de dados.
	 * @param t Objeto a ser inserido.
	 * @throws SQLException Se ocorrer um erro ao executar a operação no banco de dados.
	 */
	void insert(T t) throws SQLException;

	/**
	 * Atualiza um objeto no banco de dados.
	 * @param t Objeto a ser atualizado.
	 * @throws SQLException Se ocorrer um erro ao executar a operação no banco de dados.
	 */
	void update(T t) throws SQLException;

	/**
	 * Exclui um objeto do banco de dados.
	 * @param t Objeto a ser excluído.
	 * @throws SQLException Se ocorrer um erro ao executar a operação no banco de dados.
	 */
	void delete(T t) throws SQLException;

	/**
	 * Consulta um objeto no banco de dados.
	 * @param t Objeto a ser consultado.
	 * @return O objeto consultado, ou null se não encontrado.
	 * @throws SQLException Se ocorrer um erro ao executar a operação no banco de dados.
	 */
	T consult(T t) throws SQLException;

	/**
	 * Lista todos os objetos do tipo T no banco de dados.
	 * @return Uma lista de objetos do tipo T.
	 * @throws SQLException Se ocorrer um erro ao executar a operação no banco de dados.
	 */
	List<T> list() throws SQLException;
}
