package br.edu.ifpb.database;

import java.util.ArrayList;

public abstract class DatabaseController<T> {
  protected final DatabaseService databaseService;
  protected final String tableName;
  protected final String[] columns;

  /**
   * Construtor.
   *
   * @param databaseService Instância do serviço de banco de dados para realizar
   * operações CRUD.
   * @param tableName Nome da tabela no banco de dados.
   * @param columns Nome das colunas da tabela que serão manipuladas.
   */
  public DatabaseController(
          DatabaseService databaseService,
          String tableName,
          String[] columns
  ) {
    this.databaseService = databaseService;
    this.tableName = tableName;
    this.columns = columns;
  }

  /**
   * Insere um novo registro na tabela.
   *
   * @param values Valores a serem inseridos nas colunas da tabela.
   * @return Retorna o ID do registro criado.
   */
  public String insert(Object... values) {
    return databaseService.create(buildInsertQuery(), values);
  }

  /**
   * Busca um registro pelo ID na tabela.
   *
   * @param id O identificador do registro a ser buscado.
   * @return Uma lista contendo os dados do registro.
   */
  public ArrayList<Object[]> findById(String id) {
    String query = "SELECT * FROM " + tableName + " WHERE id = ?";

    return databaseService.read(query, id);
  }

  /**
   * Busca todos os registros da tabela.
   *
   * @return Uma lista contendo todos os registros da tabela.
   */
  public ArrayList<Object[]> findAll() {
    String query = "SELECT * FROM " + tableName;

    return databaseService.read(query);
  }

  /**
   * Busca todos os registros da tabela com uma condição especificada.
   *
   * @param condition A condição a ser usada no WHERE.
   * @param params Os parâmetros que serão passados para a consulta.
   * @return Uma lista contendo os dados dos registros que atendem à condição.
   */
  public ArrayList<Object[]> findAllByCondition(String condition, Object... params) {
    String query = "SELECT * FROM " + tableName + " WHERE " + condition;

    return databaseService.read(query, params);
  }

  /**
   * Atualiza um registro na tabela com base no ID.
   *
   * @param id O identificador do registro a ser atualizado.
   * @param values Os novos valores do registro.
   */
  public void setById(String id, Object... values) {
    Object[] newParams = new Object[values.length + 1];
    System.arraycopy(values, 0, newParams, 0, values.length);
    newParams[values.length] = id;

    databaseService.update(buildUpdateQuery(), newParams);
  }

  /**
   * Remove um registro da tabela com base no ID.
   *
   * @param id O identificador do registro a ser removido.
   */
  public void removeById(String id) {
    String query = "DELETE FROM " + tableName + " WHERE id = ?";
    databaseService.delete(query, id);
  }

  /**
   * Remove todos os registros da tabela.
   */
  public void removeAll() {
    String query = "DELETE FROM " + tableName;
    databaseService.delete(query);
  }

  /**
   * Constrói a consulta SQL de inserção.
   *
   * @return A consulta SQL de inserção.
   */
  private String buildInsertQuery() {
    StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (id, ");
    for (String column : columns) {
      query.append(column).append(", ");
    }

    query.setLength(query.length() - 2);
    query.append(") VALUES (?, ");
    for (int i = 0; i < columns.length; i++) {
      query.append("?, ");
    }

    query.setLength(query.length() - 2);
    query.append(")");

    return query.toString();
  }

  /**
   * Constrói a consulta SQL de atualização.
   *
   * @return A consulta SQL de atualização.
   */
  private String buildUpdateQuery() {
    StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");

    for (String column : columns) {
      query.append(column).append(" = ?, ");
    }

    query.setLength(query.length() - 2);
    query.append(" WHERE id = ?");

    return query.toString();
  }
}
