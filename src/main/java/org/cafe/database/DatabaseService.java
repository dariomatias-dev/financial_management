package org.cafe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Fornece operações CRUD (CREATE, READ, UPDATE, DELETE) para interação com o banco de dados.
 */
public class DatabaseService {
  private final DatabaseManager databaseManager;

  /**
   * Inicializa o DatabaseManager para gerenciar a conexão com o banco de dados.
   */
  public DatabaseService() {
    this.databaseManager = new DatabaseManager();
  }

  /**
   * CREATE: Executa uma consulta de INSERT, gerando um ID do tipo UUID para o registro.
   *
   * @param query Consulta de INSERT a ser executada.
   * @param params Valores do registro.
   * @return Retorna o ID do registro criado.
   */
  public String create(String query, Object... params) {
    Object[] newParams = new Object[params.length + 1];
    String uuid = UUID.randomUUID().toString();
    newParams[0] = uuid; // Gera um UUID único para o item.
    System.arraycopy(params, 0, newParams, 1, params.length);

    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, newParams);
      statement.executeUpdate();
      
      return uuid;
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar INSERT.", e);
    }
  }

  /**
   * READ: Executa uma consulta de SELECT e retorna os resultados como uma lista de arrays de objetos.
   *
   * @param query Consulta de SELECT a ser executada.
   * @param params Parâmetros da consulta.
   * @return Uma lista contendo todos os registros da tabela.
   */
  public ArrayList<Object[]> read(String query, Object... params) {
    ArrayList<Object[]> results = new ArrayList<>();
    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, params);

      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        int columnCount = rs.getMetaData().getColumnCount();
        Object[] rows = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
          rows[i] = rs.getObject(i + 1);
        }
        results.add(rows);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar SELECT.", e);
    }

    return results;
  }

  /**
   * UPDATE: Executa uma consulta de UPDATE com base no ID fornecido.
   *
   * @param query Consulta de UPDATE a ser executada.
   * @param params Novos valores do registro e parâmetros.
   */
  public void update(String query, Object... params) {
    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, params);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar UPDATE.", e);
    }
  }

  /**
   * DELETE: Executa uma consulta de DELETE com base no ID fornecido.
   *
   * @param query Consulta de DELETE a ser executada.
   * @param params Parâmetros da consulta.
   */
  public void delete(String query, Object... params) {
    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, params);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar DELETE.", e);
    }
  }

  // RECURSOS

  /**
   * Define os parâmetros da consulta SQL.
   *
   * @param statement O statement preparado para executar a consulta SQL.
   * @param params Os parâmetros que serão atribuídos à consulta SQL.
   * @throws SQLException Se ocorrer um erro ao definir os parâmetros no statement.
   */
  private void setParameters(PreparedStatement statement, Object[] params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      statement.setObject(i + 1, params[i]);
    }
  }
}
