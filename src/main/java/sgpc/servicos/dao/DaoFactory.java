package sgpc.servicos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa o comportamento padrão de toda fábrica de objetos de
 * acesso a dados.
 *
 */
public abstract class DaoFactory {

  public static final String MYSQL = "mysql";
  public static final String POSTGRES = "postgres";
  public static final String MARIADB = "mariadb";
  
  protected static Properties propriedades;          

  public static DaoFactory getFactory(String tipoFabrica) {

    DaoFactory fabrica = null;

    if (MYSQL.equals(tipoFabrica)) {
      fabrica = new MySqlDaoFactory();
    } /*else if (POSTGRES.equals(tipoFabrica)) {
      fabrica = new PostgresDaoFactory();
    } else if (MARIADB.equals(tipoFabrica)) {
      fabrica = new MariaDbDaoFactory();
    }*/
    return fabrica;
  }

  public abstract UsuarioDao getUsuarioDao();
/*  public abstract DiariaDao getDiariaDao();
  public abstract PacoteDao getPacoteDao();*/

  abstract Connection criarConexao();

  void fecharConexao(Connection conexao) {
    try {
      if (conexao != null) {
        conexao.close();
      }
    } catch (SQLException sqle) {
      Logger.getLogger("DaoFactory").log(Level.SEVERE, "Problemas ao fechar a conexão");
      Logger.getLogger("DaoFactory").log(Level.SEVERE, 
              "Mensagem de erro: {0}", sqle.getMessage());
    }
  }
  
  static String lerPropriedade(String chave) {
    return propriedades.getProperty(chave);
  }
}
